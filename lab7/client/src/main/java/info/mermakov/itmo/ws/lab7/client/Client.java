package info.mermakov.itmo.ws.lab7.client;

import ws.*;

import java.net.URL;
import java.util.List;
import java.util.Scanner;

import static ws.SearchKey.DURATION;
import static ws.SearchKey.YEAR;

public class Client {
    private final MovieService movieService;
    private static final String DELIMETER = System.lineSeparator();
    private static final Scanner SCANNER = new Scanner(System.in);

    public Client(URL url) {
        movieService = new MovieService(url);
    }

    public void startClient() {
        printHelp();
        int userChoice = 0;
        while ((userChoice = SCANNER.nextInt()) > 0) {
            switch (userChoice) {
                case 1:
                    Request request = new Request();
                    List<Movie> movies = movieService.getMovieWebServicePort().getMovies(request);
                    printMovies(movies);
                    System.out.println("--");
                    printHelp();
                    break;
                case 2:
                    processCustomRequest();
                    break;
                case 3:
                    return;
                default:
                    printHelp();
            }
        }
    }

    private void processCustomRequest() {
        System.out.println("--" + DELIMETER
                + "Available criteria: TITLE, STUDIO, DIRECTOR, YEAR, DURATION" + DELIMETER
                + "Enter criteria count: " + DELIMETER);
        int criteriaCount = SCANNER.nextInt();
        Request request = new Request();
        List<RequestData> requestData = request.getRequestData();
        for (int i = 0; i < criteriaCount; i++) {
            printSelectCriteria();
            int currentCriteria = SCANNER.nextInt();

            SearchKey key = getKey(currentCriteria);
            if (key == null)
                return;
            System.out.println("Selected criteria: " + key.value());
            printOperator(key);
            int operNumber = SCANNER.nextInt();

            Operator operator = getOperator(operNumber, key);
            System.out.println("Selected operator: " + operator.value());
            if (operator == null)
                return;

            System.out.println("--" + DELIMETER
                    + "Enter search string: " + DELIMETER);
            SCANNER.nextLine();
            String searchString = SCANNER.nextLine();

            RequestData newData = new RequestData();
            newData.setKey(key);
            newData.setOperator(operator);
            newData.setValue(searchString);
            requestData.add(newData);
            System.out.println("--" + DELIMETER
                    + "Added search criteria: {key=" + key + ", operator=" + operator + ", value=" + searchString + "}"
                    + DELIMETER);
        }


        List<Movie> movies = movieService.getMovieWebServicePort().getMovies(request);
        printMovies(movies);
        System.out.println("--");
        printHelp();
    }

    private Operator getOperator(int choice, SearchKey key) {
        if (YEAR.equals(key) || DURATION.equals(key)) {
            return switch (choice) {
                case 1 -> Operator.EQ;
                case 2 -> Operator.GT;
                case 3 -> Operator.GTE;
                case 4 -> Operator.LT;
                case 5 -> Operator.LTE;
                default -> null;
            };
        } else {
            if (choice == 1) {
                return Operator.LIKE;
            } else {
                return null;
            }
        }
    }

    private SearchKey getKey(int choice) {
        return switch (choice) {
            case 1 -> SearchKey.TITLE;
            case 2 -> SearchKey.STUDIO;
            case 3 -> SearchKey.DIRECTOR;
            case 4 -> YEAR;
            case 5 -> SearchKey.DURATION;
            default -> null;
        };
    }

    private void printOperator(SearchKey key) {
        switch (key) {
            case TITLE, STUDIO, DIRECTOR:
                System.out.println("--" + DELIMETER
                        + "1. LIKE");
                break;
            case YEAR, DURATION:
                System.out.println("--" + DELIMETER
                        + "1. EQ" + DELIMETER
                        + "2. GT" + DELIMETER
                        + "3. GTE" + DELIMETER
                        + "4. LT" + DELIMETER
                        + "5. LTE" + DELIMETER);
                break;
        }
    }

    private void printSelectCriteria() {
        System.out.println("--" + DELIMETER
                + "Select criteria:" + DELIMETER
                + "1. TITLE" + DELIMETER
                + "2. STUDIO" + DELIMETER
                + "3. DIRECTOR" + DELIMETER
                + "4. YEAR" + DELIMETER
                + "5. DURATION" + DELIMETER);
    }

    private void printHelp() {
        System.out.println("Usage: " + DELIMETER
                + "1. Get all movies" + DELIMETER
                + "2. Find movies" + DELIMETER
                + "3. Exit" + DELIMETER);
    }

    private void printMovies(List<Movie> movies) {
        movies.forEach(this::printMovie);
    }

    private void printMovie(Movie movie) {
        String result =
                "Movie {id=" + movie.getId() +
                        ", title=" + movie.getTitle() +
                        ", year=" + movie.getYear() +
                        ", director=" + movie.getDirector() +
                        ", duration=" + movie.getDuration() +
                        ", studio=" + movie.getStudio() +
                        "}";
        System.out.println(result);
    }
}
