package GUI;

import javafx.scene.layout.StackPane;

public class Tile extends StackPane {
    // The tile object
    private boolean isOpen;
    private boolean hasBomb;

    public Tile(boolean isOpen, int xCoord, int yCoord, boolean hasBomb) {
        // The constructor of the title of the game
        this.isOpen = isOpen;
        this.hasBomb = hasBomb;
        setTranslateX(xCoord);
        setTranslateY(yCoord);
    }

    public boolean isOpen() {
        // Set whether the tile is open
        return isOpen;
    }
    public boolean hasBomb(){
        // Set if the tile contains a bomb
        return hasBomb;
    }
    public void setIsOpen(boolean isOpen){
        // Set if the tile is open or not
        this.isOpen = isOpen;
    }
}
