package za.co.bbd.model;

import lombok.Data;

import java.util.Random;

@Data
public class Board {

    public Cell[][] Cells;

    public final Integer X_SIZE = 16;
    public final Integer Y_SIZE = 16;
    public final Integer NUM_BOMBS = 25;
    final Integer BOMB_CHANCE = 8;
    final Integer TOTAL_CELLS = X_SIZE*Y_SIZE;

    private Integer totalClicked;

    public Boolean isEndGame;

    public Boolean hasWonGame;

    public Board() {
        Cells = new Cell[Y_SIZE][X_SIZE];
        isEndGame = false;
        hasWonGame = false;
        totalClicked = 0;
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

    public void openSpace(int column, int row) {
        System.out.println(column + "-------------");
        System.out.println(row + "-------------");
        if (!Cells[row][column].isClicked()) {
            if (Cells[row][column].getValue() == -1) {
                // End Game
                Cells[row][column].setClicked(true);
                setBombsClicked();
                isEndGame = true;
            }else if (Cells[row][column].getValue() == 0) {
                // DisplayBehind
                Cells[row][column].setClicked(true);
                // Open Neighbours
                blankClicked(column, row);
                totalClicked++;
            } else {
                // Display behind
                Cells[row][column].setClicked(true);
                totalClicked++;
            }
        }

        if(hasWon())
        {
            hasWonGame = true;
            System.out.println("YOU WON!");
        }

    }

    private Boolean hasWon(){
        if(TOTAL_CELLS - totalClicked == NUM_BOMBS){
            return true;
        }else {
            return false;
        }
    }

    private void OpenSurroundingNumberedBlocks(int column, int row){
        // check above
        if (row != Y_SIZE - 1) {
            if (Cells[row + 1][column].getValue() >0){
                if(!Cells[row+1][column].isClicked){
                    Cells[row+1][column].setClicked(true);
                    totalClicked++;
                }

            }
        }

        // check below
        if (row != 0) {
            if (Cells[row - 1][column].getValue() >0){
                if(!Cells[row-1][column].isClicked){
                    Cells[row-1][column].setClicked(true);
                    totalClicked++;
                }

            }
        }

        // check left
        if (column != 0) {
            if (Cells[row][column - 1].getValue() >0){
                if(!Cells[row][column-1].isClicked){
                    Cells[row][column-1].setClicked(true);
                    totalClicked++;
                }

            }
        }

        // check right
        if (column != X_SIZE - 1) {
            if (Cells[row][column + 1].getValue() >0){
                if(!Cells[row][column+1].isClicked){
                    Cells[row][column+1].setClicked(true);
                    totalClicked++;
                }

            }
        }

        // check upper left
        if (column != 0 && row != 0) {
            if (Cells[row - 1][column - 1].getValue() >0){
                if(!Cells[row-1][column-1].isClicked){
                    Cells[row-1][column-1].setClicked(true);
                    totalClicked++;
                }

            }
        }

        // check upper right
        if (column != X_SIZE - 1 && row != 0) {
            if (Cells[row - 1][column + 1].getValue() >0){
                if(!Cells[row-1][column+1].isClicked){
                    Cells[row-1][column+1].setClicked(true);
                    totalClicked++;
                }

            }
        }

        // check lower left
        if (column != 0 && row != Y_SIZE - 1) {
            if (Cells[row + 1][column - 1].getValue() >0){
                if(!Cells[row+1][column-1].isClicked){
                    Cells[row+1][column-1].setClicked(true);
                    totalClicked++;
                }

            }
        }

        // check lower right
        if (column != X_SIZE - 1 && row != Y_SIZE - 1) {
            if (Cells[row + 1][column + 1].getValue() >0){
                if(!Cells[row+1][column+1].isClicked){
                    Cells[row+1][column+1].setClicked(true);
                    totalClicked++;
                }

            }
        }
    }

    public void blankClicked(int column, int row){
        //check for unclicked number boxes in surrounding space and open them
        OpenSurroundingNumberedBlocks(column, row);

        //check for any hidden empty blocks that haven't been clicked
        // check above
        if (row != Y_SIZE - 1) {
            if (Cells[row + 1][column].getValue() ==0){
                if(!Cells[row+1][column].isClicked){
                    Cells[row+1][column].setClicked(true);
                    totalClicked++;
                    blankClicked(column, row+1);

                }

            }
        }

        // check below
         if (row != 0) {
            if (Cells[row - 1][column].getValue() ==0){
                if(!Cells[row-1][column].isClicked){
                    Cells[row-1][column].setClicked(true);
                    totalClicked++;
                    blankClicked(column, row-1);

                }

            }
        }

        // check left
         if (column != 0) {
            if (Cells[row][column - 1].getValue() ==0){
                if(!Cells[row][column-1].isClicked){
                    Cells[row][column-1].setClicked(true);
                    totalClicked++;
                    blankClicked(column-1, row);
                }

            }
        }

        // check right
         if (column != X_SIZE - 1) {
            if (Cells[row][column + 1].getValue() ==0){
                if(!Cells[row][column+1].isClicked){
                    Cells[row][column+1].setClicked(true);
                    totalClicked++;
                    blankClicked(column+1, row);
                }

            }
        }

    }

   /* public void openNeighbours(int column, int row) {

        for (int c = -1; c < 2; c++) {
            for (int r = -1; r < 2; r++) {

                // Check if within bounds
                if (column + c >= 0 && column + c < X_SIZE) {
                    if (row + r >= 0 && row + r < Y_SIZE) {
                        if (Math.abs(column) != Math.abs(row))
                            openSpace(column + c, row + r);
                    }
                }
            }
        }
    }*/

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
