package trumpocalypse;

/**
 * 
 * @author Julian Loftis, Michael Murphy, Robert Gulker
 */

public class Character {
    
    // Money and Health
    private double currency = 0.00;
    private double health = 0;
    
    // Character Inventory
    private Inventory inv = new Inventory();
    
    // Character Name
    private String name;
    
    // Determines if character is main character or not
    private boolean isMain;
    
    /**
     * 
     * @param n - the name of the character
     * @param b - boolean if user is main or not
     */
    public Character(String n, boolean b) {
        this.name = n;
        this.health = 100.00;
        this.isMain = b;
    }
    
    /**
     * 
     * @param c - amount of currency to add
     */
    public void addCurrency(double c) {
        this.currency += c;
    }
    
    /**
     * 
     * @return - returns the currency
     */
    public double getCurrency() {
        return this.currency;
    }
    
    /**
     * 
     * @param h - adds amount of health to character health
     */
    public void addHealth(double h) {
        if (h + this.health > 100) {
            this.health = 100;
        }
        else {
            this.health += h;
        }
    }
    
    /**
     * 
     * @param h - the amount to reduce health by
     */
    public void reduceHealth(double h) {
        this.health -= h;
    }
    
    // Clears the characters's inventory
    public void clearInventory() {
        this.inv = null;
        this.inv = new Inventory();
    }
    
    // Clears the character's currency
    public void clearCurrency() {
        this.currency = 0.00;
    }
    
    /**
     * 
     * @return - the character's health
     */
    public double getHealth() {
        return this.health;
    }
    
    /**
     * 
     * @return - the character's name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * 
     * @return - if character is main character or not
     */
    public boolean getMain() {
        return this.isMain;
    }
    
    /**
     * 
     * @return - the character's inventory
     */
    public Inventory getInventory() {
        return inv;
    }
    
    // Retrieves character info, used for testing
    public void getCharacterInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("Health: " + this.health);
        System.out.println("Main Character: " + this.isMain);
        System.out.println("Currency: " + this.currency);
        System.out.println("--------------------------");
    }
}
