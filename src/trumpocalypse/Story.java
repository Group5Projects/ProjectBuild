package trumpocalypse;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Julian Loftis, Michael Murphy, Robert Gulker
 */
class Story {

    // Used to locate position in the storyline
    int progress;
    int subprogress;
    int subsubprogress;

    GameController gc;
    String location;

    // The main character and user of the game
    private Character mc = new Character("User", true);

    // C1 Mugger
    private Character c1Mugger;
    private Item c1MuggerPistol;

    // C2 Fire Person
    private Character c2FirePerson;
    private Item c2FirePersonMedkit;

    // C4 Bench Person
    private Character c4BenchPerson;
    
    // C4 Fight Person
    private Character c4FightPerson;
    
    /**
     *
     * @param gc - reference to the GameController so that we can update GUI
     * choices and dialog
     */
    public Story(GameController gc) {

        // Keeps track of the progress of the story, progress = main story, subprogress = choice within progress, subsubprogress = choice underneath subprogress
        this.progress = 0;
        this.subprogress = 0;
        this.subsubprogress = 0;

        // Reference to the GameController in order to update the GUI dialog and choices
        this.gc = gc;

        // Setup Main Character
        Item knife = new Item("Knife", "Weapon", 1);
        Item ducttape = new Item("Duct Tape", "Utility", 1);
        mc.getInventory().addItem(knife);
        mc.addCurrency(50.00);

        // C1 Mugger
        c1Mugger = new Character("c1Mugger", false);
        c1MuggerPistol = new Item("Pistol", "Weapon", 1);
        c1Mugger.getInventory().addItem(c1MuggerPistol);

        // C2 Fire Person
        c2FirePerson = new Character("c2FirePerson", false);
        c2FirePersonMedkit = new Item("Medkit", "Health", 1);
        c2FirePerson.getInventory().addItem(c2FirePersonMedkit);

        // C4 Bench Person
        c4BenchPerson = new Character("c4BenchPerson", false);
        c4BenchPerson.addCurrency(25.00);
        
        // C4 Fight Person
        c4FightPerson = new Character("c4FightPerson", false);
    }

    public void displayInventory() {

        // Creates a new dialog to show the player's inventory
        Stage dialog = new Stage();
        dialog.setTitle("Inventory");
        dialog.initStyle(StageStyle.UTILITY);

        ListView listView = new ListView();

        // Adds all of the items in the inventory to the the ListView
        for (Item i : mc.getInventory().getInventory()) {
            listView.getItems().add(i.getName());
        }

        HBox hbox = new HBox(listView);

        Scene scene = new Scene(hbox, 300, 120);

        dialog.setScene(scene);
        dialog.setWidth(250);
        dialog.setHeight(250);
        dialog.show();
    }

    /* The following methods with the structure c<num><keyword> like "c1Sneak" represent sub choices in the overall story structure
    * For example, if user clicks "Try to resolve situation peacefully" it calls c1Resolve and then the next choice could be
    * c1Resolve disarm. Once user is done with this sub story, the continue along the storyline of the game.
     */
 /* Chapter 1 */
    public void c1Sneak(String location) {

        if (subprogress == 0) {
            String prepend = "You sneak up behind the man and knife him. The lady rewards you $25. A pistol has been added to your inventory.\n";

            // Add Pistol to Inventory
            Item pistol = new Item("Pistol", "Weapon", 1);
            mc.getInventory().addItem(pistol);

            // Add Money
            mc.addCurrency(25.00);

            progress++;
            subprogress = 0;
            updateStory(prepend, null);
        }

    }

    public void c1Resolve(String location) {

        if (subprogress == 0) {
            gc.getJFX().updateDialog("'Hey, what seems to be the problem here?' "
                    + "'This has nothing to do with you man, stay back or I'll shoot!");

            String choice1 = "Try to disarm the man";
            String choice2 = "Offer him money instead";
            String choice3 = "Walk away";
            String choice4 = "Check Inventory";

            gc.getJFX().updateChoices("c1Resolve disarm", choice1, "c1Resolve money", choice2, "c1Resolve walkAway", choice3, "check", choice4);

            subprogress++;
        } else {

            if (location.contains("disarm")) {

                //System.out.println("Here");
                String prepend = "You charge the man and tackle him to the ground."
                        + "In the process, you get shot in the arm. You are able to free "
                        + "the pistol from his grip and use it against him. Pistol has been "
                        + "added to your inventory. Health has been reduced 25%.";

                // Reduce the health
                mc.reduceHealth(25.00);

                // Add Pistol to Inventory
                c1Mugger.reduceHealth(100.00);
                mc.getInventory().addItem(c1Mugger.getInventory().removeItem(c1MuggerPistol));

                progress++;
                subprogress = 0;

                updateStory(prepend, location);
            } else if (location.contains("money")) {
                gc.getJFX().setEndScene("You offer the man $50 to leave her alone. "
                        + "The man takes the money and shoots you in the head. Game over!");
            } else if (location.contains("walkAway")) {
                String prepend = "You walk away and hear a faint gunshot in the distance. 'What have I done?'\n";

                progress++;
                subprogress = 0;

                updateStory(prepend, location);
            } else if (location.contains("check")) {
                displayInventory();
            }

        }

    }

    public void c1Stop(String location) {
        if (subprogress == 0) {
            String prepend = "'Hey! Get away from her!' The man turns around and unloads "
                    + "his pistol in your direction and takes off. 'What a terrible shot!' "
                    + "The woman thanks you for intervening.\n";

            progress++;
            subprogress = 0;

            updateStory(prepend, location);
        }
    }

    /* End Chapter 1 */
 /* Chapter 2 */
    public void c2Search(String location) {

        if (subprogress == 0) {

            String prepend = "You begin to search the vehicle. "
                    + "Inside, you find $20 cash inside the glove box.";

            progress++;
            subprogress = 0;

            updateStory(prepend, location);

        }

    }

    public void c2Lady(String location) {

        if (subprogress == 0) {

            gc.getJFX().updateDialog("You approach the lady and begin to talk with her. "
                    + "'What's wrong, ma'am?' 'That man just stole my purse, please help me!'");

            String choice1 = "'Don't worry, I'll get him!";
            String choice2 = "'Sorry, I have to get going. Really wish I could help.'";
            String choice3 = "'What am I supposed to do about it?'";
            String choice4 = "Check Inventory";

            gc.getJFX().updateChoices("c2Lady chase", choice1, "c2Lady leave", choice2, "c2Lady rude", choice3, "c2Lady check", choice4);

            subprogress++;
        } else {

            if (location.contains("chase")) {

                if (subsubprogress == 0) {
                    gc.getJFX().updateDialog("'Thank you so much!' You begin to chase down the guy. You are right on his heels.");

                    String choice1 = "Tackle him";
                    
                    String choice2 = "";
                    String c2ID = "";
                    
                    boolean find = false;
                    Item r = null;

                    for (Item i : mc.getInventory().getInventory()) {
                        if (i.getName().equals("Pistol")) {
                            find = true;
                            r = i;
                            break;
                        }
                    }
                    
                    if (find == true) {
                        choice2 = "Shoot him";
                        c2ID = "c2Lady chase shoot";
                    }
                    else {
                        choice2 = "Throw knife";
                        c2ID = "c2Lady chase throw";
                    }
                    
                    String choice3 = "Let him get away";
                    String choice4 = "Check Inventory";

                    gc.getJFX().updateChoices("c2Lady chase tackle", choice1, c2ID, choice2, "c2Lady chase getAway", choice3, "c2Lady chase check", choice4);

                    subsubprogress++;
                } else {

                    if (location.contains("tackle")) {
                        String prepend = "You tackle the man. You both struggle over the purse. "
                                + "In the struggle, the man pulls out a knife and stabs you in the arm. "
                                + "Your health has reduced 25%. You were able to grab the purse and escape "
                                + "back to the lady. She rewards you $50.";

                        // Add Currency
                        mc.addCurrency(50.00);
                        
                        // Reduce Health
                        mc.reduceHealth(25.00);
                        
                        if (mc.getHealth() <= 0.00) {
                            gc.getJFX().setEndScene(prepend + " You ran out of health and died.");
                        }
                        
                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;

                        updateStory(prepend, null);
                    } 
                    else if (location.contains("throw")) {
                        String prepend = "You throw knife and miss him by a few feet. Who told you that you were good at throwing knives?\n";
                        
                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;
                        
                        updateStory(prepend, null);
                    }
                    else if (location.contains("shoot")) {
                        String prepend = "You pull out your pistol and shoot the man. You collect the "
                                + "purse and also discover a knife on the man's body. You return the purse "
                                + "to the lady and she rewards you $50.";

                        // Add Currency
                        mc.addCurrency(50.00);

                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;

                        updateStory(prepend, null);
                    } else if (location.contains("getAway")) {

                        gc.getJFX().setEndScene("You let the man get away and the lady screams at you. She "
                                + "turns on you and tells the group of people at the fire that you stole "
                                + "her purse. The group of people jump you and one of them pulls out a pistol "
                                + "and shoots you in the head.");

                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;
                    } else if (location.contains("check")) {
                        displayInventory();
                    }
                }

            } else if (location.contains("leave")) {

                String prepend = "'Forget you!' You keep on walking to your bunker.";

                progress++;
                subprogress = 0;
                subsubprogress = 0;

                updateStory(prepend, location);

            } else if (location.contains("rude")) {

                String prepend = "'He's gonna get away!' She then screams to the group "
                        + "of people that you are the one who stole her purse. The group "
                        + "mugs you and one of them breaks your arm. Your health has been "
                        + "reduced 25%. ";

                // Reduce health
                mc.reduceHealth(25.00);

                if (mc.getHealth() <= 0.00) {
                    gc.getJFX().setEndScene(prepend + "User is dead!");
                }

                progress++;
                subprogress = 0;
                subsubprogress = 0;

                updateStory(prepend, location);

            } else if (location.contains("check")) {
                displayInventory();
            }

        }

    }

    public void c2PeopleFire(String location) {

        if (subprogress == 0) {

            gc.getJFX().updateDialog("'Hey guys, hows it going?' 'Alright. You looking to buy a medkit for $20?'");

            String choice1 = "Buy medkit for $20";
            String choice2 = "Offer him $15 instead";
            String choice3 = "Pass on the offer";
            String choice4 = "Check Inventory";

            gc.getJFX().updateChoices("c2PeopleFire buy20", choice1, "c2PeopleFire buy15", choice2, "c2PeopleFire pass", choice3, "c2PeopleFire check", choice4);

            subprogress++;
        } else {

            if (location.contains("buy20")) {

                System.out.println("Buy 20");

                if (mc.getCurrency() >= 20.00) {

                    String prepend = "'Nice doing business with you. Good luck on your trip!' Medkit has been added to your inventory. \n";

                    mc.getInventory().addItem(c2FirePerson.getInventory().removeItem(c2FirePersonMedkit));

                    progress++;
                    subprogress = 0;
                    subsubprogress = 0;

                    updateStory(prepend, location);

                } else {

                    if (subsubprogress == 0) {
                        gc.getJFX().updateDialog("'Sorry, I don't have enough money.' "
                                + "'Well, that's too bad. Let's see what you have in "
                                + "that backpack there.' The group starts to approach you.");

                        String choice1 = "Run away";
                        String choice2 = "Pull out pistol";
                        String choice3 = "Give them your backpack (containing inventory)";
                        String choice4 = "Check Inventory";

                        gc.getJFX().updateChoices("c2PeopleFire buy20 run", choice1, "c2PeopleFire buy20 pistol", choice2, "c2PeopleFire buy20 give", choice3, "c2PeopleFire buy20 check", choice4);

                        subsubprogress++;
                    } else {

                        if (location.contains("run")) {
                            gc.getJFX().setEndScene("You try to run away, but you hear the chambering of a "
                                    + "shotgun round. All of a sudden you drop to the ground and your "
                                    + "vision fades to black. ");

                            progress++;
                            subprogress = 0;
                            subsubprogress = 0;
                        } else if (location.contains("pistol")) {
                            String prepend = "You pull out your pistol and aim it at the closest "
                                    + "person standing next to you. The group takes off leaving the "
                                    + "medkit behind. It has been added to your inventory. \n";

                            progress++;
                            subprogress = 0;
                            subsubprogress = 0;

                            updateStory(prepend, location);
                        } else if (location.contains("give")) {
                            String prepend = "You hand over the backpack and lose all of your "
                                    + "inventory and currency. \n";

                            // Clear Inventory
                            mc.clearInventory();

                            // Clear Currency
                            mc.clearCurrency();

                            progress++;
                            subprogress = 0;
                            subsubprogress = 0;

                            updateStory(prepend, location);
                        } else if (location.contains("check")) {
                            displayInventory();
                        }

                    }
                }

            } else if (location.contains("buy15")) {

                if (mc.getCurrency() >= 15.00) {
                    String prepend = "'Fine, I guess that will work. Here you go.' Medkit has been added to your inventory. \n";

                    mc.getInventory().addItem(c2FirePerson.getInventory().removeItem(c2FirePersonMedkit));

                    progress++;
                    subprogress = 0;
                    subsubprogress = 0;

                    updateStory(prepend, location);
                } else {

                    if (subsubprogress == 0) {

                        gc.getJFX().updateDialog("'Sorry, I don't have enough money.' 'Well, that's too bad. "
                                + "Let's see what you have in that backpack there.' The group starts to approach you.");

                        String choice1 = "Run away";
                        String choice2 = "Pull out pistol";
                        String choice3 = "Give them your backpack (containing inventory)";
                        String choice4 = "Check Inventory";

                        gc.getJFX().updateChoices("c2PeopleFire buy15 run", choice1, "c2PeopleFire buy15 pistol", choice2, "c2PeopleFire buy15 give", choice3, "c2PeopleFire buy15 check", choice4);

                        subsubprogress++;
                    } else {

                        if (location.contains("run")) {
                            gc.getJFX().setEndScene("You try to run away, but your hear the chambering of a shotgun round. "
                                    + "All of sudden you drop to the ground and your vision fades to black. ");

                            progress++;
                            subprogress = 0;
                            subsubprogress = 0;
                        } else if (location.contains("pistol")) {
                            String prepend = "You pull out your pistol and aim it at the closest person standing next to you. "
                                    + "The group takes off leaving the medkit behind. It has been added to your inventory. \n";

                            mc.getInventory().addItem(c2FirePerson.getInventory().removeItem(c2FirePersonMedkit));

                            progress++;
                            subprogress = 0;
                            subsubprogress = 0;

                            updateStory(prepend, location);
                        } else if (location.contains("give")) {
                            String prepend = "You hand over the backpack and lose all of your inventory and currency. \n";

                            // Clear currency
                            mc.clearCurrency();

                            // Clear Inventory
                            mc.clearInventory();

                            progress++;
                            subprogress = 0;
                            subsubprogress = 0;

                            updateStory(prepend, location);
                        } else if (location.contains("check")) {
                            displayInventory();
                        }

                    }

                }

            } else if (location.contains("pass")) {

                String prepend = "'Alright, leave us alone then.' \n";

                progress++;
                subprogress = 0;

                updateStory(prepend, location);

            } else if (location.contains("check")) {

                displayInventory();

            }

        }

    }

    /* End Chapter 2 */
 /* Chapter 3 */
    public void c3Intervene(String location) {

        if (subprogress == 0) {
            gc.getJFX().updateDialog("Mind your own business!");

            String choice1 = "You both need to stop!";
            String choice2 = "Awe, cant we all just get along?";
            String choice3 = "Were you dropped as a baby?";
            String choice4 = "Indicate you supported Trump.";
            gc.getJFX().updateChoices("c3Intervene stop", choice1, "c3Intervene along", choice2, "c3Intervene baby", choice3, "c3Intervene trump", choice4);

            subprogress++;
        } else {
            if (location.contains("stop")) {
                if (subprogress == 1) {
                    gc.getJFX().updateDialog("lol? seriously? weak? This wont stop, I'm killing this thief!");

                    String choice1 = "Grab the man?";
                    String choice2 = "Yell: Hey! aint nobody got time for this!";
                    String choice3 = "...Is there a fire?";
                    String choice4 = "If someone throws bread at you....do you duck?";

                    subprogress++;
                    gc.getJFX().updateChoices("c3Intervene stop grab", choice1, "c3Intervene stop yell", choice2, "c3Intervene stop fire", choice3, "c3Intervene stop duck", choice4);
                } else {

                    if (location.contains("grab")) {

                        String prepend = "You grabbed the man. He pulls out a knife and stabs you. You run away from the scene.";
                        progress++;
                        subprogress = 0;

                        updateStory(prepend, location);
                    } else if (location.contains("yell")) {

                        if (subsubprogress == 0) {
                            gc.getJFX().updateDialog("The angry man grabs your throat and begins choking you... Mr.funny, I presume?");

                            String choice1 = "Please stop?";
                            String choice2 = "Gouge his eyes out!";
                            String choice3 = "Knee to the groin!";
                            String choice4 = "Snot strike!";

                            gc.getJFX().updateChoices("c3Intervene stop yell plead", choice1, "c3Intervene stop yell gouge", choice2, "c3Intervene stop yell knee", choice3, "c3Intervene stop yell snot", choice4);
                            subsubprogress++;
                        } else {
                            if (location.contains("plead")) {
                                String prepend = "You plead for the man to stop, you hear faint chuckling as your vision goes dark, and your journey ends.";

                                progress++;
                                subprogress = 0;
                                subsubprogress = 0;

                                updateStory(prepend, location);
                            } else if (location.contains("gouge")) {
                                String prepend = "You claw at his eyes, like a fat fingered infant grabbing playdough. The man goes down like a hobo on a ham sandwich. So much blood!";

                                progress++;
                                subprogress = 0;
                                subsubprogress = 0;

                                updateStory(prepend, location);
                            } else if (location.contains("knee")) {
                                String prepend = "You knee him in the groin! He falls over writhing in pain, you are able to escape.\n";

                                progress++;
                                subprogress = 0;
                                subsubprogress = 0;

                                updateStory(prepend, location);
                            } else if (location.contains("snot")) {
                                String prepend = "The sinus infection you have has paid off. You spew forth a small meteric crap ton of green sludge from your sinus canals; smothering the mans face in yellowish green mucus. The man gags and lets go of you, you can hear him vomiting as you flee.\n";

                                progress++;
                                subprogress = 0;
                                subsubprogress = 0;

                                updateStory(prepend, location);
                            }
                        }

                    } else if (location.contains("fire")) {

                        String prepend = "You are now in a maze of twisty passages, all alike. ";
                        progress++;
                        subprogress = 0;

                        updateStory(prepend, location);

                    } else if (location.contains("duck")) {

                        String prepend = "Yes, a profound statement, random, and irrelevant.... You win nothing.";
                        progress++;
                        subprogress = 0;

                        updateStory(prepend, location);

                    }
                }
            } else if (location.contains("along")) {

                if (subprogress == 1) {

                    gc.getJFX().setEndScene("Thats not gonna happen, but.. I can help you along with a nap." + " You feel the sharp sting of needle pierce your skin, and all goes quiet.");

                    progress = 0;
                    subprogress = 0;

                }

            } else if (location.contains("baby")) {

                if (subprogress == 1) {

                    String prepend = "Dropped, eh? I'm gonna drop you! A ringing fills you ears, as the concrete below rushes towards your face, you hit the ground with a thud. Health has been reduced 10%";

                    // Reduce Health
                    mc.reduceHealth(10.0);
                    
                    if (mc.getHealth() <= 0.00) {
                        gc.getJFX().setEndScene(prepend + " You ran out of health and died.");
                    }
                    
                    progress++;
                    subprogress = 0;
                    updateStory(prepend, location);

                }

            } else if (location.contains("trump")) {

                if (subprogress == 1) {

                    gc.getJFX().setEndScene("I voted clinton! You both begin to argue your politcal views, the debate rages on for all eternity... costing you this game... and you life.");

                    progress = 0;
                    subprogress = 0;
                }

            }
        }
    }

    public void c3WalkCalmly(String location) {

        if (subprogress == 0) {

            gc.getJFX().updateDialog("'Hey, what seems to be the problem here?' 'This guy stole my wallet!' "
                    + "'No, I didn't! I found it on the ground!");

            String choice1 = "Steal the wallet from both of them";
            String choice2 = "Give the wallet back to the guy who claims it's his";
            String choice3 = "Split the money in the wallet";
            String choice4 = "Check Inventory";

            gc.getJFX().updateChoices("c3WalkCalmly steal", choice1, "c3WalkCalmly giveBack", choice2, "c3WalkCalmly split", choice3, "c3WalkCalmly check", choice4);

            subprogress++;
        } else {

            if (location.contains("steal")) {

                System.out.println("Stealing...");

                String prepend = "You pull out your pistol and steal the wallet from both of them and take off. They never saw it coming. You find $25 inside the wallet.";

                // Add the money
                mc.addCurrency(25.00);

                progress++;
                subprogress = 0;
                subsubprogress = 0;

                updateStory(prepend, location);

            } else if (location.contains("giveBack")) {

                if (subsubprogress == 0) {
                    gc.getJFX().updateDialog("You take the wallet from the guy and begin to hand it to the supposed original owner. The guy begins to reach for something behind his back.");

                    String choice1 = "";
                    String c1ID = "";

                    boolean find = false;
                    Item r = null;

                    for (Item i : mc.getInventory().getInventory()) {
                        if (i.getName().equals("Pistol")) {
                            find = true;
                            r = i;
                            break;
                        }
                    }

                    if (find == true) {
                        choice1 = "Pull out pistol and shoot";
                        c1ID = "c3WalkCalmly giveBack pistol";
                    } else {
                        choice1 = "Run Away";
                        c1ID = "c3WalkCalmly giveBack away";
                    }

                    String choice2 = "Tackle the guy";
                    String choice3 = "Drop the wallet";
                    String choice4 = "Check Inventory";

                    gc.getJFX().updateChoices(c1ID, choice1, "c3WalkCalmly giveBack tackle", choice2, "c3WalkCalmly giveBack dropWallet", choice3, "c3WalkCalmly giveBack check", choice4);

                    subsubprogress++;
                } else {

                    if (location.contains("away")) {
                        String prepend = "You run away with gun shots whizzing by your head. You barely escape. \n";

                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;

                        updateStory(prepend, location);
                    }
                    if (location.contains("pistol")) {

                        String prepend = "You shoot the guy and pistol falls from the guy's hands. The other guy takes off with the wallet and the gun. \n";

                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;

                        updateStory(prepend, location);

                    } else if (location.contains("tackle")) {

                        gc.getJFX().setEndScene("You tackle the man and catch the flash from the end of the muzzle as your whole world turns black. Game Over.");

                        mc.reduceHealth(100.00);

                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;

                    } else if (location.contains("dropWallet")) {

                        String prepend = "You drop the wallet on the ground. The man takes off with the wallet. \n";

                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;

                        updateStory(prepend, location);
                    } else if (location.contains("check")) {
                        displayInventory();
                    }

                }
            } else if (location.contains("split")) {

                String prepend = "You split the contents of the wallet between the two guys. They appear to accept it and go their own ways. \n";

                progress++;
                subprogress = 0;
                subsubprogress = 0;

                updateStory(prepend, location);
            } else if (location.contains("check")) {
                displayInventory();
            }

        }

    }

    public void c3InspectShoppingCart(String location) {
        if (subprogress == 0) {
            String prepend = "You walk up and begin to search the shopping cart. Inside you find a bottle of water, some crackers, and duct tape. These items have been added to your inventory.\n ";

            progress++;
            subprogress = 0;

            Item water = new Item("Bottle of Water", "Food/Water", 1);
            Item crackers = new Item("Crackers", "Food/Water", 1);
            Item ductTape = new Item("Duct Tape", "Tools", 1);

            mc.getInventory().addItems(water, crackers, ductTape);

            updateStory(prepend, location);
        }
    }

    /* End Chapter 3 */
    
    /* Chapter 4 */
    public void c4Approach(String location) {

        // Fight Sequence
        if (subprogress == 0) {

            gc.getJFX().updateDialog("The man appears disturbed. You try to walk by and he attacks you.");

            String choice1 = "Use knife to attack";
            
            String choice2 = "";
            String c2ID = "";
            
            boolean find = false;
            Item r = null;

            for (Item i : mc.getInventory().getInventory()) {
                if (i.getName().equals("Pistol")) {
                    find = true;
                    r = i;
                    break;
                }
            }
            
            if (find == true) {
                choice2 = "Use pistol to shoot";
                c2ID = "c4Approach pistol";
            }
            else {
                choice2 = "Try to run away";
                c2ID = "c4Approach away";
            }
            
            String choice3 = "Hand to hand combat only";
            String choice4 = "Check Inventory";

            gc.getJFX().updateChoices("c4Approach knife", choice1, c2ID, choice2, "c4Approach handToHand", choice3, "c4Approach check", choice4);

            subprogress++;

        } else {

            if (location.contains("knife")) {

                if (subsubprogress == 0) {

                    // Reduce health 
                    mc.reduceHealth(15.00);
                    c4FightPerson.reduceHealth(15.00);
                    
                    if (mc.getHealth() <= 0.00) {
                        gc.getJFX().setEndScene("You lunge to attack with the knife, you cut him across the face. He is able to get the knife from your grip and stab you in the leg. Health has been reduced 25%. Your health has reached zero. Game over.");
                    } else {
                        gc.getJFX().updateDialog("You lunge to attack with the knife, you cut him across the face. He is able to get the knife from your grip and stab you in the leg. Health has been reduced 25%.");

                        String choice1 = "Pull out pistol and shoot";
                        String choice2 = "Try to disarm him";
                        String choice3 = "Try to run away";
                        String choice4 = "Check Inventory";

                        gc.getJFX().updateChoices("c4Approach knife gun", choice1, "c4Approach knife disarm", choice2, "c4Approach knife run", choice3, "c4Approach knife check", choice4);
                    }
                    subsubprogress++;
                } else {

                    if (location.contains("gun")) {

                        String prepend = "You are able to pull out your pistol to shoot. He lunges for the gun but it is too late.";
                        
                        c4FightPerson.reduceHealth(100.00);
                        
                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;

                        updateStory(prepend, location);

                    } else if (location.contains("disarm")) {

                        gc.getJFX().setEndScene("You reach to disarm the man and you both struggle over the knife. He trips you to the ground with the blade inches from your heart. There is no need to explain the rest. Game over.");

                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;

                    } else if (location.contains("run")) {

                        gc.getJFX().setEndScene("You try to run away from the man, but are unable to escape. You shouldn't have dropped the knife. Game over.");

                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;
                    } else if (location.contains("check")) {
                        displayInventory();
                    }

                }

            }
            else if (location.contains("away")) {
                gc.getJFX().setEndScene("You try to run away, but you hear the distinct sound of pistol chambering a round. The end.");
                
                progress++;
                subprogress = 0;
                subsubprogress = 0;
            }
            else if (location.contains("pistol")) {

                if (subsubprogress == 0) {

                    gc.getJFX().updateDialog("You are able to get the pistol out and shoot him in the arm. He closes the distance and is able to kick the pistol out of reach.");
                    
                    c4FightPerson.reduceHealth(50.00);
                    
                    String choice1 = "Go for Choke Hold";
                    String choice2 = "Attack with knife";
                    String choice3 = "Knee in the groin";
                    String choice4 = "Check Inventory";

                    gc.getJFX().updateChoices("c4Approach pistol choke", choice1, "c4Approach pistol blade", choice2, "c4Approach pistol knee", choice3, "c4Approach pistol check", choice4);

                    subsubprogress++;
                } else {

                    if (location.contains("choke")) {
                        String prepend = "You are able to get the guy in a choke hold, and he slowly falls asleep.\n";
                        
                        c4FightPerson.reduceHealth(100.00);
                        
                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;

                        updateStory(prepend, location);
                    } else if (location.contains("blade")) {
                        gc.getJFX().setEndScene("You lunge with the knife and he blocks the blow and instead turns the knife on you. Your journey has ended.\n");

                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;
                    } else if (location.contains("knee")) {
                        String prepend = "You knee the man in the groin and he falls over in pain. You are able to knock him out.\n";
                        
                        c4FightPerson.reduceHealth(100.00);
                        
                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;

                        updateStory(prepend, location);
                    } else if (location.contains("check")) {
                        displayInventory();
                    }

                }

            } else if (location.contains("handToHand")) {

                if (subsubprogress == 0) {

                    gc.getJFX().updateDialog("You both trade punches and seem like an even match. You are able to land a devasting blow to his head, but he got you in your wind pipe.");

                    String choice1 = "Fake injury and suprise attack";
                    String choice2 = "Try to get him in head lock";
                    String choice3 = "Tackle him";
                    String choice4 = "Check Inventory";

                    gc.getJFX().updateChoices("c4Approach handToHand fake", choice1, "c4Approach handToHand lock", choice2, "c4Approach handToHand tackle", choice3, "c4Approach handToHand check", choice4);

                    subsubprogress++;
                } else {

                    if (location.contains("fake")) {
                        String prepend = "You fake injury and are able to mislead the guy. He closes in and you elbow him in the abdomen. He falls over writhing in pain and you are able to escape.\n";

                        c4FightPerson.reduceHealth(100.00);
                        
                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;

                        updateStory(prepend, location);
                    } else if (location.contains("lock")) {
                        String prepend = "You are able to get him in a headlock and he slowly falls asleep.";

                        c4FightPerson.reduceHealth(100.00);
                        
                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;

                        updateStory(prepend, location);
                    } else if (location.contains("tackle")) {
                        gc.getJFX().setEndScene("You tackle the man and somehow manages to get you in a choke hold. Your world slowly fades to black.");

                        progress++;
                        subprogress = 0;
                        subsubprogress = 0;
                    } else if (location.contains("check")) {
                        displayInventory();
                    }

                }

            } else if (location.contains("check")) {
                displayInventory();
            }

        }

    }

    public void c4BenchPeople(String location) {
        // Sell Feature

        if (subprogress == 0) {

            gc.getJFX().updateDialog("'Hey, you interested in buying any of these items?'");

            String choice1 = "";
            String c1ID = "";
            
            boolean find1 = false;
            Item r1 = null;

            for (Item i : mc.getInventory().getInventory()) {
                if (i.getName().equals("Medkit")) {
                    find1 = true;
                    r1 = i;
                    break;
                }
            }
            
            if (find1 == true) {
                choice1 = "Sell medkit for $10";
                c1ID = "c4BenchPeople medkit";
            }
            else {
                choice1 = "Tell them you changed your mind.";
                c1ID = "c4BenchPeople change";
            }
            
            String choice2 = "Sell knife for $15";

            String choice3 = "";
            String c3ID = "";

            boolean find = false;
            Item r = null;

            for (Item i : mc.getInventory().getInventory()) {
                if (i.getName().equals("Pistol")) {
                    find = true;
                    r = i;
                    break;
                }
            }

            if (find == true) {
                choice3 = "Sell pistol for $100";
                c3ID = "c4BenchPeople pistol";
                System.out.println("Invent: " + mc.getInventory());
                mc.getInventory().removeItem(r);
            } else {
                choice3 = "Keep Walking";
                c3ID = "c4BenchPeople keepwalking";
            }

            String choice4 = "Check Inventory";

            gc.getJFX().updateChoices(c1ID, choice1, "c4BenchPeople knife", choice2, c3ID, choice3, "c4BenchPeople check", choice4);

            subprogress++;
        } else {

            if (location.contains("medkit")) {

                if (c4BenchPerson.getCurrency() >= 10.00) {

                    String prepend = "The person buys the medkit for $10. This money has been added to your currency.\n";

                    // Add currency
                    mc.addCurrency(10.00);

                    // Remove medkit from your inventory
                    // Add medkit to their inventory
                    progress++;
                    subprogress = 0;
                    subsubprogress = 0;

                    updateStory(prepend, location);

                } else {

                    String prepend = "The person does not have sufficient currency. \n";

                    progress++;
                    subprogress = 0;
                    subsubprogress = 0;

                    updateStory(prepend, location);

                }

            } 
            else if (location.contains("change")) {
                progress++;
                subprogress = 0;
                subsubprogress = 0;
                
                updateStory("", location);
            }
            else if (location.contains("knife")) {

                if (c4BenchPerson.getCurrency() >= 15.00) {

                    String prepend = "The person buys the knife for $15. The money has been added to your currency. \n";

                    progress++;
                    subprogress = 0;
                    subsubprogress = 0;

                    updateStory(prepend, location);
                } else {

                    String prepend = "The person does not have sufficient currency. \n";

                    progress++;
                    subprogress = 0;
                    subsubprogress = 0;

                    updateStory(prepend, location);
                }

            } else if (location.contains("keepwalking")) {
                progress++;
                subprogress = 0;
                subsubprogress = 0;
                updateStory("", location);
            } else if (location.contains("pistol")) {

                if (c4BenchPerson.getCurrency() >= 100.00) {

                    String prepend = "The person buys the pistol for $100. The money has been added to your currency.\n";

                    progress++;
                    subprogress = 0;
                    subsubprogress = 0;

                    updateStory(prepend, location);

                } else {

                    String prepend = "The person does not have sufficient currency.\n";

                    progress++;
                    subprogress = 0;
                    subsubprogress = 0;

                    updateStory(prepend, location);
                }

            } else if (location.contains("check")) {
                displayInventory();
            }

        }

    }

    /* End Chapter 4 */
    
    /**
     *
     * @param prepend - text that needs to be added before the dialog text
     * @param location - location/position in the story line (Ex. "c1Resolve
     * disarm")
     */
    public void updateStory(String prepend, String location) {

        // This method basically moves the storyline along and receives story location from the ChoiceButton
        if (progress == 0) {

            if (location.contains("c1Sneak")) {
                c1Sneak(location);
            } else if (location.contains("c1Resolve")) {
                c1Resolve(location);
            } else if (location.contains("c1Stop")) {
                c1Stop(location);
            } else if (location.contains("c1Check")) {
                displayInventory();
            }

        } else if (progress == 1) {

            gc.getJFX().updateDialog(prepend + "You come to an intersection. You see an abandoned vehicle "
                    + "on the side of the road. There is also a lady asking for help on the sidewalk. There are "
                    + "also a few people huddled around a fire.");

            String choice1 = "Search abandonded vehicle";
            String choice2 = "Talk to lady";
            String choice3 = "Talk to people around fire";
            String choice4 = "Check Inventory";

            gc.getJFX().updateChoices("c2Search", choice1, "c2Lady", choice2, "c2PeopleFire", choice3, "c2Check", choice4);

            progress++;

        } else if (progress == 2) {

            if (location.contains("c2Search")) {
                c2Search(location);
            } else if (location.contains("c2Lady")) {
                c2Lady(location);
            } else if (location.contains("c2PeopleFire")) {
                c2PeopleFire(location);
            } else if (location.contains("c2Check")) {
                displayInventory();
            }

        } else if (progress == 3) {

            gc.getJFX().updateDialog(prepend + "You come across two people arguing in the road. One is accusing the other of stealing something from the other. You also see an abandonded shopping cart with items inside.");

            String choice1 = "Intervene";
            String choice2 = "Walk calmly up to them";
            String choice3 = "Search Shopping Cart";
            String choice4 = "Check Inventory";

            gc.getJFX().updateChoices("c3Intervene", choice1, "c3WalkCalmly", choice2, "c3InspectShoppingCart", choice3, "c3Check", choice4);

            progress++;

        } else if (progress == 4) {

            if (location.contains("c3Intervene")) {
                c3Intervene(location);
            } else if (location.contains("c3WalkCalmly")) {
                c3WalkCalmly(location);
            } else if (location.contains("c3InspectShoppingCart")) {
                c3InspectShoppingCart(location);
            } else if (location.contains("c3Check")) {
                displayInventory();
            }

        } else if (progress == 5) {

            gc.getJFX().updateDialog(prepend + "You are nearing the bunker. Up ahead at the traffic light, you see man walking around anxiously. You also see a few people sitting on a bench on the sidewalk.");

            String choice1 = "Approach the anxious man";
            String choice2 = "Walk up to the people on the bench";
            String choice3 = "Keep walking";
            String choice4 = "Check Inventory";

            gc.getJFX().updateChoices("c4Approach", choice1, "c4BenchPeople", choice2, "c4KeepWalking", choice3, "c4Check", choice4);

            progress++;
        } else if (progress == 6) {

            if (location.contains("c4Approach")) {
                c4Approach(location);
            } else if (location.contains("c4BenchPeople")) {
                c4BenchPeople(location);
            } else if (location.contains("c4KeepWalking")) {
                progress++;
                updateStory(prepend, location);
            } else if (location.contains("c4Check")) {
                displayInventory();
            }

        } else if (progress == 7) {

            gc.getJFX().setEndScene("Congrats! You have finished the game! You can try again by restarting below or you can exit the game. Thanks for playing!");

        }

    }

}
