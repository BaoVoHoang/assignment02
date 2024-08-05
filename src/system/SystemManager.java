package system;

import system.book.Book;
import system.book.BookList;
import system.exception.BookException;
import system.exception.UserException;
import system.user.User;
import system.user.UserList;
import system.user.UserPlan;

import java.util.Scanner;

public class SystemManager {
    private static BookList bookList = new BookList();
    private static UserList userList = new UserList();
    private static User currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            if (currentUser == null) {
                showMainMenu();
            } else {
                showUserMenu();
            }
            int option = getOption(scanner);

            try {
                if (currentUser == null) {
                    handleMainMenu(option, scanner);
                } else {
                    handleUserMenu(option, currentUser);
                }
            } catch (BookException | UserException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("Application ended.");
    }

    private static void showMainMenu() {
        System.out.println("================================");
        System.out.println("|| Menu - Mini-System: OOP/A2 ||");
        System.out.println("================================");
        System.out.println("1. Load Booklist");
        System.out.println("2. Show Booklist");
        System.out.println("3. Search in the list");
        System.out.println("4. Create user");
        System.out.println("5. Show users");
        System.out.println("6. Save users");
        System.out.println("7. Load users");
        System.out.println("8. Login user");
        System.out.println("9. Exit");
        System.out.print("Choose an option: ");
    }

    private static void showUserMenu() {
        System.out.println("================================");
        System.out.println("|| Menu - User .............. ||");
        System.out.println("================================");
        System.out.println("10. Show all books");
        System.out.println("11. Add book in my list");
        System.out.println("12. Show my booklist");
        System.out.println("13. Read book");
        System.out.println("14. Download book");
        System.out.println("15. Change password");
        System.out.println("16. Logoff");
        System.out.print("Choose an option: ");
    }

    private static int getOption(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.err.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void handleMainMenu(int option, Scanner scanner) throws BookException, UserException {
        switch (option) {
            case 1:
                createBookList(scanner);
                break;
            case 2:
                showBookList();
                break;
            case 3:
                searchInBookList(scanner);
                break;
            case 4:
                createUser(scanner);
                break;
            case 5:
                showUserList();
                break;
            case 6:
                saveUserList(scanner);
                break;
            case 7:
                loadUserList(scanner);
                break;
            case 8:
                currentUser = loginUser(scanner);
                break;
            case 9:
                System.exit(0);
                break;
            default:
                System.err.println("Invalid option. Please try again.");
        }
    }

    private static void handleUserMenu(int option, User currentUser) throws BookException, UserException {
        switch (option) {
            case 10:
                showBookList();
                break;
            case 11:
                addBookInMyList(currentUser);
                break;
            case 12:
                showMyBookList(currentUser);
                break;
            case 13:
                readBook(currentUser);
                break;
            case 14:
                downloadBook(currentUser);
                break;
            case 15:
                changePassword(currentUser);
                break;
            case 16:
                logoff();
                break;
            default:
                System.err.println("Invalid option. Please try again.");
        }
    }

    private static void createBookList(Scanner scanner) throws BookException {
        System.out.print("Name of file to create booklist: ");
        String fileName = scanner.next();
        bookList.loadBookList(fileName);
        System.out.println("Book list created successfully!");
    }

    private static void showBookList() {
        System.out.println(bookList);
    }

    private static void searchInBookList(Scanner scanner) throws BookException {
        System.out.print("Enter search string: ");
        String searchString = scanner.next();
        System.out.println("Search results:");
        for (Book book : bookList.searchInBookList(searchString)) {
            System.out.println(book);
        }
    }

    private static void createUser(Scanner scanner) throws UserException {
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        System.out.print("Enter plan type (trial, standard, vip): ");
        String planType = scanner.next();
        System.out.print("Enter activation status (true/false): ");
        boolean isActive = scanner.nextBoolean();

        UserPlan plan = new UserPlan(UserPlan.PlanType.valueOf(planType.toUpperCase()), isActive);
        User user = new User(email, password, plan);
        userList.addUser(user);

        System.out.println("User created successfully!");
    }

    private static void showUserList() {
        System.out.println(userList);
    }

    private static void saveUserList(Scanner scanner) throws UserException {
        System.out.print("Enter the user file name: ");
        String fileName = scanner.next();
        userList.saveUserList(fileName);
        System.out.println("User list saved successfully!");
    }

    private static void loadUserList(Scanner scanner) throws UserException {
        System.out.print("Enter the user file name: ");
        String fileName = scanner.next();
        userList.loadUserList(fileName);
        System.out.println("User list loaded successfully!");
    }

    private static User loginUser(Scanner scanner) throws UserException {
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        for (User user : userList.getUserList()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("User logged in successfully!");
                return user;
            }
        }
        throw new UserException("Invalid email or password.");
    }

    private static void addBookInMyList(User currentUser) throws BookException, UserException {
        System.out.print("Enter the book index: ");
        Scanner scanner = new Scanner(System.in);
        int bookIndex = scanner.nextInt();
        Book book = bookList.findBookByIndex(bookIndex);
        currentUser.addToBookList(book);
        System.out.println("Book added to your list successfully!");
    }

    private static void showMyBookList(User currentUser) {
        currentUser.displayBookList();
    }

    private static void readBook(User currentUser) throws BookException, UserException {
        System.out.print("Enter the book index: ");
        Scanner scanner = new Scanner(System.in);
        int bookIndex = scanner.nextInt();
        Book book = currentUser.findBookByIndex(bookIndex);
        if (book != null && book.read(currentUser)) {
            System.out.println("Book read successfully!");
        } else {
            throw new BookException("Cannot read the book.");
        }
    }

    private static void downloadBook(User currentUser) throws BookException, UserException {
        System.out.print("Enter the book index: ");
        Scanner scanner = new Scanner(System.in);
        int bookIndex = scanner.nextInt();
        Book book = currentUser.findBookByIndex(bookIndex);
        if (book != null && book.download(currentUser)) {
            System.out.println("Book downloaded successfully!");
        } else {
            throw new BookException("Cannot download the book.");
        }
    }

    private static void changePassword(User currentUser) {
        System.out.print("Enter new password: ");
        Scanner scanner = new Scanner(System.in);
        String newPassword = scanner.next();
        currentUser.setPassword(newPassword);
        System.out.println("Password changed successfully!");
    }

    private static void logoff() {
        currentUser = null;
        System.out.println("User logged off successfully.");
    }
}
