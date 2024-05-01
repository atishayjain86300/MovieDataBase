import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MoviesDatabase extends JFrame {
    private JTextField titleField, yearField, directorField, searchField;
    private JButton addButton, searchButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private ArrayList<Movie> movies;

    public MoviesDatabase() {
        setTitle("Movies Database");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        movies = new ArrayList<>();

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField(15);
        JLabel yearLabel = new JLabel("Year:");
        yearField = new JTextField(5);
        JLabel directorLabel = new JLabel("Director:");
        directorField = new JTextField(15);
        addButton = new JButton("Add Movie");
        topPanel.add(titleLabel);
        topPanel.add(titleField);
        topPanel.add(yearLabel);
        topPanel.add(yearField);
        topPanel.add(directorLabel);
        topPanel.add(directorField);
        topPanel.add(addButton);

        JPanel searchPanel = new JPanel(new FlowLayout());
        JLabel searchLabel = new JLabel("Search:");
        searchField = new JTextField(15);
        searchButton = new JButton("Search");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel centerPanel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(new String[]{"Title", "Year", "Director"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMovie();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMovie();
            }
        });

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(searchPanel, BorderLayout.CENTER);
        contentPane.add(centerPanel, BorderLayout.SOUTH);
    }

    private void addMovie() {
        String title = titleField.getText();
        int year = Integer.parseInt(yearField.getText());
        String director = directorField.getText();
        Movie movie = new Movie(title, year, director);
        movies.add(movie);
        tableModel.addRow(new Object[]{title, year, director});
        clearFields();
    }

    private void searchMovie() {
        String searchTerm = searchField.getText().toLowerCase();
        tableModel.setRowCount(0); // Clear table
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(searchTerm) ||
                    String.valueOf(movie.getYear()).contains(searchTerm) ||
                    movie.getDirector().toLowerCase().contains(searchTerm)) {
                tableModel.addRow(new Object[]{movie.getTitle(), movie.getYear(), movie.getDirector()});
            }
        }
    }

    private void clearFields() {
        titleField.setText("");
        yearField.setText("");
        directorField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MoviesDatabase().setVisible(true);
            }
        });
    }
}

class Movie {
    private String title;
    private int year;
    private String director;

    public Movie(String title, int year, String director) {
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
}
