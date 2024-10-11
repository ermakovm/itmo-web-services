package info.mermakov.itmo.ws.lab1.service;

import info.mermakov.itmo.ws.lab1.model.Movie;
import info.mermakov.itmo.ws.lab1.model.Request;
import info.mermakov.itmo.ws.lab1.repository.MovieDAO;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService(serviceName = "MovieService")
public class MovieWebService {
    private final MovieDAO movieDAO;

    public MovieWebService() {
        this.movieDAO = new MovieDAO();
    }

    @WebMethod(operationName = "getMovies")
    public List<Movie> getMovies(@WebParam(name = "request") Request request) {
        return movieDAO.getMovies(request);
    }
}
