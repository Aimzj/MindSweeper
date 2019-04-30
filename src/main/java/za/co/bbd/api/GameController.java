package za.co.bbd.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import za.co.bbd.db.Game;
import za.co.bbd.db.GameRepository;
import za.co.bbd.model.GameCheckpoint;

import javax.xml.ws.WebServiceException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("games")
public class GameController {
    private static final Logger LOG = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/index")
    public String index(){
        return "index";
    }


    @PostMapping("/start")
    public Long recordStartDate(GameCheckpoint checkpoint){
        try {
            LOG.debug(checkpoint.toString());

            Game game = new Game(null, checkpoint.getUser(), Instant.now(), null);

            game = gameRepository.save(game);

            LOG.info("Date has been saved for user {}", checkpoint.getUser());
            return game.getId();
        }catch (Exception e){
            LOG.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/")
    public List<Game> showAllGames(){
        List<Game> games = new ArrayList<>();
        Iterable<Game> dbGames = gameRepository.findAll();
        if(dbGames == null){
            return games;
        }
        dbGames.forEach(games::add);
        return games;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> showCurrentGame(@PathVariable("id") Long gmaeId){
        Optional<Game> dbGames = gameRepository.findById(gmaeId);
        if(dbGames != null && dbGames.isPresent()){
            return new ResponseEntity<>(dbGames.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}/end")
    public ResponseEntity<Game> recordEndDate(@PathVariable("id") Long gameId, GameCheckpoint checkpoint){
        Optional<Game> opGame = gameRepository.findById(gameId);

        if(opGame != null && opGame.isPresent()){
            Game game= opGame.get();
            game.setEndTime(Instant.now());
            gameRepository.save(game);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
