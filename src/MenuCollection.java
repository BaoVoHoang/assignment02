import java.util.ArrayList;

public interface MenuCollection<T> {
    void addMenuItem(T item);
    void removeMenuItem(int itemID);
    T getMenuItemByID(int itemID) throws MenuItemNotFoundException;
    ArrayList<T> listMenuItems();
}
