package za.co.bbd.model;

import java.util.Random;

public class Board {

    public Integer[][] Cells;

    public final Integer X_SIZE = 16;
    public final Integer Y_SIZE = 16;
    final Integer BOMB_CHANCE = 8;

    public Board(){
        Cells = new Integer[X_SIZE][Y_SIZE];

        InitialiseBoard();
    }

    private void InitialiseBoard(){
        //initialise grid with bombs placed randomly
        for(Integer x=0; x<X_SIZE; x++){
            for(Integer y=0; y<Y_SIZE; y++ ){
                Random rand = new Random();
                int cell = rand.nextInt(BOMB_CHANCE);
                if(cell == 1){
                    Cells[x][y]= -1;
                }
                else Cells[x][y]= 0;
            }
        }

        //move through grid checking for number of bombs surrounding each cell
        //set the cell status accordingly
        Integer bombCount;
        for(Integer x=0; x<X_SIZE; x++){
            for(Integer y=0; y<Y_SIZE; y++ ){
                //if cell isn't a bomb, cell status is updated
                if (Cells[x][y] != -1)
                {
                    //reset bomb count
                    bombCount=0;

                    //check above
                    if(y!=Y_SIZE-1){
                        if(Cells[x][y+1] == -1)
                            bombCount++;
                    }

                    //check below
                    if(y!=0){
                        if(Cells[x][y-1] == -1)
                            bombCount++;
                    }

                    //check left
                    if(x!=0){
                        if(Cells[x-1][y] == -1)
                            bombCount++;
                    }

                    //check right
                    if(x!=X_SIZE-1){
                        if(Cells[x+1][y] == -1)
                            bombCount++;
                    }

                    //check upper left
                    if(x!=0 && y!=0){
                        if(Cells[x-1][y-1] == -1)
                            bombCount++;
                    }

                    //check upper right
                    if(x!=X_SIZE-1 && y!=0){
                        if(Cells[x+1][y-1] == -1)
                            bombCount++;
                    }

                    //check lower left
                    if(x!=0 && y!=Y_SIZE-1){
                        if(Cells[x-1][y+1] == -1)
                            bombCount++;
                    }

                    //check lower right
                    if(x!=X_SIZE-1 && y!=Y_SIZE-1){
                        if(Cells[x+1][y+1] == -1)
                            bombCount++;
                    }

                    Cells[x][y] = bombCount;
                }

            }
        }
    }
}
