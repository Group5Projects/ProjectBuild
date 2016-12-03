package trumpocalypse;

/**
 *
 * @author Julian Loftis, Michael Murphy, Robert Gulker
 * 
 */

public class Item {
    
    // Name, Type, and Quantity of the Item
    private String name;
    private String type;
    private int quantity;
    
    /**
     * 
     * @param n - the name of the item
     * @param t - the type of the item
     * @param q - the quantity of item to add
     */
    public Item(String n, String t, int q) {
        this.name = n;
        this.type = t;
        this.quantity = q;
    }
    
    /**
     * 
     * @return - the name of the item 
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * 
     * @return - the type of the item
     */
    public String getType() {
        return this.type;
    }
    
    /**
     * 
     * @param q - increase the quantity of item by this much
     */
    public void addQuantity(int q) {
        this.quantity += q;
    }
    
    /**
     * 
     * @return - the quantity of the item
     */
    public int getQuantity() {
        return this.quantity;
    }
    
}
