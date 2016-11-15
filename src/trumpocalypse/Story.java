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
                    gc.getJFX().updateDialog("You begin walking West-bound on Interstate 10. After a few hours of walking, you see a stranger "
                            + "sitting on the right-hand side of the road. He appears to be homeless, down on his luck, and not much of a threat "
                            + "to you. Next to the stranger, you see a shopping cart filled with an array of different items.");
                    gc.getJFX().getChoicePane().getChildren();
                    String choice1 = "Keep walking";
                    String choice2 = "Approach the Stranger";
                    String choice3 = "Inspect the Shopping Cart";
                    String choice4 = "Check Inventory";
                    gc.getJFX().updateChoices(choice1, choice2, choice3, choice4);
                    progress++;
                    break;
                case 2:
                    gc.getJFX().updateDialog("There are no useful items within the truck. You attempt to start the truck but it will not"
                            + "turn over,"
                            + "items to be found in the truck");
                    gc.getJFX().getChoicePane().getChildren();
                    String choice5 = "Try Starting the Truck Again";
                    String choice6 = "Better Start Walking";
                    String choice7 = "Check Wooden Crate";
                    String choice8 = "Check Inventory";
                    gc.getJFX().updateChoices(choice5, choice6, choice7, choice8);
                    progress++;
                    break;
                case 3:
                    gc.getJFX().updateDialog("Searching vehicle now. Some water was found inside the vehicle. It has been added to your inventory.");
                    progress++;
                    break;
                case 4: 
                    gc.getJFX().updateDialog("Suddenly an angry millenial clinton supporter jumps out from the dark, and kills you for having your own opinion ");
                    progress++;
                    break;  
                default:
                    gc.getJFX().updateDialog("Error");
                    break;
            }
    }
        
      
}
