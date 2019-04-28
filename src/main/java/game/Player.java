package game;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public
class Player {
    private @Id @GeneratedValue Long id;
    private String name;
    private Integer score;

    Player(String name, Integer score){
        this.name = name;
        this.score = score;
    }

}
