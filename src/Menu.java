import java.util.Comparator;
import java.util.Scanner;

public class Menu {
    private static Menu instance;
    private MenuArrayList menu;
    private Order order;

    private Menu() {
        menu = MenuFactory.createMenuArrayList();
        order = new Order();
        menu.loadMenuFromCSV("./data/ass02menu.csv");
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void displayMenu() {
        System.out.println("Restaurant Menu:");
        for (MenuItem item : menu.listMenuItems()) {
            System.out.println(item);
        }
    }

    public void handleUserSelection() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu Options:");
            System.out.println("1. Display Menu");
            System.out.println("2. Add Item to Order");
            System.out.println("3. Remove Item from Order");
            System.out.println("4. Sort Menu");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    displayMenu();
                    break;
                case 2:
                    addItemToOrder(scanner);
                    break;
                case 3:
                    removeItemFromOrder(scanner);
                    break;
                case 4:
                    sortMenu(scanner);
                    break;
                case 5:
                    checkout();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    private void addItemToOrder(Scanner scanner) {
        System.out.print("Enter Item ID to add: ");
        int itemID = scanner.nextInt();
        try {
            MenuItem item = menu.getMenuItemByID(itemID);
            order.addItem(item);
            System.out.println("Item added to order: " + item.getItemName());
        } catch (MenuItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeItemFromOrder(Scanner scanner) {
        System.out.print("Enter Item ID to remove: ");
        int itemID = scanner.nextInt();
        order.removeItem(itemID);
        System.out.println("Item removed from order.");
    }

    private void sortMenu(Scanner scanner) {
        System.out.println("\nSort Options:");
        System.out.println("1. Sort by Item ID");
        System.out.println("2. Sort by Item Name");
        System.out.println("3. Sort by Category");
        System.out.println("4. Sort by Price");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                menu.sortMenu(Comparator.comparingInt(MenuItem::getItemID));
                break;
            case 2:
                menu.sortMenu(Comparator.comparing(MenuItem::getItemName));
                break;
            case 3:
                menu.sortMenu(Comparator.comparing(MenuItem::getCategory));
                break;
            case 4:
                menu.sortMenu(Comparator.comparingDouble(MenuItem::getPrice));
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
        }
    }

    private void checkout() {
        System.out.println("Order Summary:");
        for (MenuItem item : order.listOrderItems()) {
            System.out.println(item);
        }
        System.out.println("Total Price: $" + order.getTotalPrice());
    }

    public static void main(String[] args) {
        Menu menu = Menu.getInstance();
        menu.handleUserSelection();
    }
}
