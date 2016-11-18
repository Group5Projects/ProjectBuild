package trumpocalypse;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Julian Loftis
 */

public class ChoiceButton extends Button {
    public ChoiceButton(GameController gc, int choice, String text) {
        // Used for choices in game (choice = (1-4))
        // If choices are 5 or 6, they are used to restart and exit game
        
        this.setText(text);
        this.setPrefSize(400, 30);
        this.setLayoutX(50);
        
        if (choice == 1) { 
            this.setLayoutY(10); 
        }
        else if (choice == 2) { 
            this.setLayoutY(50); 
        }
        else if (choice == 3) { 
            this.setLayoutY(90); 
        }
        else if (choice == 4) { 
            this.setLayoutY(130); 
        }
        else if (choice == 5) { // Restart Button
            this.setLayoutY(10);
        }
        else if (choice == 6) { // Exit Button
            this.setLayoutY(50);
        }
        else { 
            System.out.println("Can't position"); 
        }
        
        if (choice >= 1 && choice <= 4) {
            this.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    gc.getStory().updateStory(choice);
                }

            });
        }
        else if (choice == 5) {
            // Restart Button
            this.setOnAction( __ ->
            {
              gc.getJFX().getStage().close();
              Platform.runLater( () -> new Trumpocalypse().start( new Stage() ) );
            } );
        }
        else if (choice == 6) {
            // Close Button
            this.setOnAction( __ -> {  System.exit(0); } );
        }
        else {
            System.out.println("Unsupported choice range. - ChoiceButton.java");
        }
    }

}
