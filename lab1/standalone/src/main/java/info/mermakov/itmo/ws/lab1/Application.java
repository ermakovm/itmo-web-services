package info.mermakov.itmo.ws.lab1;


import com.sun.istack.logging.Logger;
import info.mermakov.itmo.ws.lab1.service.MovieService;
import jakarta.xml.ws.Endpoint;

import java.util.logging.Level;

public class Application {
    public static void main(String[] args) {
        String serviceUrl = "http://0.0.0.0:8080/MovieService";
        MovieService service = new MovieService();
        Endpoint.publish(serviceUrl, service);

        Logger.getLogger(Application.class).log(Level.INFO, "Server started");
    }
}
