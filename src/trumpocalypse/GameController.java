package trumpocalypse;

/**
 * 
 * @author Julian Loftis
 */

public class GameController {
    
    private Story story;
    private Trumpocalypse jfx;
    
    public GameController(Trumpocalypse jfx) {
        //mc.getCharacterInfo();
        
        this.jfx = jfx;
        story = new Story(this, jfx);
        
//        Item i1 = new Item("Pistol", "Shoots", 1);
//        Item i2 = new Item("Medkit", "Heals", 5);
//        Item i3 = new Item("Medkit", "Heals", 1);
//        
//        mc.getInventory().addItems(pistol, medkit, medkit2);
//        mc.getInventory().displayInventory();
    }
    
    public void updateDialog(String d) {
        jfx.updateDialog(d);
    }

    
    public Story getStory() {
        return this.story;
    }
    
    public Trumpocalypse getJFX() {
        return this.jfx;
    }
}
