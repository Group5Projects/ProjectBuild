package trumpocalypse;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 * @author Julian Loftis
 */

class Story {
    
    int progress;
    int subprogress;
    
    GameController gc;
    Trumpocalypse jfx;
    String location;
    private Character mc = new Character("Julian", true);
    
    public Story(GameController gc, Trumpocalypse jfx) {
        this.progress = 0;
        this.subprogress = 0;
        this.gc = gc;
        this.jfx = jfx;
        this.location = "start";
    }
    
    
    public void searchVehicle() {
        gc.getJFX().updateDialog(location);
    }
    
    /*
    
    CHAPTER ONE METHODS
     - approachStranger
     - inspectShoppingCart

    */
    
    public void approachStranger(String location) {
        if (subprogress == 0) {
            gc.getJFX().updateDialog("Hey, hows it going? (Stranger) Not too well, can't seem to find any water anywhere. You wouldn't have any would you?");
            gc.getJFX().getChoicePane().getChildren();

            String choice1 = "Give stranger water";
            String choice2 = "Tell stranger you don't have any";
            String choice3 = "Turn around and walk away";
            String choice4 = "Check Inventory";

            gc.getJFX().updateChoices("approach givewater", choice1, "approach nowater", choice2, "approach turnaround", choice3, "check", choice4);
            subprogress++;
        }
        else {

            if (location.contains("givewater")) {
                //Givewater
                String prepend = "Thank you for the water! I really needed this. Here is some money in return. Good luck with your trip.";
                progress++;
                subprogress = 0;
                
                updateStory(prepend, location, 1);
            }
            else if (location.contains("nowater")) {
                // Nowater
                String prepend = "Hmmm... Okay. Have a good day then.";
                progress++;
                subprogress = 0;
                
                updateStory(prepend, location, 2);
            }
            else if (location.contains("turnaround")) {
                // Turnaround
                if (subprogress == 1) {
                    gc.getJFX().updateDialog("Hey! I was talking to you. Get back over here.");

                    String choice1 = "Turn around and go back to stranger";
                    String choice2 = "Pull pistol out to confront stranger";
                    String choice3 = "Run Away";
                    String choice4 = "Check Inventory";
                    
                    gc.getJFX().updateChoices("approach turnaround goback", choice1, "approach turnaround pistol", choice2, "approach turnaround runaway", choice3, "check", choice4);
                    subprogress++;
                }
                else {
                    if (location.contains("goback")) {
                        String prepend = "Stranger pulls out pistol and shoots at you. The bullet hits your arm. Health has been reduced by 25%. You return fire and the stranger runs off.";                        
                        progress++;
                        subprogress = 0;
                        updateStory(prepend, location, 1);
                    }
                    else if (location.contains("pistol")) {
                        String prepend = "'Hold up man, I don't want any trouble. I'm out of here.' (Stranger runs off).";
                        progress++;
                        subprogress = 0;
                        updateStory(prepend, location, 2);
                    }
                    else if (location.contains("runaway")) {
                        String prepend = "The stranger begins to shoot in your direction but misses. You found a hiding place and lost him.";
                        progress++;
                        subprogress = 0;
                        updateStory(prepend, location, 3);
                    }
                    else {
                        // Check Inventory
                        displayInventory();
                    }
                }
            }
            else {
                // Inventory
                displayInventory();
            }
        }
    }
    public void inspectShoppingCart(String location) {
        if (subprogress == 0) {
            String prepend = "You walk up and begin to search the shopping cart. Inside you find a bottle of water, some crackers, and duct tape. These items have been added to your inventory.\n ";
            progress++;
            subprogress = 0;
            
            Item water = new Item("Bottle of Water", "Food/Water", 1);
            Item crackers = new Item("Crackers", "Food/Water", 1);
            Item ductTape = new Item("Duct Tape", "Tools", 1);
            
            mc.getInventory().addItems(water,crackers,ductTape);
            
            updateStory(prepend, "inspect", 3);
        }
    }
    
    /*
    
    END CHAPTER ONE METHODS
    
    */
    
    /* 
    
    CHAPTER 2 METHODS
    - interveneArguing
    - walkCalmly
    
    */
    
    public void interveneArguing(String location) {
        if (subprogress == 0) {
            
            gc.getJFX().updateDialog("One man pulls out a gun and is threatening to shoot the other.");
            
            String choice1 = "Pull out pistol";
            String choice2 = "Pull out pistol and shoot";
            String choice3 = "See if you can de-escalate the situation";
            String choice4 = "Check Inventory";
            
            gc.getJFX().updateChoices("pistol1", choice1, "pistol2", choice2, "deescalateSituation", choice3, "check", choice4);
            subprogress++;
        }
        else {
            
            if (location.contains("pistol1")) {
                
            }
            else if (location.contains("pistol2")) {
                
            }
            else if (location.contains("deescalateSituation")) {
                
            }
            else {
                displayInventory();
            }
        }
    }
   
    
    public void displayInventory() {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        Scene scene = new Scene(new Group(new Text(25, 25, mc.getInventory().toString())));
        dialog.setScene(scene);
        dialog.setWidth(250);
        dialog.setHeight(250);
        dialog.show();
    }
    
    public void updateStory(String prepend, String location, int choice) {
        
        if (progress == 0) {
            
            System.out.println("Progress: " + progress);
            System.out.println("Subprogress: " + subprogress);
            System.out.println("Location: " + location);
            
            if (location.contains("keepwalking")) {
                // Proceed
                progress++;
                updateStory("", "keepwalking", 1);
            }
                
            if (location.contains("approach")) {
                approachStranger(location);
            }
            
            if (location.contains("inspect")) {
                inspectShoppingCart(location);
            }
            
            if (location.contains("check")) {
                System.out.println("here");
                displayInventory();
            }
            
        }
        else if (progress == 1) {
           
                gc.getJFX().updateDialog(prepend + "You come across two people arguing in the road. One is accusing the other of stealing something from the other.");

                String choice1 = "Intervene";
                String choice2 = "Walk calmly up to them";
                String choice3 = "Ignore and keep walking";
                String choice4 = "Check Inventory";

                gc.getJFX().updateChoices("intervene", choice1, "walkCalmly", choice2, "ignore", choice3, "check", choice4);
                progress++;
                
        }
        else if (progress == 2) {

            if (location.contains("intervene")) {
                //interveneArguing(location);
            }
            else if (location.contains("walkCalmly")) {
                //walkCalmly(location);
            }
            else if (location.contains("ignore")) {
                //ignoreArguing(location);
            }
            else if (location.contains("check")) {
               // Check Inventory
               displayInventory();
            }
            
        }
        

    }
        
      
}
