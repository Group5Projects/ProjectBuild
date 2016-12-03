package trumpocalypse;

import javafx.scene.control.Button;

/**
 *
 * @author Julian Loftis, Michael Murphy, Robert Gulker
 * 
 */

public class ExitButton extends Button {
    public ExitButton() {
        this.setText("X");
        this.setStyle("-fx-font: 14 arial; -fx-background-color: seagreen; -fx-text-fill: white; -fx-padding: 5 10 5 10;");
        this.setLayoutX(460);
        this.setLayoutY(10);
        
        // Exit the application
        this.setOnAction( __ -> {  System.exit(0); } );
    }
}
