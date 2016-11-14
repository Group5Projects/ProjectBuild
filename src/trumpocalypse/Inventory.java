package trumpocalypse;

import java.util.ArrayList;

/**
 * 
 * @author Julian Loftis
 */

public class Inventory {
    private ArrayList<Item> inv;
    
    public Inventory() {
        inv = new ArrayList<Item>();
    }
    
    public void addItem(Item i) {
        inv.add(i);
    }
    public void addItems(Item... is) {
        for (Item i: is) {
            if (inv.contains(i.getName())) {
                int index = inv.indexOf(i);
                inv.get(index).addQuantity(1);
            }
            else {
                inv.add(i);
            }
        }
    }
    public void removeItem(Item i) {
        inv.remove(i);
    }
    public ArrayList<Item> getInventory() {
        return this.inv;
    } 
    public void displayInventory() {
        for (Item i: this.inv) {
            System.out.println("Name: " + i.getName());
            System.out.println("Type: " + i.getType());
            System.out.println("Quantity: " + i.getQuantity() + "\n");
        }
    }
}
