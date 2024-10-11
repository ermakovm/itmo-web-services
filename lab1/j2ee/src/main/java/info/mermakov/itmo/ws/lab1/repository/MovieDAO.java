package info.mermakov.itmo.ws.lab1.repository;

import info.mermakov.itmo.ws.lab1.model.*;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Log
public class MovieDAO {
    private static final String DEFAULT_QUERY = "select * from movies";
    private static final String QUERY = "select * from movies where ";

    private final Connection connection;

    public MovieDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Movie> getMovies(Request request) {
        if (request != null && request.getRequestData() != null && !request.getRequestData().isEmpty()) {
            return executeQuery(request.getRequestData());
        }
        return executeQuery();
    }

    private List<Movie> executeQuery(List<RequestData> requestData) {
        List<RequestData> validRequestData = requestData.stream().filter(this::validateRequest).toList();

        String params = validRequestData.stream().map(item -> {
            String field = item.getKey().getField();
            Operator operator = item.getOperator();

            return "(" + field + " " + operator.getSqlOperator() + " ?)";
        }).collect(Collectors.joining(" AND "));
        String query = QUERY + params;

        log.log(Level.INFO, query);

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int index = 1;
            for (RequestData data : validRequestData) {
                String value = data.getValue();
                Operator operator = data.getOperator();

                if (Operator.LIKE.equals(operator)) {
                    String valueStr = "%" + value + "%";
                    statement.setString(index++, valueStr);
                } else {
                    Short shortValue = Short.parseShort(value);
                    statement.setShort(index++, shortValue);
                }
            }


            ResultSet resultSet = statement.executeQuery();

            return getMovies(resultSet);
        } catch (SQLException exception) {
            log.log(Level.SEVERE, exception.getMessage(), exception);
            throw new RuntimeException(exception);
        }
    }

    private List<Movie> executeQuery() {
        try (PreparedStatement statement = connection.prepareStatement(DEFAULT_QUERY)) {
            ResultSet resultSet = statement.executeQuery();

            return getMovies(resultSet);
        } catch (SQLException exception) {
            log.log(Level.SEVERE, exception.getMessage(), exception);
            throw new RuntimeException(exception);
        }
    }

    private List<Movie> getMovies(ResultSet resultSet) throws SQLException {
        List<Movie> movies = new ArrayList<>();

        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            Short year = resultSet.getShort("year");
            Short duration = resultSet.getShort("duration_minutes");
            String studio = resultSet.getString("studio");
            String director = resultSet.getString("director");

            movies.add(new Movie(id, title, year, duration, studio, director));
        }

        return movies;
    }

    private boolean validateRequest(RequestData request) {
        SearchKey key = request.getKey();
        Operator operator = request.getOperator();
        String value = request.getValue();


        if (key == null || operator == null || value == null) {
            return false;
        }
        if (Operator.LIKE.equals(operator)) {
            return true;
        }
        try {
            Short.parseShort(value);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
