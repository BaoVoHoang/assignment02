import java.util.ArrayList;

public class Order {
    private ArrayList<MenuItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(int itemID) {
        items.removeIf(item -> item.getItemID() == itemID);
    }

    public double getTotalPrice() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public ArrayList<MenuItem> listOrderItems() {
        return items;
    }
}
