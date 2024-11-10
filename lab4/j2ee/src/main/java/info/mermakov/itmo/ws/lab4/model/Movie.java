package info.mermakov.itmo.ws.lab4.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Movie {
    private Long id;
    private String title;
    private Short year;
    private Short duration;
    private String studio;
    private String director;
}
