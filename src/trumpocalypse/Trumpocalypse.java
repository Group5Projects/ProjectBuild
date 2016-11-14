package trumpocalypse;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 * @author Julian Loftis
 */

public class Trumpocalypse extends Application {
    
    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;
    private DialogText dialog;
    private Pane choicePane;
    private GameController gc;
    
    @Override
    public void start(Stage primaryStage) {

        Scene scene1 = startScene();
        
        stage = primaryStage;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Hello World!");
        stage.setScene(scene1);
        stage.setWidth(500);
        stage.setHeight(400);
        stage.setResizable(false);
        stage.show();
        
    }
    public Scene startScene(){
        
        Pane root = new Pane();
        Button startGame = new Button();
        startGame.setText("Start Game");
        startGame.setStyle("-fx-font: 18 arial; -fx-background-color: seagreen; -fx-text-fill: white; -fx-padding: 10 40 10 40;");
        startGame.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                  stage.setScene(ingameScene());
            }
        });
        startGame.setLayoutX(160);
        startGame.setLayoutY(275);
        
        // Created By Text
        Text createdBy = new Text("Created by:");
        createdBy.setFont(Font.font("Arial", FontWeight.NORMAL, 14));   
        createdBy.setLayoutX(215);
        createdBy.setLayoutY(350);
        
        // Creator Credits
        Text credits = new Text("Julian Loftis - Michael Murphy - Robert Gulker");
        credits.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        credits.setLayoutX(130);
        credits.setLayoutY(375);
        
        // Logo + Background Image
        Image image = new Image("logo.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setLayoutX(0);
        iv1.setLayoutY(0);
        
        // System Description
        DialogText t = new DialogText("Welcome to the Text Adventure Game! "
                + "The story takes place at the start of a nuclear war between Russia and the US. "
                + "The player must make it to his bunker in time."
                + "The player's success depends on his/her choices. Choose wisely!");
        t.setFont(Font.font("Arial", FontWeight.NORMAL, 16)); 
        t.setStyle("-fx-alignment: center-right; -fx-fill: white;");
        t.setWrappingWidth(400);
        t.setTextAlignment(TextAlignment.CENTER);
        t.setLayoutX(50);
        t.setLayoutY(140);
        
        // Exit Button
        ExitButton btnExit = new ExitButton();
        
        // Add all of the elements to the window
        root.getChildren().addAll(iv1, t, createdBy, credits, startGame, btnExit);
        root.setStyle("-fx-background-image: url(textadventure-ingame.png);");
        
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        return new Scene(root);
    }
    
    public Scene ingameScene(){
        Pane root = new Pane();
        root.getStylesheets().add("ingame.css");
        
        // Game Controller
        gc = new GameController(this);
        
        // System Description
        HBox hb = new HBox();
        dialog = new DialogText("Test");
        hb.getChildren().add(dialog);
        hb.setPrefSize(500, 280);
        hb.setAlignment(Pos.CENTER_LEFT);
        hb.setLayoutX(50);
        
        choicePane = new Pane();
        choicePane.setPrefSize(500, 180);
        choicePane.setLayoutX(0);
        choicePane.setLayoutY(220);
        ChoiceButton btn1 = new ChoiceButton(gc, 1, "Pick up gun");
        ChoiceButton btn2 = new ChoiceButton(gc, 2, "Keep walking");
        ChoiceButton btn3 = new ChoiceButton(gc, 3, "Search Vehicle");
        ChoiceButton btn4 = new ChoiceButton(gc, 4, "Search House");
        choicePane.getChildren().addAll(btn1,btn2,btn3,btn4);
        
        RestartButton restartBtn = new RestartButton(stage);
        ExitButton exitBtn = new ExitButton();
        
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        
        root.getChildren().addAll(hb, restartBtn, exitBtn, choicePane);
        root.setStyle("-fx-background-image: url(textadventure-ingame.png);");
        return new Scene(root);
    }
    

    public static void main(String[] args) {
        launch(args);
    }
    
    public void updateDialog(String text) {
        dialog.setText(text);
    }
    public void updateChoices(String c1, String c2, String c3, String c4) {
        ChoiceButton choice1 = new ChoiceButton(gc, 1, c1);
        ChoiceButton choice2 = new ChoiceButton(gc, 2, c2);
        ChoiceButton choice3 = new ChoiceButton(gc, 3, c3);
        ChoiceButton choice4 = new ChoiceButton(gc, 4, c4);
        choicePane.getChildren().addAll(choice1, choice2, choice3, choice4);
    }
    public Pane getChoicePane() {
        return choicePane;
    }
    
}
