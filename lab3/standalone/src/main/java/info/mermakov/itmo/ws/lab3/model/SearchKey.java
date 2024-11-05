package info.mermakov.itmo.ws.lab3.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum SearchKey {
    TITLE("title", Set.of(Operator.LIKE)),
    STUDIO("studio", Set.of(Operator.LIKE)),
    DIRECTOR("director", Set.of(Operator.LIKE)),
    YEAR("year", Set.of(Operator.EQ, Operator.GTE, Operator.LTE, Operator.GT, Operator.LT)),
    DURATION("duration_minutes", Set.of(Operator.EQ, Operator.GTE, Operator.LTE, Operator.GT, Operator.LT));


    private final String field;
    private final Set<Operator> operators;
}
