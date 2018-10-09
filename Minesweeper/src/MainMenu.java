
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu {
    // This is the main menu part of the game
    public static Stage stg;
    public static Scene display(final Stage stage) {
        // Showing the display of the images and the buttons
        stg = stage;
        final Text title = new Text("Minesweeper");
        final Button play = new Button("Play");
        final Pane pane = new Pane();
        final ImageView background = new ImageView();
        background.setImage(new Image("/Images/background.png"));
        background.setFitHeight(500);
        background.setFitWidth(500);
        pane.getChildren().addAll(
                background,
                title,
                play
        );
        title.setFont(Font.font("Verdana", 50));
        title.setFill(Paint.valueOf("FFFF00"));
        // Setting the coordinates for the buttons and the title
        play.setPrefWidth(100);
        play.setPrefHeight(50);
        play.setLayoutX(200);
        play.setLayoutY(200);
        title.setLayoutX(100);
        title.setLayoutY(100);
        // Setting an event handler when the button is clicked
        play.setOnAction(event -> {
                    GameBoard.createBoard();
                    GameBoard.mouseClickListeners();
                    GameBoard.setGameScreen(stage);
                }
        );
        // Returns a new scene to be displayed on the stage
        return new Scene(pane, 492, 500);

    }
}
