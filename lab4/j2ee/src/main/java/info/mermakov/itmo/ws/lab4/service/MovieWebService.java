package info.mermakov.itmo.ws.lab4.service;

import info.mermakov.itmo.ws.lab4.model.*;
import info.mermakov.itmo.ws.lab4.repository.MovieDAO;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.extern.java.Log;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;

@Log
@RequestScoped
@Path("/movies")
@Produces({MediaType.APPLICATION_JSON})
public class MovieWebService {
    @Resource(name = "jdbc/itmo-ws")
    private DataSource dataSource;

    @GET
    public List<Movie> getMovies() {
        try (Connection connection = getConnection()) {
            MovieDAO movieDAO = new MovieDAO(connection);
            return movieDAO.getMovies(null);
        } catch (Exception exception) {
            log.log(Level.SEVERE, exception.getMessage(), exception);
            throw new RuntimeException(exception);
        }
    }

    @GET
    @Path("/find")
    public List<Movie> getMovies(
            @QueryParam("key") @XmlElement(required = true) SearchKey key,
            @QueryParam("operator") @XmlElement(required = true) Operator operator,
            @QueryParam("data") @XmlElement(required = true) String data
    ) {
        try (Connection connection = getConnection()) {
            Request request = new Request(List.of(new RequestData(key, data, operator)));
            MovieDAO movieDAO = new MovieDAO(connection);
            return movieDAO.getMovies(request);
        } catch (Exception exception) {
            log.log(Level.SEVERE, exception.getMessage(), exception);
            throw new RuntimeException(exception);
        }
    }

    private Connection getConnection() {
        if (dataSource == null) {
            try {
                InitialContext cxt = new InitialContext();
                dataSource = (DataSource) cxt.lookup("jdbc/itmo-ws");
            } catch (Exception exception) {
                log.log(Level.SEVERE, exception.getMessage(), exception);
                throw new RuntimeException(exception);
            }
        }
        try {
            return dataSource.getConnection();
        } catch (Exception exception) {
            log.log(Level.SEVERE, exception.getMessage(), exception);
            throw new RuntimeException(exception);
        }
    }
}
