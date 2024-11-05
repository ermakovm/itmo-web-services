package info.mermakov.itmo.ws.lab3.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Operator {
    LIKE("LIKE"),
    EQ("="),
    GTE(">="),
    LTE("<="),
    GT(">"),
    LT("<");

    private final String sqlOperator;
}
