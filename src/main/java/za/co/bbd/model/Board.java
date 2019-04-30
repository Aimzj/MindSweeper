package za.co.bbd.model;

import java.util.Random;
import za.co.bbd.model.CellStatus;

public class Board {

    CellStatus[][] Cells;

    final Integer X_SIZE = 16;
    final Integer Y_SIZE = 16;
    final Integer BOMB_CHANCE = 16;

    public Board(){
        Cells = new CellStatus[X_SIZE][Y_SIZE];

        InitialiseBoard();
    }

    private void InitialiseBoard(){
        //initialise grid with bombs placed randomly
        for(Integer x=0; x<X_SIZE; x++){
            for(Integer y=0; y<Y_SIZE; y++ ){
                Random rand = new Random();
                int cell = rand.nextInt(BOMB_CHANCE);
                if(cell == 1){
                    Cells[x][y]=CellStatus.BOMB;
                }
            }
        }

        //move through grid checking for number of bombs surrounding each cell
        //set the cell status accordingly
        Integer bombCount;
        for(Integer x=0; x<X_SIZE; x++){
            for(Integer y=0; y<Y_SIZE; y++ ){
                //if cell isn't a bomb, cell status is updated
                if (Cells[x][y] != CellStatus.BOMB)
                {
                    //reset bomb count
                    bombCount=0;

                    //check above
                    if(y!=Y_SIZE-1){
                        if(Cells[x][y+1] == CellStatus.BOMB)
                            bombCount++;
                    }

                    //check below
                    if(y!=0){
                        if(Cells[x][y-1] == CellStatus.BOMB)
                            bombCount++;
                    }

                    //check left
                    if(x!=0){
                        if(Cells[x-1][y] == CellStatus.BOMB)
                            bombCount++;
                    }

                    //check right
                    if(x!=X_SIZE-1){
                        if(Cells[x+1][y] == CellStatus.BOMB)
                            bombCount++;
                    }

                    //check upper left
                    if(x!=0 && y!=0){
                        if(Cells[x-1][y-1] == CellStatus.BOMB)
                            bombCount++;
                    }

                    //check upper right
                    if(x!=X_SIZE-1 && y!=0){
                        if(Cells[x+1][y-1] == CellStatus.BOMB)
                            bombCount++;
                    }

                    //check lower left
                    if(x!=0 && y!=Y_SIZE-1){
                        if(Cells[x-1][y+1] == CellStatus.BOMB)
                            bombCount++;
                    }

                    //check lower right
                    if(x!=X_SIZE-1 && y!=Y_SIZE-1){
                        if(Cells[x+1][y+1] == CellStatus.BOMB)
                            bombCount++;
                    }

                    Cells[x][y] = CellStatus.values()[bombCount];
                }

            }
        }
    }

    public void openSpace(int x, int y){
        //TODO: add a variable to check if space has already been opened
        if(Cells[y][x] == CellStatus.BOMB){
            //End Game
        }
        else if(Cells[y][x] == CellStatus.EMPTY){
            //DisplayBehind
            //Open Neightbours
        }
        else{
            //Display behind
        }
    }

    public void openNeighbours(int x,int y){

        for(int i = -1;i<2;i++){
            for(int j = -1;j<2;j++){

                //Check if within bounds
                if(x+i>0 && x+i<X_SIZE){
                    if(y+j>0 && y+j<Y_SIZE){
                        openSpace(x+i,y+j);
                    }
                }
            }
        }
    }

}
