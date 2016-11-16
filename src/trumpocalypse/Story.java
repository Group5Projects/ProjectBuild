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
                        switch (choice) {
                            case 1: 
                                gc.getJFX().updateDialog("You continue walking along Interstate 10. After a few hours of walking, are are now outside the "
                                        + "city limits of New Orleans, LA. As you enter the city, you are approached by a stranger carrying a backpack. You're "
                                        + "not exactly sure what he wants.");
                                gc.getJFX().getChoicePane().getChildren();
                                String choice1a = "Try to Walk Past the Stranger";
                                String choice2a = "Approach the Stranger";
                                String choice3a = "Attack the Stranger";
                                String choice4a = "Check Inventory";
                                gc.getJFX().updateChoices(choice1a, choice2a, choice3a, choice4a);
                                progress++;
                                break;
                            case 2:
                                gc.getJFX().updateDialog("'Hello ther stranger. The name's Fred. What can I do for you?'");
                                gc.getJFX().getChoicePane().getChildren();
                                String choice5a = "'I'm trying to get to Arizona.'";
                                String choice6a = "'What's with the outfit? Halloween's over.'";
                                String choice7a = "'You've got a lot of stuff in that cart, I want it.'";
                                String choice8a = "Check Inventory";
                                gc.getJFX().updateChoices(choice5a, choice6a, choice7a, choice8a);
                                progress++;
                                break;
                            case 3:
                                gc.getJFX().updateDialog("You attempt to search the cart, but are abruptly stopped by the stranger. 'Whoa there fella! "
                                        + "What are you doing going through my stuff?'");
                                gc.getJFX().getChoicePane().getChildren();
                                String choice9a = "'I'm sorry, I didn't know this was yours.'";
                                String choice10a = "'Oh relax! This stuff is junk anyway.'";
                                String choice11a = "'Stay out of my way, or be sorry!'";
                                String choice12a = "Check Inventory";
                                gc.getJFX().updateChoices(choice9a, choice10a, choice11a, choice12a);
                                progress++;
                                break;
                            case 4: 
                                gc.getJFX().updateDialog("Inventory");
                                gc.getJFX().getChoicePane().getChildren();
                                String choice13a = "Display Weapons";
                                String choice14a = "Display Med-Kits";
                                String choice15a = "Display Currency";
                                String choice16a = "Go Back";
                                gc.getJFX().updateChoices(choice13a, choice14a, choice15a, choice16a);
                                break;  
                            default:
                                gc.getJFX().updateDialog("Error");
                                break;
                        }
                    break;
                    
                case 2:
                    gc.getJFX().updateDialog("There are no useful items within the truck. You attempt to start the truck but it will not.");
                    gc.getJFX().getChoicePane().getChildren();
                    String choice5 = "Try Starting the Truck Again";
                    String choice6 = "Better Start Walking";
                    String choice7 = "Check Wooden Crate";
                    String choice8 = "Check Inventory";
                    gc.getJFX().updateChoices(choice5, choice6, choice7, choice8);
                    progress++;
                    break;
                case 3:
                    gc.getJFX().updateDialog("You search the crate and you find a handgun within the crate");
                    gc.getJFX().getChoicePane().getChildren();
                    String choice9 = "Add Handgun to Inventory";
                    String choice10 = "Inspect Pickup Truck";
                    String choice11 = "Start Walking";
                    String choice12 = "Check Inventory";
                    gc.getJFX().updateChoices(choice9, choice10, choice11, choice12);
                    progress++;
                    break;
                case 4: 
                    gc.getJFX().updateDialog("Inventory");
                    gc.getJFX().getChoicePane().getChildren();
                    String choice13 = "Display Weapons";
                    String choice14 = "Display Med-Kits";
                    String choice15 = "Display Currency";
                    String choice16 = "Go Back";
                    gc.getJFX().updateChoices(choice13, choice14, choice15, choice16);
                    break;  
                default:
                    gc.getJFX().updateDialog("Error");
                    break;
            }
    }
        
      
}
