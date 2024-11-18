package info.mermakov.itmo.ws.lab3.filter;

import info.mermakov.itmo.ws.lab3.annotation.Secured;
import info.mermakov.itmo.ws.lab3.service.MovieWebService;
import jakarta.xml.soap.*;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

import javax.xml.namespace.QName;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class BasicAuthHandler implements SOAPHandler<SOAPMessageContext> {
    private static final String USERNAME = "test_user";
    private static final String PASSWORD = "test_password";
    private static final String AUTH_HEADER = "Authorization";
    private static final String NAMESPACE = "http://service.lab3.ws.itmo.mermakov.info/";
    private static final String AUTH_SCHEME = "Basic";
    private final Set<String> securedMethods;

    public BasicAuthHandler(final MovieWebService movieWebService) {
        securedMethods = Arrays.stream(movieWebService.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(Secured.class))
                .map(Method::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);


        if (isRequest != null && !isRequest) {
            QName qName = (QName) context.get(MessageContext.WSDL_OPERATION);
            String methodName = qName.getLocalPart();
            if (methodName == null || !securedMethods.contains(methodName)) {
                return true;
            }

            try {
                SOAPMessage soapMessage = context.getMessage();
                SOAPHeader soapHeader = soapMessage.getSOAPHeader();
                if (soapHeader == null) {
                    setFault(context, "Authorization failed", "Header not found");
                    return false;
                }

                Iterator<Node> headerChildren = soapHeader.getChildElements(new QName(NAMESPACE, AUTH_HEADER));
                if (!headerChildren.hasNext()) {
                    setFault(context, "Authorization failed", "Header not found");
                    return false;
                }

                SOAPElement soapElement = (SOAPElement) headerChildren.next();

                String authValue = soapElement.getValue();
                if (!authValue.startsWith(AUTH_SCHEME)) {
                    setFault(context, "Authorization failed", "Invalid auth scheme");
                    return false;
                }

                String encodedCredentials = authValue.substring(AUTH_SCHEME.length()).trim();
                String decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials));
                StringTokenizer tokenizer = new StringTokenizer(decodedCredentials, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();

                if (!isValidUser(username, password)) {
                    setFault(context, "Authorization failed", "Invalid username or password");
                    return false;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                setFault(context, "Authorization failed", exception.getMessage());
                return false;
            }
        }

        return true;
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleFault(SOAPMessageContext soapMessageContext) {
        return false;
    }

    @Override
    public void close(MessageContext messageContext) {
    }

    private boolean isValidUser(String username, String password) {
        return USERNAME.equals(username) && PASSWORD.equals(password);
    }

    private void setFault(SOAPMessageContext context, String faultString, String message) {
        try {
            SOAPMessage soapMessage = context.getMessage();
            SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();


            SOAPBody soapBody = soapEnvelope.getBody();
            soapBody.removeContents();
            SOAPFault fault = soapBody.addFault();
            fault.setFaultString(faultString);

            fault.addDetail()
                    .addChildElement(new QName(NAMESPACE, "AuthException", "ns2"))
                    .addChildElement("message").addTextNode(message);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
