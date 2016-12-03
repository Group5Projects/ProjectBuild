package trumpocalypse;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Julian Loftis, Michael Murphy, Robert Gulker
 * 
 */

public class RestartButton extends Button {
    public RestartButton(Stage st) {
        this.setText("Restart");
        this.setStyle("-fx-font: 14 arial; -fx-background-color: seagreen; -fx-text-fill: white; -fx-padding: 5 10 5 10;");
        this.setLayoutX(10);
        this.setLayoutY(10);
        
        // Restart the system
        this.setOnAction( __ ->
        {
          st.close();
          Platform.runLater( () -> new Trumpocalypse().start( new Stage() ) );
        } );
    }
}
