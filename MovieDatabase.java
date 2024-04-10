
import java.util.ArrayList;
import java.util.Scanner;

public class MovieDatabase {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Movie> movies = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    searchMovie();
                    break;
                case 3:
                    displayDetails();
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nMovie Database Menu");
        System.out.println("1. Add Movie");
        System.out.println("2. Search Movies");
        System.out.println("3. Display Movie Details");
        System.out.println("4. Exit");
    }

    private static int getChoice() {
        System.out.print("Enter your choice: ");
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // Clear the buffer
            choice = -1;
        }
        return choice;
    }

    private static void addMovie() {
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        System.out.print("Enter release year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        System.out.print("Enter director: ");
        String director = scanner.nextLine();

        Movie movie = new Movie(title, year, director);
        movies.add(movie);
        System.out.println(title + " added to the database!");
    }

    private static void searchMovie() {
        System.out.print("Enter search term (title, year, or director): ");
        String searchTerm = scanner.nextLine().toLowerCase();
        ArrayList<Movie> foundMovies = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(searchTerm) ||
                    String.valueOf(movie.getYear()).contains(searchTerm) ||
                    movie.getDirector().toLowerCase().contains(searchTerm)) {
                foundMovies.add(movie);
            }
        }

        if (foundMovies.isEmpty()) {
            System.out.println("No movies found matching that term.");
        } else {
            System.out.println("Found movies:");
            for (Movie movie : foundMovies) {
                System.out.println("\t- " + movie);
            }
        }
    }

    private static void displayDetails() {
        if (movies.isEmpty()) {
            System.out.println("No movies found in the database yet!");
            return;
        }

        System.out.println("Select a movie to view details:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i).getTitle());
        }

        System.out.print("Enter movie number: ");
        int choice;
        try {
            choice = scanner.nextInt() - 1;
        } catch (Exception e) {
            scanner.nextLine(); // Clear the buffer
            choice = -1;
        }

        if (choice >= 0 && choice < movies.size()) {
            Movie movie = movies.get(choice);
            System.out.println(movie);
        } else {
            System.out.println("Invalid choice!");
        }
    }
}

 class MovieDataBasic {
    private String title;
    private int year;
    private String director;

    public MovieDataBasic(String title, int year, String director) {
        this.title = title;
        this.year = year;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Year: " + year + ", Director: " + director;
    }
}