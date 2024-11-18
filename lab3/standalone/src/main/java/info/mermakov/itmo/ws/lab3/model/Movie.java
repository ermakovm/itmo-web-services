package info.mermakov.itmo.ws.lab3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private Long id;
    private String title;
    private Short year;
    private Short duration;
    private String studio;
    private String director;
}
