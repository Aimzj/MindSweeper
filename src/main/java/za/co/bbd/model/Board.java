package za.co.bbd.model;

import java.util.Random;

public class Board {

    public Cell[][] Cells;

    public final Integer X_SIZE = 16;
    public final Integer Y_SIZE = 16;
    public final Integer NUM_BOMBS = 25;
    final Integer BOMB_CHANCE = 8;

    public Boolean isEndGame;

    public Board() {
        Cells = new Cell[Y_SIZE][X_SIZE];
        isEndGame = false;
        InitialiseBoard();
    }

    private void SpawnBombs(){
        //add bombs to board
        Random rand = new Random();
        int randomRow = rand.nextInt(Y_SIZE);
        int randomColumn = rand.nextInt(X_SIZE);
        for(int i=0; i<NUM_BOMBS; i++){

            while(Cells[randomRow][randomColumn].getValue()== -1){
                rand = new Random();
                randomRow = rand.nextInt(Y_SIZE);
                randomColumn = rand.nextInt(X_SIZE);
            }
            Cells[randomRow][randomColumn].setValue(-1);
        }
    }

    private void InitialiseBoard() {
        // initialise grid to be all empty
        for (Integer x = 0; x < X_SIZE; x++) {
            for (Integer y = 0; y < Y_SIZE; y++) {
                Cell cell = new Cell();
                cell.setClicked(false);
                cell.setValue(0);
                Cells[y][x] = cell;
            }
        }

        SpawnBombs();

        // move through grid checking for number of bombs surrounding each cell
        // set the cell status accordingly
        Integer bombCount;
        for (Integer x = 0; x < X_SIZE; x++) {
            for (Integer y = 0; y < Y_SIZE; y++) {
                // if cell isn't a bomb, cell status is updated
                if (Cells[y][x].getValue() != -1) {
                    // reset bomb count
                    bombCount = 0;

                    // check above
                    if (y != Y_SIZE - 1) {
                        if (Cells[y + 1][x].getValue() == -1)
                            bombCount++;
                    }

                    // check below
                    if (y != 0) {
                        if (Cells[y - 1][x].getValue() == -1)
                            bombCount++;
                    }

                    // check left
                    if (x != 0) {
                        if (Cells[y][x - 1].getValue() == -1)
                            bombCount++;
                    }

                    // check right
                    if (x != X_SIZE - 1) {
                        if (Cells[y][x + 1].getValue() == -1)
                            bombCount++;
                    }

                    // check upper left
                    if (x != 0 && y != 0) {
                        if (Cells[y - 1][x - 1].getValue() == -1)
                            bombCount++;
                    }

                    // check upper right
                    if (x != X_SIZE - 1 && y != 0) {
                        if (Cells[y - 1][x + 1].getValue() == -1)
                            bombCount++;
                    }

                    // check lower left
                    if (x != 0 && y != Y_SIZE - 1) {
                        if (Cells[y + 1][x - 1].getValue() == -1)
                            bombCount++;
                    }

                    // check lower right
                    if (x != X_SIZE - 1 && y != Y_SIZE - 1) {
                        if (Cells[y + 1][x + 1].getValue() == -1)
                            bombCount++;
                    }

                    Cells[y][x].setValue(bombCount);
                }

            }
        }
    }

    public void setFlag(int x, int y) {
        Cells[y][x].setFlag(!Cells[y][x].flag);
    }

    public void openSpace(int x, int y) {
        // TODO: add a variable to check if space has already been opened
        System.out.println(x + "-------------");
        System.out.println(y + "-------------");
        if (!Cells[y][x].isClicked()) {
            if (Cells[y][x].getValue() == -1) {
                // End Game
                Cells[y][x].setClicked(true);
                setBombsClicked();
                isEndGame = true;
            } else if (Cells[y][x].getValue() == 0) {
                // DisplayBehind
                Cells[y][x].setClicked(true);
                // Open Neighbours
                openNeighbours(x, y);
            } else {
                // Display behind
                Cells[y][x].setClicked(true);
            }
        }

    }

    public void openNeighbours(int x, int y) {

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {

                // Check if within bounds
                if (x + i >= 0 && x + i < X_SIZE) {
                    if (y + j >= 0 && y + j < Y_SIZE) {
                        if (Math.abs(x) != Math.abs(y))
                            openSpace(x + i, y + j);
                    }
                }
            }
        }
    }

    public void setBombsClicked() {
        for (Integer x = 0; x < X_SIZE; x++) {
            for (Integer y = 0; y < Y_SIZE; y++) {
                if (Cells[y][x].getValue() == -1) {
                    Cells[y][x].isClicked = true;
                }
            }
        }
    }

}
