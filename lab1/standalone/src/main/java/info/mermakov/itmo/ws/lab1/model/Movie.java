package info.mermakov.itmo.ws.lab1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {
    private Long id;
    private String title;
    private Short year;
    private Short duration;
    private String studio;
    private String director;
}
