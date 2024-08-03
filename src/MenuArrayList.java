import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class MenuArrayList implements MenuCollection<MenuItem> {
    private ArrayList<MenuItem> menuItems;

    public MenuArrayList() {
        menuItems = new ArrayList<>();
    }

    public void loadMenuFromCSV(String filePath) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                MenuItem menuItem = new MenuItem(
                        Integer.parseInt(values[0]),
                        values[1],
                        values[2],
                        Double.parseDouble(values[3])
                );
                menuItems.add(menuItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    @Override
    public void removeMenuItem(int itemID) {
        menuItems.removeIf(item -> item.getItemID() == itemID);
    }

    @Override
    public MenuItem getMenuItemByID(int itemID) throws MenuItemNotFoundException {
        for (MenuItem item : menuItems) {
            if (item.getItemID() == itemID) {
                return item;
            }
        }
        throw new MenuItemNotFoundException("Menu item with ID " + itemID + " not found.");
    }

    @Override
    public ArrayList<MenuItem> listMenuItems() {
        return menuItems;
    }

    public void sortMenu(Comparator<MenuItem> comparator) {
        menuItems.sort(comparator);
    }
}
