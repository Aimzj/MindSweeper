package za.co.bbd.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Board1 {
    private @Id Long id;
    public Integer[][] fields;
    public final Integer SIZE = 5;

    public Board1() {
        id = 1L;
        fields = new Integer[5][5];
        initBoard();
    }

    private void initBoard() {
        
        for(int r = 0; r < SIZE; r++) {
            for(int c = 0; c < SIZE; c++) {
                fields[r][c] = 0;
            }
        }
        fields[0][1] = 1;
    }
}
