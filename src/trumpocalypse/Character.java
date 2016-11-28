package trumpocalypse;

/**
 * 
 * @author Julian Loftis
 */

public class Character {
    
    private double currency = 0.00;
    private double health = 0;
    private Inventory inv = new Inventory();
    private String name;
    private boolean isMain;
    
    public Character(String n, boolean b) {
        this.name = n;
        this.health = 25.00;
        this.isMain = b;
    }
    
    public void addCurrency(double c) {
        this.currency += c;
    }
    public double getCurrency() {
        return this.currency;
    }
    
    public void addHealth(double h) {
        if (h + this.health > 100) {
            this.health = 100;
        }
        else {
            this.health += h;
        }
    }
    
    public void reduceHealth(double h) {
        this.health -= h;
    }
    
    public void clearInventory() {
        this.inv = null;
        this.inv = new Inventory();
    }
    
    public void clearCurrency() {
        this.currency = 0.00;
    }
    
    public double getHealth() {
        return this.health;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean getMain() {
        return this.isMain;
    }
    
    public Inventory getInventory() {
        return inv;
    }
    public void getCharacterInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("Health: " + this.health);
        System.out.println("Main Character: " + this.isMain);
        System.out.println("Currency: " + this.currency);
        System.out.println("--------------------------");
    }
}
