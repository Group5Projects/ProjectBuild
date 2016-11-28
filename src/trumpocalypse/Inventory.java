package trumpocalypse;

import java.util.ArrayList;

/**
 * 
 * @author Julian Loftis
 */

public class Inventory {
    private ArrayList<Item> inv;
    private int size;
    
    public Inventory() {
        inv = new ArrayList<Item>();
    }
    
    public void addItem(Item i) {
        inv.add(i);
        size++;
    }
    public void addItems(Item... is) {
        for (Item i: is) {
            if (inv.contains(i.getName())) {
                int index = inv.indexOf(i);
                inv.get(index).addQuantity(1);
            }
            else {
                inv.add(i);
                size++;
            }
        }
    }
    public int getSize() {
        return this.size;
    }
    
    public Item removeItem(Item i) {
        Item n = i;
        inv.remove(i);
        return n;
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
    
    @Override
    public String toString() {
        String result = "";
        
        if (size > 0) {
            for (Item i: this.inv) {
                result += "\nName: " + i.getName() + "\n";
                result += "Type: " + i.getType() + "\n";
                result += "Quantity: " + i.getQuantity() + "\n";
            }
        }
        else {
            result += "No items in inventory!";
        }
        
        return result;
    }
}
