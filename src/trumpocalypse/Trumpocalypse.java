package trumpocalypse;

import javafx.application.Application;
import static javafx.application.Application.launch;
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
                  stage.setScene(inGameScene());
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
        
        // Exit Button
        ExitButton btnExit = new ExitButton();
        
        // Add all of the elements to the window
        root.getChildren().addAll(iv1, t, createdBy, credits, startGame, btnExit);
        root.setStyle("-fx-background-image: url(textadventure-ingame.png);");
        
        // Used to drag screen around
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        // Used to drag screen around
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        return new Scene(root);
    }
    
    // Called if the user wins the game, dialog is used to display custom win message
    public Scene endGameScene(String dialog) {
        Pane root = new Pane();
        root.getStylesheets().add("ingame.css");
        
        // Custom Dialog to show Winning Message
        DialogText t = new DialogText(dialog);        
        t.setLayoutY(120);
        
        // Restart and Exit Options
        Pane finalPane = new Pane();
        finalPane.setPrefSize(500, 180);
        finalPane.setLayoutX(0);
        finalPane.setLayoutY(260);
        
        // Restart and Exit Buttons (Using choice id of 5 & 6)
        ChoiceButton btn1 = new ChoiceButton(gc, "restart", 5, "Restart Game");
        ChoiceButton btn2 = new ChoiceButton(gc, "exit", 6, "Exit Game");
        finalPane.getChildren().addAll(btn1,btn2);
        
        // Exit Button
        ExitButton btnExit = new ExitButton();
        
        // Used to drag screen around
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        // Used to drag screen around
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        
        root.getChildren().addAll(t, finalPane, btnExit);
        
        root.setStyle("-fx-background-image: url(textadventure-ingame.png);");
        return new Scene(root);
    }
    
    public Scene inGameScene(){
        Pane root = new Pane();
        root.getStylesheets().add("ingame.css");
        
        // Game Controller
        gc = new GameController(this);
        
        // System Description
        HBox hb = new HBox();
        dialog = new DialogText("You are trying to make it to your bunker which is five miles away." +
                "You are proceeding on foot. You have your backpack with you which contains $50, water," +
                "and a knife. You come across a man with a pistol mugging a lady.");
        hb.getChildren().add(dialog);
        hb.setPrefSize(500, 280);
        hb.setAlignment(Pos.CENTER_LEFT);
        hb.setLayoutX(50);
        
        choicePane = new Pane();
        choicePane.setPrefSize(500, 180);
        choicePane.setLayoutX(0);
        choicePane.setLayoutY(220);
        
        ChoiceButton btn1 = new ChoiceButton(gc, "c1Sneak", 1, "Silently sneak up and knife the guy");
        ChoiceButton btn2 = new ChoiceButton(gc, "c1Resolve", 2, "Try to resolve situation peacefully");
        ChoiceButton btn3 = new ChoiceButton(gc, "c1Stop", 3, "Yell at the man to stop");
        ChoiceButton btn4 = new ChoiceButton(gc, "c1Check", 4, "Check Inventory");
        
        choicePane.getChildren().addAll(btn1,btn2,btn3,btn4);
        
        RestartButton restartBtn = new RestartButton(stage);
        ExitButton exitBtn = new ExitButton();
        
        // Used to drag screen around
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        // Used to drag screen around
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
        // Updates the dialog story text 
        dialog.setText(text);
    }
    public void updateChoices(String c1ID, String c1, String c2ID, String c2, String c3ID, String c3, String c4ID, String c4) {
        // Updates the ChoiceButtons with a new set of strings
        ChoiceButton choice1 = new ChoiceButton(gc, c1ID, 1, c1);
        ChoiceButton choice2 = new ChoiceButton(gc, c2ID, 2, c2);
        ChoiceButton choice3 = new ChoiceButton(gc, c3ID, 3, c3);
        ChoiceButton choice4 = new ChoiceButton(gc, c4ID, 4, c4);
        choicePane.getChildren().addAll(choice1, choice2, choice3, choice4);
    }
    public void setEndScene(String dialog) {
        // Called from story to show that the user has reached the end game (either failed or won)
        stage.setScene(endGameScene(dialog));
    }
    public Pane getChoicePane() {
        return this.choicePane;
    }
    public Stage getStage() {
        return this.stage;
    }
    
}
