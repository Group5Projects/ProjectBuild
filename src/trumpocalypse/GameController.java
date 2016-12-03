package trumpocalypse;

/**
 * 
 * @author Julian Loftis, Michael Murphy, Robert Gulker
 */

public class GameController {
    
    // Story and Reference to Trumpocalypse GUI
    private Story story;
    private Trumpocalypse jfx;
    
    /**
     * 
     * @param jfx - reference to the GUI so that we can update GUI choices and dialog
     */
    public GameController(Trumpocalypse jfx) {
  
        this.jfx = jfx;
        story = new Story(this);

    }
    
    /**
     * 
     * @param d - update the GUI dialog
     */
    public void updateDialog(String d) {
        jfx.updateDialog(d);
    }

    /**
     * 
     * @return - the story reference
     */
    public Story getStory() {
        return this.story;
    }
    
    /**
     * 
     * @return - the GUI reference
     */
    public Trumpocalypse getJFX() {
        return this.jfx;
    }
}
