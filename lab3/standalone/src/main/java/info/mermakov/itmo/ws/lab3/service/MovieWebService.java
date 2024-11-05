package info.mermakov.itmo.ws.lab3.service;

import info.mermakov.itmo.ws.lab3.exception.InvalidBodyException;
import info.mermakov.itmo.ws.lab3.exception.MovieServiceFault;
import info.mermakov.itmo.ws.lab3.model.ChangeRequest;
import info.mermakov.itmo.ws.lab3.model.Movie;
import info.mermakov.itmo.ws.lab3.model.Request;
import info.mermakov.itmo.ws.lab3.repository.MovieDAO;
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
    public Long addMovie(@WebParam(name = "createRequest") ChangeRequest request) throws InvalidBodyException {
        validateBody(request);
        return movieDAO.createMovie(request);
    }

    @WebMethod(operationName = "deleteMovie")
    public Boolean deleteMovie(@WebParam(name = "deleteId") Long id) {
        return movieDAO.deleteMovie(id);
    }

    @WebMethod(operationName = "updateMovie")
    public Boolean updateMovie(
            @WebParam(name = "updateId") Long id,
            @WebParam(name = "updateRequest") ChangeRequest request
    ) throws InvalidBodyException {
        validateBody(request);
        return movieDAO.updateMovie(id, request);
    }

    private void validateBody(ChangeRequest request) throws InvalidBodyException {
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            MovieServiceFault fault = MovieServiceFault.defaultInstance();
            fault.setMessage("Title is required");
            throw new InvalidBodyException(fault);
        }

        if (request.getYear() == null || request.getYear() <= 1900) {
            MovieServiceFault fault = MovieServiceFault.defaultInstance();
            fault.setMessage("Year is required");
            throw new InvalidBodyException(fault);
        }

        if (request.getDuration() == null || request.getDuration() < 5) {
            MovieServiceFault fault = MovieServiceFault.defaultInstance();
            fault.setMessage("Duration is required");
            throw new InvalidBodyException(fault);
        }

        if (request.getStudio() == null || request.getStudio().trim().isEmpty()) {
            MovieServiceFault fault = MovieServiceFault.defaultInstance();
            fault.setMessage("Studio is required");
            throw new InvalidBodyException(fault);
        }

        if (request.getDirector() == null || request.getDirector().trim().isEmpty()) {
            MovieServiceFault fault = MovieServiceFault.defaultInstance();
            fault.setMessage("Director is required");
            throw new InvalidBodyException(fault);
        }
    }
}
