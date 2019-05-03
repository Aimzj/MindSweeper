package za.co.bbd.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import za.co.bbd.db.FakeGameRepository;
import za.co.bbd.db.Game;

import java.time.Instant;

@RestController
@RequestMapping("games")
public class StartGameController {
    private static final Logger LOG = LoggerFactory.getLogger(StartGameController.class);

    @Autowired
    private FakeGameRepository gameRepository;

    //creates game and redirects to /game/{id}
    @PostMapping("/start")
    public RedirectView recordStartDate(@RequestParam String username){
        try {
            LOG.debug(username.toString());

            Game game = new Game(username, Instant.now());

            game =gameRepository.save(game);

            LOG.info("Date has been saved for user {}", username);

            String gameId = game.getId();
            return new RedirectView("/game/"+ gameId);
        }catch (Exception e){
            LOG.error(e.getMessage(), e);
            throw e;
        }
    }
}
