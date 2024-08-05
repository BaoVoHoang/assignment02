package system.book;

import system.exception.BookException;
import system.util.SystemUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookList {
    private List<Book> bestsellers;
    private Set<String> genreSet;
    private Set<String> languageSet;

    public BookList() {
        this.bestsellers = new ArrayList<>();
        this.genreSet = new HashSet<>();
        this.languageSet = new HashSet<>();
    }

    public void loadBookList(String csvFile) throws BookException {
        try (BufferedReader br = new BufferedReader(new FileReader("./src/best-selling-books.csv"))) {
            String line;
            // Skip the header row
            br.readLine();
            int lineNumber = 1; // To track the line number for debugging
            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] values = parseCSVLine(line);
                if (values.length != 6) {
                    throw new BookException("Invalid CSV format on line " + lineNumber);
                }
                try {
                    String name = values[0];
                    String author = values[1];
                    String language = values[2];
                    int published = Integer.parseInt(values[3]);
                    float millionSales = Float.parseFloat(values[4]);
                    String genre = values[5];

                    Book book = new Book(name, author, language, published, millionSales, genre);
                    bestsellers.add(book);
                    genreSet.add(genre);
                    languageSet.add(language);
                } catch (NumberFormatException e) {
                    throw new BookException("Error parsing number on line " + lineNumber + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new BookException("Error reading book list: " + e.getMessage());
        }
    }

    private String[] parseCSVLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(current.toString());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }
        result.add(current.toString());

        return result.toArray(new String[0]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bestsellers.size(); i++) {
            sb.append("Index: ").append(i).append(", ").append(bestsellers.get(i)).append("\n");
        }
        return sb.toString();
    }

    public Book findBookByIndex(int index) throws BookException {
        if (index < 0 || index >= bestsellers.size()) {
            throw new BookException("Index out of bounds");
        }
        return bestsellers.get(index);
    }

    public List<Book> searchInBookList(String searchString) throws BookException {
        if (!SystemUtil.isValid(searchString)) {
            throw new BookException("Invalid search string");
        }
        List<Book> result = new ArrayList<>();
        for (Book book : bestsellers) {
            if (book.toString().toLowerCase().contains(searchString.toLowerCase())) {
                result.add(book);
            }
        }
        if (result.isEmpty()) {
            throw new BookException("No books found matching the search criteria");
        }
        return result;
    }

    public Set<String> getGenreSet() {
        return genreSet;
    }

    public Set<String> getLanguageSet() {
        return languageSet;
    }
}
