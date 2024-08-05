package system.user;

import system.book.Book;
import system.exception.UserException;
import system.util.SystemUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String password;
    private List<Book> bookList;
    private UserPlan plan;

    public User(String email, String password, UserPlan plan) throws UserException {
        if (SystemUtil.isValid(email) && SystemUtil.isValid(password)) {
            this.email = email;
            this.password = password;
            this.plan = plan;
            this.bookList = new ArrayList<>();
        } else {
            throw new UserException("Invalid user credentials.");
        }
    }

    public static User createUser(String... params) throws UserException {
        if (params.length != 4) {
            throw new IllegalArgumentException("Invalid number of parameters");
        }
        String email = params[0];
        String password = params[1];
        UserPlan.PlanType planType = UserPlan.PlanType.valueOf(params[2].toUpperCase());
        boolean isActive = Boolean.parseBoolean(params[3]);
        UserPlan plan = new UserPlan(planType, isActive);

        return new User(email, password, plan);
    }

    public boolean isActive() {
        return plan.isActive();
    }

    public UserPlan getPlan() {
        return plan;
    }

    public void addToBookList(Book book) throws UserException {
        if (isActive()) {
            bookList.add(book);
        } else {
            throw new UserException("User plan is not active.");
        }
    }

    public Book findBookByIndex(int index) throws UserException {
        if (index < 0 || index >= bookList.size()) {
            throw new UserException("Book index out of bounds");
        }
        return bookList.get(index);
    }

    public void loadBookList() throws UserException {
        try (BufferedReader br = new BufferedReader(new FileReader(email + "_books.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = SystemUtil.lineReader(line);
                if (values.length != 6) {
                    throw new UserException("Invalid book data format");
                }
                String name = values[0];
                String author = values[1];
                String language = values[2];
                String genre = values[3];
                int published = Integer.parseInt(values[4]);
                float millionSales = Float.parseFloat(values[5]);

                Book book = new Book(name, author, language, published, millionSales,genre);
                bookList.add(book);
            }
        } catch (IOException e) {
            throw new UserException("Error reading book list: " + e.getMessage());
        }
    }

    public void saveBookList() throws UserException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(email + "_books.txt"))) {
            for (Book book : bookList) {
                bw.write(book.getName() + "," + book.getAuthor() + "," + book.getLanguage() + "," +
                        book.getGenre() + "," + book.getPublished() + "," + book.getMillionSales());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new UserException("Error writing book list: " + e.getMessage());
        }
    }

    public void displayBookList() {
        if (bookList.isEmpty()) {
            System.out.println("No books in your list.");
        } else {
            for (int i = 0; i < bookList.size(); i++) {
                System.out.println("Index: " + i + ", " + bookList.get(i));
            }
        }
    }

    public void setPassword(String password) {
        if (SystemUtil.isValid(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Email: " + email + ", Plan: " + plan;
    }
}
