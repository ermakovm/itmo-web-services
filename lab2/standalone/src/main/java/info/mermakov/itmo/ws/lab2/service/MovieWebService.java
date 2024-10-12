package info.mermakov.itmo.ws.lab2.service;

import info.mermakov.itmo.ws.lab2.model.ChangeRequest;
import info.mermakov.itmo.ws.lab2.model.Movie;
import info.mermakov.itmo.ws.lab2.model.Request;
import info.mermakov.itmo.ws.lab2.repository.MovieDAO;
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

    @WebMethod(operationName = "addMovie")
    public Long addMovie(@WebParam(name = "createRequest") ChangeRequest request) {
        return movieDAO.createMovie(request);
    }

    @WebMethod(operationName = "deleteMovie")
    public Boolean deleteMovie(@WebParam(name = "deleteId") Long id) {
        return movieDAO.deleteMovie(id);
    }

    @WebMethod(operationName = "updateMovie")
    public Boolean updateMovie(@WebParam(name = "updateId") Long id, @WebParam(name = "updateRequest") ChangeRequest request) {
        return movieDAO.updateMovie(id, request);
    }
}
