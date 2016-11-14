package trumpocalypse;

/**
 *
 * @author Julian Loftis
 * 
 */

public class Item {
    private String name;
    private String type;
    private int quantity;
    
    public Item(String n, String t, int q) {
        this.name = n;
        this.type = t;
        this.quantity = q;
    }
    
    public String getName() {
        return this.name;
    }
    public String getType() {
        return this.type;
    }
    public void addQuantity(int q) {
        this.quantity += q;
    }
    public int getQuantity() {
        return this.quantity;
    }
}
