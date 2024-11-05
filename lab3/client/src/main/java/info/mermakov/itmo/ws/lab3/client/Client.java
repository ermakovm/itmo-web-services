package info.mermakov.itmo.ws.lab3.client;

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
                    processAddMovie();
                    break;
                case 4:
                    deleteMovie();
                    break;
                case 5:
                    processUpdateMovie();
                    break;
                case 6:
                    return;
                default:
                    printHelp();
            }
        }
    }

    private void processAddMovie() {
        ChangeRequest changeRequest = getChangeRequest();

        try {
            Long result = movieService.getMovieWebServicePort().addMovie(changeRequest);
            System.out.println("New movie added: " + result);
        } catch (InvalidBodyException exception) {
            System.err.println(exception.getFaultInfo().getMessage());
        }

        System.out.println("--");
        printHelp();
    }

    private void processUpdateMovie() {
        System.out.println("--" + DELIMETER
                + "Movie id for update:" + DELIMETER);
        Long id = SCANNER.nextLong();
        ChangeRequest request = getChangeRequest();
        try {
            Boolean result = movieService.getMovieWebServicePort().updateMovie(id, request);
            System.out.println("Update result: " + result);
        } catch (InvalidBodyException exception) {
            System.err.println(exception.getFaultInfo().getMessage());
        }
        System.out.println("--");
        printHelp();
    }

    private ChangeRequest getChangeRequest() {
        System.out.println("--" + DELIMETER
                + "Title:" + DELIMETER);
        SCANNER.nextLine();
        String title = SCANNER.nextLine();

        System.out.println("Studio:" + DELIMETER);
        String studio = SCANNER.nextLine();


        System.out.println("Director:" + DELIMETER);
        String director = SCANNER.nextLine();

        System.out.println("Year:" + DELIMETER);
        Short year = SCANNER.nextShort();

        System.out.println("Duration:" + DELIMETER);
        Short duration = SCANNER.nextShort();

        ChangeRequest changeRequest = new ChangeRequest();
        changeRequest.setTitle(title);
        changeRequest.setStudio(studio);
        changeRequest.setDirector(director);
        changeRequest.setYear(year);
        changeRequest.setDuration(duration);

        return changeRequest;
    }

    private void deleteMovie() {
        System.out.println("--" + DELIMETER
                + "Enter id for deletion:" + DELIMETER);
        long deleteId = SCANNER.nextLong();
        Boolean result = movieService.getMovieWebServicePort().deleteMovie(deleteId);
        System.out.println("Deletion result: " + result);
        System.out.println("--");
        printHelp();
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
                + "3. Add movie" + DELIMETER
                + "4. Delete movie" + DELIMETER
                + "5. Update movie" + DELIMETER
                + "6. Exit" + DELIMETER);
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
