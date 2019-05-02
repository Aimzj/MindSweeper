package za.co.bbd.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import za.co.bbd.db.FakeGameRepository;
import za.co.bbd.db.Game;
import za.co.bbd.model.Board;

@Controller
public class BoardController {

    FakeGameRepository repository;
    BoardController(FakeGameRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/game/{gameId}")
    public String GetData(@PathVariable("gameId") String id,Model model)throws IllegalArgumentException{

        System.out.println("------------------------------------------------------>>>>>>>---------------");
        Game game = repository.findById(id);
        Board board = game.getBoard();

        model.addAttribute("xsize", board.X_SIZE);
        model.addAttribute("ysize", board.Y_SIZE);
        model.addAttribute("id", game.getId());

        model.addAttribute("Cells", board.Cells);

        return "board";
    }

    @PostMapping("/game/{gameId}")
    public RedirectView PostData(@PathVariable("gameId") String id, @RequestParam int row, @RequestParam int column)throws IllegalArgumentException{

        System.out.println("YAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAS");
        Game game = repository.findById(id);
        Board board = game.getBoard();

        board.openSpace(column, row);

        return new RedirectView("/game/"+id);
    }

    @GetMapping("/about")
    public void About(){    
    }

    @GetMapping("/highscores")
    public void Highscores(){    
    }
    
}
