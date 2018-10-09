package Minesweeper.GUI;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main1 extends Application {
    public static void main(String []args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception{
        try {
            stage.setResizable(false);
            stage.setTitle("Minesweeper");
            stage.setScene(GUI.MainMenu.display(stage));
            stage.show();
        }catch(Exception e){
            e.getMessage();
        }
    }
}
