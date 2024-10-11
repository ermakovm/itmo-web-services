package info.mermakov.itmo.ws.lab1.service;

import info.mermakov.itmo.ws.lab1.model.Movie;
import info.mermakov.itmo.ws.lab1.model.Request;
import info.mermakov.itmo.ws.lab1.repository.MovieDAO;
import jakarta.annotation.Resource;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.extern.java.Log;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;

@Log
@WebService(serviceName = "MovieService")
public class MovieWebService {
    @Resource(name = "jdbc/itmo-ws")
    private DataSource dataSource;

    @WebMethod(operationName = "getMovies")
    public List<Movie> getMovies(@WebParam(name = "request") Request request) {
        try (Connection connection = getConnection()) {
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
