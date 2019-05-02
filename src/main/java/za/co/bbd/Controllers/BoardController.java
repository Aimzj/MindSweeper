package za.co.bbd.Controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.omg.PortableServer.THREAD_POLICY_ID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import antlr.collections.List;
import za.co.bbd.db.FakeGameRepository;
import za.co.bbd.db.Game;
import za.co.bbd.model.Board;

@Controller
public class BoardController {

    FakeGameRepository repository;

    BoardController(FakeGameRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/game/{gameId}")
    public String GetData(@PathVariable("gameId") String id, Model model) throws IllegalArgumentException {

        System.out.println("------------------------------------------------------>>>>>>>---------------");
        Game game = repository.findById(id);
        Board board = game.getBoard();

        model.addAttribute("xsize", board.X_SIZE);
        model.addAttribute("ysize", board.Y_SIZE);
        model.addAttribute("id", game.getId());
        model.addAttribute("isEnd", board.isEndGame);

        model.addAttribute("Cells", board.Cells);

        return "board";
    }

    @PostMapping("/game/{gameId}")
    public ResponseEntity PostData(@PathVariable("gameId") String id, @RequestParam int row, @RequestParam int column)
            throws IllegalArgumentException, InterruptedException {

        System.out.println("YAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAS");
        Game game = repository.findById(id);
        Board board = game.getBoard();

        board.openSpace(column, row);
        Thread.sleep(400);

        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @PutMapping("/game/{gameId}")
    public ResponseEntity PutFlag(@PathVariable("gameId") String id, @RequestParam int row, @RequestParam int column)
            throws IllegalArgumentException {

        System.out.println("YAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAS");
        Game game = repository.findById(id);
        Board board = game.getBoard();

        board.setFlag(column, row);

        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @GetMapping("/about")
    public void About() {
    }

    @GetMapping("/highscores")

    public String Highscores(Model model) {

        ArrayList<Game> games = repository.find();

        model.addAttribute("games", games);

        return "highscores";

    }

}
