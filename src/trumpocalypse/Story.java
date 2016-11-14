package trumpocalypse;

/**
 * 
 * @author Julian Loftis
 */

class Story {
    
    int progress;
    GameController gc;
    Trumpocalypse jfx;
    
    public Story(GameController gc, Trumpocalypse jfx) {
        progress = 0;
        this.gc = gc;
        this.jfx = jfx;
    }
    
    public void updateStory(int choice) {
        
            switch (choice) {
                case 1: 
                    gc.getJFX().updateDialog("You have chosen to pick up the gun. It has been added to your inventory.");
                    gc.getJFX().getChoicePane().getChildren();
                    String choice1 = "Proceed walking";
                    String choice2 = "Approach stranger on side of road";
                    String choice3 = "Look for more items";
                    String choice4 = "Display Inventory";
                    gc.getJFX().updateChoices(choice1, choice2, choice3, choice4);
                    progress++;
                    break;
                case 2:
                    gc.getJFX().updateDialog("You have chosen to keep walking. Good choice.");
                    progress++;
                    break;
                case 3:
                    gc.getJFX().updateDialog("Searching vehicle now. Some water was found inside the vehicle. It has been added to your inventory.");
                    progress++;
                    break;
                case 4: 
                    gc.getJFX().updateDialog("Suddenly an angry feminisht clinton supporter jumps out from the dark, and kills you for having your own opinion ");
                    progress++;
                    break;  
                default:
                    gc.getJFX().updateDialog("Error");
                    break;
            }
    }
        
      
}
