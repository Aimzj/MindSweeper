import org.junit.Test;
import za.co.bbd.model.Board;
import za.co.bbd.model.Cell;

import static junit.framework.TestCase.*;

public class unitTests {

    @Test
    public void BoardNotNullTest(){
        Board board = new Board();

        for(int row=0; row<board.Y_SIZE; row++){
            for(int column=0; column<board.X_SIZE; column++){
                assertNotNull(board.Cells[row][column]);
            }
        }
    }

    @Test
    public void GameEndsOnBombClickTest(){
        Board board = new Board();

        //loop through the board until a bomb is found
        for(int row=0; row<board.Y_SIZE; row++){
            for(int column=0; column<board.X_SIZE; column++){

                board.openSpace(column, row);

                if(!board.Cells[row][column].isClicked){
                    if(board.Cells[row][column].getValue() == -1){
                        assertTrue(board.isEndGame);
                    }else {
                        assertFalse(board.isEndGame);
                    }
                }
            }
        }
    }
}
