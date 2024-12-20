package info.mermakov.itmo.ws.lab7.client;

import lombok.extern.java.Log;

import java.net.URI;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;

@Log
public class Application {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final UddiService UDDI_SERVICE = new UddiService();

    public static void main(String[] args) {
        try {
            while (true) {
                System.out.println("1. Register service");
                System.out.println("2. Find service");
                System.out.println("3. Exit");

                int userChoise = SCANNER.nextInt();
                SCANNER.nextLine();
                switch (userChoise) {
                    case 1:
                        System.out.println("Enter service name:");
                        String serviceName = SCANNER.nextLine();
                        System.out.println("Enter business name:");
                        String businessName = SCANNER.nextLine();
                        System.out.println("Enter service url:");
                        String serviceUrl = SCANNER.nextLine();
                        UDDI_SERVICE.publish(businessName, serviceName, serviceUrl);
                        break;
                    case 2:
                        System.out.println("Enter service name:");
                        String sName = SCANNER.nextLine();
                        System.out.println("Enter business name:");
                        String bName = SCANNER.nextLine();
                        String location = UDDI_SERVICE.findServiceLocation(bName, sName);

                        URL serviceURL = new URI(location).toURL();
                        Client client = new Client(serviceURL);
                        client.startClient();
                        break;
                    case 3:
                        System.exit(0);
                }
            }
        } catch (Exception exception) {
            log.log(Level.SEVERE, exception.getMessage(), exception);
        }
    }
}
