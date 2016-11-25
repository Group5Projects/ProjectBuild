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
    int subsubprogress;
    
    GameController gc;
    Trumpocalypse jfx;
    String location;
    
    private Character mc = new Character("Julian", true);
    
    public Story(GameController gc, Trumpocalypse jfx) {
        this.progress = 0;
        this.subprogress = 0;
        this.subsubprogress = 0;
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
                        //String prepend = "Stranger pulls out pistol and shoots at you. The bullet hits your arm. Health has been reduced by 25%. You return fire and the stranger runs off.";                        
                        if (subsubprogress == 0) {
                          
                            gc.getJFX().updateDialog("Stranger pulls out pistol and shoots at you. THe bullet hits your arm. Health has been reduced by 25%.");

                            String choice1 = "Heal using medkit";
                            String choice2 = "Return pistol fire";
                            String choice3 = "Run away";
                            String choice4 = "Check Inventory";
                            
                            gc.getJFX().updateChoices("approach turnaround goback heal", choice1, "approach turnaround goback pistol", choice2, "approach turnaround goback runaway", choice3, "approach turnaround goback check", choice4);
                            subsubprogress++;
                        }
                        else {
                            if (location.contains("heal")) {
                                String prepend = "You healed using the medkit and the stranger appeared to run away.";
                                
                                progress++;
                                subprogress = 0;
                                subsubprogress = 0;
                                updateStory(prepend, location, 1);
                            }
                            else if (location.contains("pistol")) {
                                String prepend = "You use your pistol and shoot back.";
                                
                                progress++;
                                subprogress = 0;
                                subsubprogress = 0;
                                updateStory(prepend, location, 2);
                            }
                            else if (location.contains("runaway")) {
                                String prepend = "You runaway and lose him.";
                                
                                progress++;
                                subprogress = 0;
                                subsubprogress = 0;
                                updateStory(prepend, location, 3);
                            }
                            else if (location.contains("check")) {
                                String prepend = "Display Inventory";
                                
                                progress++;
                                subprogress = 0;
                                subsubprogress = 0;
                                updateStory(prepend, location, 4);
                            }
                        }
                        //progress++;
                        //subprogress = 0;
                        //updateStory(prepend, location, 1);
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
    
    public void displayInventory() {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        Scene scene = new Scene(new Group(new Text(25, 25, mc.getInventory().toString())));
        dialog.setScene(scene);
        dialog.setWidth(250);
        dialog.setHeight(250);
        dialog.show();
    }
    
    public void intervene(String location) {
        
        if (subprogress == 0) {
            gc.getJFX().updateDialog("Mind your own business!");
            
            String choice1 = "You both need to stop!";
            String choice2 = "Awe, cant we all just get along?";
            String choice3 = "Were you dropped as a baby?";
            String choice4 = "Indicate you supported Trump.";
            gc.getJFX().updateChoices("intervene stop", choice1, "intervene along", choice2, "intervene baby", choice3, "intervene trump", choice4);
            
            subprogress++;
        }
        else {
            if (location.contains("stop")) {
                if (subprogress == 1) {
                    gc.getJFX().updateDialog("lol? seriously? weak? This wont stop, I'm killing this thief!");

                    String choice1 = "Grab the man?";
                    String choice2 = "Yell: Hey! aint nobody got time for this!";
                    String choice3 = "...Is there a fire?";
                    String choice4 = "If someone throws bread at you....do you duck?";
                    
                    System.out.println("Progress: " + progress);
                    System.out.println("Subprogress: " + subprogress);
                    System.out.println("Location: " + location);
                    
                    subprogress++;
                    gc.getJFX().updateChoices("intervene stop grab", choice1, "intervene stop yell", choice2, "intervene stop fire", choice3, "intervene stop duck", choice4);
                }
                else {
                    System.out.println("Progress: " + progress);
                    System.out.println("Subprogress: " + subprogress);
                    System.out.println("Subsubprogress: " + subsubprogress);
                    System.out.println("Location: " + location);
                    
                    if (location.contains("grab")) {
                        
                        String prepend = "You grabbed the man. He pulls out a knife and stabs you. You run away from the scene.";
                        progress++;
                        subprogress = 0;
                        
                        updateStory(prepend, location, 1);
                    }
                    else if (location.contains("yell")) {
                        System.out.println("Yes it does");
                        if (subsubprogress == 0) {
                            gc.getJFX().updateDialog("The angry man grabs your throat and begins choking you... Mr.funny, I presume?");
                            
                            String choice1 = "Please stop?";
                            String choice2 = "Gouge his eyes out!";
                            String choice3 = "Knee to the groin!";
                            String choice4 = "Snot strike!";
                            
                            gc.getJFX().updateChoices("intervene stop yell stop", choice1, "intervene stop yell gouge", choice2, "intervene stop yell knee", choice3, "intervene stop yell snot", choice4);
                            subsubprogress++;
                        }
                        else {
                            if (location.contains("stop")) {
                                String prepend = "You plead for the man to stop, you hear faint chuckling as your vision goes dark, and your journey ends.";
                                
                                progress++;
                                subprogress = 0;
                                subsubprogress = 0;
                                
                                updateStory(prepend, location, 1);
                            }
                            else if (location.contains("gouge")) {
                                String prepend = "You claw at his eyes, like a fat fingered infant grabbing playdough. The man goes down like a hobo on a ham sandwich. So much blood!";
                                
                                progress++;
                                subprogress = 0;
                                subsubprogress = 0;
                                
                                updateStory(prepend, location, 2);
                            }
                            else if (location.contains("knee")) {
                                String prepend = "You knee him in the groin!";
                                
                                progress++;
                                subprogress = 0;
                                subsubprogress = 0;
                                
                                updateStory(prepend, location, 3);
                            }
                            else if (location.contains("snot")) {
                                String prepend = "The sinus infection you have, has paid off. You spew forth a small meteric crap ton of green sludge from your sinus canals; smothering the mans face yellowish green mucus. The man gags and lets go of you, you can hear him vomiting as you flee.";
                                
                                progress++;
                                subprogress = 0;
                                subsubprogress = 0;
                                
                                updateStory(prepend, location, 4);
                            }
                        }
                        
                    }
                    else if (location.contains("fire")) {
                        String prepend = "You see a fire and run away. ";
                        progress++;
                        subprogress = 0;
                        
                        updateStory(prepend, location, 1);
                    }
                    else if (location.contains("duck")) {
                        String prepend = "You grabbed the man. He pulls out a knife and stabs you. You run away from the scene.";
                        progress++;
                        subprogress = 0;
                        
                        updateStory(prepend, location, 1);
                    }
                }
            }
            else if (location.contains("along")) {
                
            }
            else if (location.contains("baby")) {
                
            }
            else if (location.contains("trump")) {
                
            }
        }
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

                String choice1 = "intervene";
                String choice2 = "Walk calmly up to them";
                String choice3 = "Ignore and keep walking";
                String choice4 = "Check Inventory";

                gc.getJFX().updateChoices("intervene", choice1, "walkCalmly", choice2, "ignore", choice3, "check", choice4);
                progress++;
                
        }
        else if (progress == 2) {

            if (location.contains("intervene")) {
                intervene(location);
            }
            else if (location.contains("along")) {
                 //along
            }
            else if (location.contains("baby")) {
                //ignoreArguing(location);
            }
            else if (location.contains("trump")) {
               gc.getJFX().updateDialog("Hey, I voted for Trump buddy!");
               
            }
            
        }
        else if (progress == 3){
            
            System.out.println("Progress #3");
            gc.getJFX().updateDialog(prepend + "You come across a bridge.");
            
            String choice1 = "Choice 1";
            String choice2 = "Choice 2";
            String choice3 = "Choice 3";
            String choice4 = "Choice 4";
            
            gc.getJFX().updateChoices("choice1", choice1, "choice2", choice2, "choice3", choice3, "choice4", choice4);
            
//            if (location.contains("stop")) {
//                       //Murphy Story Line Update. 
//                        
//                        
//                        progress++;
//            }
//            else if (location.contains("")) {
//
//            }
//            else if (location.contains("Grab")){
//                gc.getJFX().updateDialog("You grab the man, and he throat punches you, rendering you unconscious.");
//            }
            
        }
   
    }
        
        
    }
    
    

      

