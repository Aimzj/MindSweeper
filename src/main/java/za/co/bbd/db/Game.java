package za.co.bbd.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import za.co.bbd.model.Board;

import java.time.Instant;
import java.time.Duration;

@Getter
@Setter
@AllArgsConstructor
public class Game {

    private String id;

    private String username;

    private Instant startTime;

    private Instant endTime;

    private Duration score;

    private final Board board;

    public Game()
    {
        board = new Board(); 
    }

    public Game(String username,Instant startInstant)
    {
        this.username = username;
        this.startTime = startInstant;
        board = new Board(); 

        //save in repo

    }
    
    public void calculateScore()
    {
        if(endTime != null)
        {
            score= Duration.between(endTime, startTime);
        }
        
    }
}


