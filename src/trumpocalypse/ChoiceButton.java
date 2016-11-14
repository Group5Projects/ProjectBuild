package trumpocalypse;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author Julian Loftis
 */

public class ChoiceButton extends Button {
    public ChoiceButton(GameController gc, int choice, String text) {
        this.setText(text);
        this.setPrefSize(400, 30);
        this.setLayoutX(50);
        
        if (choice == 1) { this.setLayoutY(10); }
        else if (choice == 2) { this.setLayoutY(50); }
        else if (choice == 3) { this.setLayoutY(90); }
        else if (choice == 4) { this.setLayoutY(130); }
        else { System.out.println("Can't position"); }
        
        this.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                gc.getStory().updateStory(choice);
            }
            
        });
    }

}
