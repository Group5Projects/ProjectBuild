package trumpocalypse;

import java.util.ArrayList;

/**
 * 
 * @author Julian Loftis, Michael Murphy, Robert Gulker
 */

public class Inventory {
    
    // ArrayList of Items and Size of Inventory
    private ArrayList<Item> inv;
    private int size;
    
    public Inventory() {
        inv = new ArrayList<Item>();
    }
    
    /**
     * 
     * @param i - item to be added
     */
    public void addItem(Item i) {
        inv.add(i);
        size++;
    }
    
    /**
     * 
     * @param is - items to be added
     */
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
    
    /**
     * 
     * @return - the size of the inventory
     */
    public int getSize() {
        return this.size;
    }
    
    /**
     * 
     * @param i - item to be removed
     * @return - the item that was removed
     */
    public Item removeItem(Item i) {
        Item n = i;
        inv.remove(i);
        return n;
    }
    
    /**
     * 
     * @return - the inventory
     */
    public ArrayList<Item> getInventory() {
        return this.inv;
    } 
    
    // Displays the user inventory, used for testing
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
