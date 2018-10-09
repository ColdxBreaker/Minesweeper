import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameBoard {
    // The game board that will hold all of the tiles
    private static GridPane grid = new GridPane();
    private static Scene scene = new Scene(grid, 500, 500); // 1 px offset per block
    private static Tile[][] tileBoard = new Tile[10][10];
    private static int mouseXPos;
    private static int mouseYPos;
    private static Alert gameOver = new Alert(Alert.AlertType.INFORMATION, "You clicked a mine. Game Over!");
    private static Stage stg;

    public static void setGameScreen(final Stage stage) {
        // Setting the game screen (The actual board game)
        stage.setScene(scene);
        stg = stage;

    }

    public static void mouseClickListeners() {
        // This will wait until the user clicks the mouse and will obtain the mouse coordinates
        grid.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Checking coordinates of the board
                System.out.println(Math.round(event.getSceneX()) / 50 + "," + Math.round(event.getSceneY()) / 50);
                mouseXPos = (int) (Math.round(event.getSceneX()) / 50);
                mouseYPos = (int) (Math.round(event.getSceneY()) / 50);
                if (!(tileBoard[mouseXPos][mouseYPos].isOpen())) {
                    onClick(mouseXPos, mouseYPos);
                }
                if (checkWin()) {
                    displayWin();
                }
            }
        });
    }

    public static void displayWin() {
        // Displaying an alert saying that the player has won
        Alert win = new Alert(Alert.AlertType.INFORMATION, "You Won!");
        win.showAndWait();
        stg.close();
    }

    public static boolean checkWin() {
        // Checking the entire board whether the play has won
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                if(!tileBoard[row][column].hasBomb()){
                    if(!tileBoard[row][column].isOpen()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static void onClick(int xCoord, int yCoord) {
        // Once the mouse button has been clicked, it will check whether it is a bomb and if it's not, it'll check
        // the other neighbouring values to determine the number of bombs around the area
        Rectangle rect = new Rectangle(49, 49, Color.GREY);
        int bombCount = 0;
        if (tileBoard[xCoord][yCoord].hasBomb()) {
            System.out.println("Bomb");
            grid.add((new ImageView("/Images/mines.png")), xCoord, yCoord);
            gameOver.showAndWait();
            stg.close();
        } else {
            if (xCoord == 0 && yCoord == 0) {
                for (int row = yCoord; row < yCoord + 2; row++) {
                    for (int column = xCoord; column < xCoord + 2; column++) {
                        if (tileBoard[column][row].hasBomb()) {
                            bombCount++;
                        } else {
                            checkNeighbours(column, row);
                        }
                    }
                }
            } else if (xCoord == 9 && yCoord == 0) {
                for (int row = yCoord; row < yCoord + 2; row++) {
                    for (int column = xCoord - 1; column < xCoord + 1; column++) {
                        if (tileBoard[column][row].hasBomb()) {
                            bombCount++;
                        } else {
                            checkNeighbours(column, row);
                        }
                    }
                }
            } else if (xCoord == 9 && yCoord == 9) {
                for (int row = yCoord - 1; row < yCoord + 1; row++) {
                    for (int column = xCoord - 1; column < xCoord + 1; column++) {
                        if (tileBoard[column][row].hasBomb()) {
                            bombCount++;
                        } else {
                            checkNeighbours(column, row);
                        }
                    }
                }
            } else if (xCoord == 0 && yCoord == 9) {
                for (int row = yCoord - 1; row < yCoord + 1; row++) {
                    for (int column = xCoord; column < xCoord + 2; column++) {
                        if (tileBoard[column][row].hasBomb()) {
                            bombCount++;
                        } else {
                            checkNeighbours(column, row);
                        }
                    }
                }
            } else if (xCoord == 0) {
                for (int row = yCoord - 1; row < yCoord + 2; row++) {
                    for (int column = xCoord; column < xCoord + 2; column++) {
                        if (tileBoard[column][row].hasBomb()) {
                            bombCount++;
                        } else {
                            checkNeighbours(column, row);
                        }
                    }
                }
            } else if (xCoord == 9) {
                for (int row = yCoord - 1; row < yCoord + 2; row++) {
                    for (int column = xCoord - 1; column < xCoord + 1; column++) {
                        if (tileBoard[column][row].hasBomb()) {
                            bombCount++;
                        } else {
                            checkNeighbours(column, row);
                        }
                    }
                }
            } else if (yCoord == 0) {
                for (int row = yCoord; row < yCoord + 2; row++) {
                    for (int column = xCoord - 1; column < xCoord + 2; column++) {
                        if (tileBoard[column][row].hasBomb()) {
                            bombCount++;
                        } else {
                            checkNeighbours(column, row);
                        }
                    }
                }
            } else if (yCoord == 9) {
                for (int row = yCoord - 1; row < yCoord + 1; row++) {
                    for (int column = xCoord - 1; column < xCoord + 2; column++) {
                        if (tileBoard[column][row].hasBomb()) {
                            bombCount++;
                        } else {
                            checkNeighbours(column, row);
                        }
                    }
                }
            } else {
                for (int row = yCoord - 1; row < yCoord + 2; row++) {
                    for (int column = xCoord - 1; column < xCoord + 2; column++) {
                        if (tileBoard[column][row].hasBomb()) {
                            bombCount++;
                        } else {
                            checkNeighbours(column, row);
                        }
                    }
                }
            }
            if (bombCount > 0) {
                // This will display a text for the number of bombs in the area
                Text text = new Text("      " + Integer.toString(bombCount));
                grid.add(rect, xCoord, yCoord);
                grid.add(text, xCoord, yCoord);
            }
            if (bombCount == 0) {
                // If there are no bombs, then a regular grey rectangle will be generated
                grid.add(rect, xCoord, yCoord);
            }
        }
        // Setting the state of the tile such that it is open
        tileBoard[xCoord][yCoord].setIsOpen(true);


    }

    public static void checkNeighbours(int xCoord, int yCoord) {
        // This will check if the neighbours have numbers and if they don't then it will be displayed
        Rectangle rect = new Rectangle(49, 49, Color.GREY);
        int bombCount = 0;
        if (xCoord == 0 && yCoord == 0) {
            for (int row = yCoord; row < yCoord + 2; row++) {
                for (int column = xCoord; column < xCoord + 2; column++) {
                    if (tileBoard[column][row].hasBomb()) {
                        bombCount++;
                    }
                }
            }
        } else if (xCoord == 9 && yCoord == 0) {
            for (int row = yCoord; row < yCoord + 2; row++) {
                for (int column = xCoord - 1; column < xCoord + 1; column++) {
                    if (tileBoard[column][row].hasBomb()) {
                        bombCount++;
                    }
                }
            }
        } else if (xCoord == 9 && yCoord == 9) {
            for (int row = yCoord - 1; row < yCoord + 1; row++) {
                for (int column = xCoord - 1; column < xCoord + 1; column++) {
                    if (tileBoard[column][row].hasBomb()) {
                        bombCount++;
                    }
                }
            }
        } else if (xCoord == 0 && yCoord == 9) {
            for (int row = yCoord - 1; row < yCoord + 1; row++) {
                for (int column = xCoord; column < xCoord + 2; column++) {
                    if (tileBoard[column][row].hasBomb()) {
                        bombCount++;
                    }
                }
            }
        } else if (xCoord == 0) {
            for (int row = yCoord - 1; row < yCoord + 2; row++) {
                for (int column = xCoord; column < xCoord + 2; column++) {
                    if (tileBoard[column][row].hasBomb()) {
                        bombCount++;
                    }
                }
            }
        } else if (xCoord == 9) {
            for (int row = yCoord - 1; row < yCoord + 2; row++) {
                for (int column = xCoord - 1; column < xCoord + 1; column++) {
                    if (tileBoard[column][row].hasBomb()) {
                        bombCount++;
                    }
                }
            }
        } else if (yCoord == 0) {
            for (int row = yCoord; row < yCoord + 2; row++) {
                for (int column = xCoord - 1; column < xCoord + 2; column++) {
                    if (tileBoard[column][row].hasBomb()) {
                        bombCount++;
                    }
                }
            }
        } else if (yCoord == 9) {
            for (int row = yCoord - 1; row < yCoord + 1; row++) {
                for (int column = xCoord - 1; column < xCoord + 2; column++) {
                    if (tileBoard[column][row].hasBomb()) {
                        bombCount++;
                    }
                }
            }
        } else {
            for (int row = yCoord - 1; row < yCoord + 2; row++) {
                for (int column = xCoord - 1; column < xCoord + 2; column++) {
                    if (tileBoard[column][row].hasBomb()) {
                        bombCount++;
                    }
                }
            }
        }
        if (bombCount > 0) {
            Text text = new Text("      " + Integer.toString(bombCount));
            grid.add(rect, xCoord, yCoord);
            grid.add(text, xCoord, yCoord);
        }
        if (bombCount == 0) {
            grid.add(rect, xCoord, yCoord);
        }
        tileBoard[xCoord][yCoord].setIsOpen(true);

    }


    public static void createBoard() {
        // This will generate the board with all of the grids
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                Rectangle rect = new Rectangle(49, 49, Color.WHITE);
                rect.setStroke(Paint.valueOf("000000"));
                grid.add(rect, row, column);
            }
        }
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                tileBoard[row][column] = new Tile(false, row, column, Math.random() < 0.1);
            }
        }
    }
}
