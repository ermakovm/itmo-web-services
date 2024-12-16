package info.mermakov.itmo.ws.lab7.client;

import lombok.extern.java.Log;
import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.config.UDDIClerk;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.*;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

@Log
public class UddiService {
    private final UDDIClient uddiClient;
    private final UDDISecurityPortType securityPortType;
    private final UDDIPublicationPortType publicationPortType;

    public UddiService() {
        try {
            uddiClient = new UDDIClient("META-INF/uddi.xml");
            Transport uddiTransport = uddiClient.getTransport("default");
            securityPortType = uddiTransport.getUDDISecurityService();
            publicationPortType = uddiTransport.getUDDIPublishService();
        } catch (Exception exception) {
            log.log(Level.SEVERE, "Exception in UddiService", exception);
            throw new RuntimeException(exception);
        }
    }

    public void publish(String businessName, String serviceName, String location) {
        try {
            ZonedDateTime currentTime = ZonedDateTime.now();
            String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(currentTime);
            UDDIClerk clerk = uddiClient.getClerk("default");

            BusinessEntity businessEntity = new BusinessEntity();
            Name bName = new Name();
            bName.setValue(businessName + " - " + date);
            businessEntity.getName().add(bName);

            BusinessEntity savedEntity = clerk.register(businessEntity);

            BusinessService businessService = new BusinessService();
            businessService.setBusinessKey(savedEntity.getBusinessKey());
            Name sName = new Name();
            sName.setValue(serviceName);
            businessService.getName().add(sName);

            BindingTemplate bindingTemplate = new BindingTemplate();
            AccessPoint accessPoint = new AccessPoint();
            accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
            accessPoint.setValue(location);
            bindingTemplate.setAccessPoint(accessPoint);

            BindingTemplates bindingTemplates = new BindingTemplates();
            bindingTemplates.getBindingTemplate().add(UDDIClient.addSOAPtModels(bindingTemplate));

            businessService.setBindingTemplates(bindingTemplates);

            clerk.register(businessService);
        } catch (Exception exception) {
            log.log(Level.SEVERE, "Exception in UddiService", exception);
            throw new RuntimeException(exception);
        }
    }
}
