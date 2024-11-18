package info.mermakov.itmo.ws.lab3;

import com.sun.istack.logging.Logger;
import info.mermakov.itmo.ws.lab3.filter.BasicAuthHandler;
import info.mermakov.itmo.ws.lab3.service.MovieWebService;
import jakarta.xml.ws.Endpoint;

import java.util.List;
import java.util.logging.Level;

public class Application {
    public static void main(String[] args) {
        String serviceUrl = "http://0.0.0.0:8080/ws/movie-service";

        MovieWebService service = new MovieWebService();
        Endpoint endpoint = Endpoint.create(service);
        endpoint.getBinding().setHandlerChain(List.of(new BasicAuthHandler(service)));
        endpoint.publish(serviceUrl);

        Logger.getLogger(Application.class).log(Level.INFO, "Server started");
    }
}
