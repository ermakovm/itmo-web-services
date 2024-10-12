package info.mermakov.itmo.ws.lab2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeRequest {
    private String title;
    private Short year;
    private Short duration;
    private String studio;
    private String director;
}
