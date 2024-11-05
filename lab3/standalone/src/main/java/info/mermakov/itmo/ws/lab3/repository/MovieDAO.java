package info.mermakov.itmo.ws.lab3.repository;


import info.mermakov.itmo.ws.lab3.config.ConnectionUtil;
import info.mermakov.itmo.ws.lab3.exception.MovieServiceFault;
import info.mermakov.itmo.ws.lab3.exception.InvalidBodyException;
import info.mermakov.itmo.ws.lab3.model.*;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Log
public class MovieDAO {
    private static final String DEFAULT_QUERY = "select * from movies";
    private static final String QUERY = "select * from movies where ";
    private static final String DELETE_QUERY = "delete from movies where id = ?";
    private static final String INSERT_QUERY = "insert into movies(title, year, duration_minutes, studio, director) " +
            "values(?,?,?,?,?)";
    private static final String UPDATE_QUERY = "update movies set title = ?, " +
            "year = ?, " +
            "duration_minutes = ?, " +
            "studio = ?, " +
            "director = ? " +
            "where id = ?";

    public Boolean deleteMovie(Long movieId) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)
        ) {
            statement.setLong(1, movieId);
            return statement.executeUpdate() > 0;
        } catch (SQLException exception) {
            log.log(Level.SEVERE, exception.getMessage(), exception);
            throw new RuntimeException(exception);
        }
    }

    public Boolean updateMovie(Long movieId, ChangeRequest request) {
        if (request.getTitle() == null
                || request.getDirector() == null
                || request.getStudio() == null
                || request.getYear() == null
                || request.getDuration() == null
        ) {
            return false;
        }

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, request.getTitle());
            statement.setShort(2, request.getYear());
            statement.setShort(3, request.getDuration());
            statement.setString(4, request.getStudio());
            statement.setString(5, request.getDirector());
            statement.setLong(6, movieId);

            return statement.executeUpdate() > 0;
        } catch (SQLException exception) {
            log.log(Level.SEVERE, exception.getMessage(), exception);
            throw new RuntimeException(exception);
        }
    }

    public Long createMovie(ChangeRequest request) {
        if (request.getTitle() == null
                || request.getDirector() == null
                || request.getStudio() == null
                || request.getYear() == null
                || request.getDuration() == null
        ) {
            return -1L;
        }
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, request.getTitle());
            statement.setShort(2, request.getYear());
            statement.setShort(3, request.getDuration());
            statement.setString(4, request.getStudio());
            statement.setString(5, request.getDirector());

            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                }
                return -1L;
            }
        } catch (SQLException exception) {
            log.log(Level.SEVERE, exception.getMessage(), exception);
            throw new RuntimeException(exception);
        }
    }

    public List<Movie> getMovies(Request request) {
        if (request != null && request.getRequestData() != null && !request.getRequestData().isEmpty()) {
            return executeQuery(request.getRequestData());
        }
        return executeQuery();
    }

    private List<Movie> executeQuery(List<RequestData> requestData) {
        try (Connection connection = ConnectionUtil.getConnection()) {
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
                        short shortValue = Short.parseShort(value);
                        statement.setShort(index++, shortValue);
                    }
                }

                try (ResultSet resultSet = statement.executeQuery()) {
                    return getMovies(resultSet);
                }
            }
        } catch (SQLException exception) {
            log.log(Level.SEVERE, exception.getMessage(), exception);
            throw new RuntimeException(exception);
        }
    }

    private List<Movie> executeQuery() {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DEFAULT_QUERY)
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                return getMovies(resultSet);
            }
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
