package info.mermakov.itmo.ws.lab4;

import info.mermakov.itmo.ws.lab4.model.*;
import info.mermakov.itmo.ws.lab4.repository.MovieDAO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.List;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {
    private final MovieDAO movieDAO;

    public MovieResource() {
        movieDAO = new MovieDAO();
    }

    @GET
    public List<Movie> getMovies() {
        return movieDAO.getMovies(null);
    }

    @GET
    @Path("/find")
    public List<Movie> getMovies(
            @QueryParam("key") @XmlElement(required = true) SearchKey key,
            @QueryParam("operator") @XmlElement(required = true) Operator operator,
            @QueryParam("data") @XmlElement(required = true) String data
    ) {
        Request request = new Request(List.of(new RequestData(key, data, operator)));
        return movieDAO.getMovies(request);
    }
}
