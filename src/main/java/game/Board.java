package game;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Random;

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

    @ModelAttribute("Board")
    private CellStatus[][] GetBoard(){
        return Cells;
    }
}
