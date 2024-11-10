package info.mermakov.itmo.ws.lab5.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestData {
    private SearchKey key;
    private String value;
    private Operator operator;
}
