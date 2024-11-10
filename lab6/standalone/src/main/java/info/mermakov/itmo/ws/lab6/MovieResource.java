package info.mermakov.itmo.ws.lab6;

import info.mermakov.itmo.ws.lab6.exception.InvalidBodyException;
import info.mermakov.itmo.ws.lab6.model.ChangeRequest;
import info.mermakov.itmo.ws.lab6.model.Movie;
import info.mermakov.itmo.ws.lab6.model.Request;
import info.mermakov.itmo.ws.lab6.repository.MovieDAO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {
    private final MovieDAO movieDAO;

    public MovieResource() {
        movieDAO = new MovieDAO();
    }

    @GET
    public List<Movie> getMovies() {
        return movieDAO.getMovies(null);
    }

    @POST
    @Path("/find")
    public List<Movie> getMovies(Request request) {
        return movieDAO.getMovies(request);
    }

    @POST
    public Long addMovie(ChangeRequest request) throws InvalidBodyException {
        validateBody(request);
        return movieDAO.createMovie(request);
    }

    @DELETE
    @Path("/{id}")
    public Boolean deleteMovie(@PathParam("id") Long id) {
        return movieDAO.deleteMovie(id);
    }

    @PUT
    @Path("/{id}")
    public Boolean updateMovie(@PathParam("id") Long id, ChangeRequest request) throws InvalidBodyException {
        validateBody(request);
        return movieDAO.updateMovie(id, request);
    }

    private void validateBody(ChangeRequest request) throws InvalidBodyException {
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new InvalidBodyException("Title is required");
        }

        if (request.getYear() == null || request.getYear() <= 1900) {
            throw new InvalidBodyException("Year is required");
        }

        if (request.getDuration() == null || request.getDuration() < 5) {
            throw new InvalidBodyException("Duration is required");
        }

        if (request.getStudio() == null || request.getStudio().trim().isEmpty()) {
            throw new InvalidBodyException("Studio is required");
        }

        if (request.getDirector() == null || request.getDirector().trim().isEmpty()) {
            throw new InvalidBodyException("Director is required");
        }
    }
}
