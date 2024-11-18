package info.mermakov.itmo.ws.lab3.client;

import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

import javax.xml.namespace.QName;
import java.util.Base64;
import java.util.Set;

public class AuthHandler implements SOAPHandler<SOAPMessageContext> {
    private static final Set<String> securedMethods = Set.of("addMovie", "deleteMovie", "updateMovie");
    private static final String USERNAME = "test_user";
    private static final String PASSWORD = "test_password";
    private static final String AUTH_HEADER = "Authorization";
    private static final String NAMESPACE = "http://service.lab3.ws.itmo.mermakov.info/";

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (isRequest) {
            QName qName = (QName) context.get(MessageContext.WSDL_OPERATION);
            String methodName = qName.getLocalPart();
            System.err.println("Method: " + methodName);
            if (securedMethods.contains(methodName)) {
                System.err.println("Add header to request");
                try {
                    SOAPHeader soapHeader = context.getMessage().getSOAPHeader();
                    if (soapHeader == null) {
                        soapHeader = context.getMessage().getSOAPPart().getEnvelope().addHeader();
                    }
                    soapHeader.addChildElement(new QName(NAMESPACE, AUTH_HEADER)).setValue(getHeaderValue());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }

    private String getHeaderValue() {
        String credentials = USERNAME + ":" + PASSWORD;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        return "Basic " + base64Credentials;
    }
}
