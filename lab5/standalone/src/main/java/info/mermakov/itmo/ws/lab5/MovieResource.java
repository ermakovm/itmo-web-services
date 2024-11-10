package info.mermakov.itmo.ws.lab5;

import info.mermakov.itmo.ws.lab5.model.ChangeRequest;
import info.mermakov.itmo.ws.lab5.model.Movie;
import info.mermakov.itmo.ws.lab5.model.Request;
import info.mermakov.itmo.ws.lab5.repository.MovieDAO;
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
    public Long addMovie(ChangeRequest request) {
        return movieDAO.createMovie(request);
    }

    @DELETE
    @Path("/{id}")
    public Boolean deleteMovie(@PathParam("id") Long id) {
        return movieDAO.deleteMovie(id);
    }

    @PUT
    @Path("/{id}")
    public Boolean updateMovie(@PathParam("id") Long id, ChangeRequest request) {
        return movieDAO.updateMovie(id, request);
    }
}
