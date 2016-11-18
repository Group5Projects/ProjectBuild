package trumpocalypse;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Julian Loftis
 * 
 */

public class DialogText extends Text {
    /**
     * 
     * @param t - used to set the text of the button.
     * @return - nothing.
     */
    public DialogText(String t) {
        this.setText(t);
        this.setFont(Font.font("Arial", FontWeight.NORMAL, 16)); 
        this.setStyle("-fx-alignment: center-right; -fx-fill: white;");
        this.setWrappingWidth(400);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setLayoutX(50);
        this.setLayoutY(140);
    }
}
