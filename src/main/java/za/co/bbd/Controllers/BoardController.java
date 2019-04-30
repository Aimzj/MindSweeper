package za.co.bbd.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import za.co.bbd.model.Board;

@Controller
public class BoardController {

    @GetMapping("/game")
    public String GetData(Model model){
        Board board = new Board();

        model.addAttribute("xsize", board.X_SIZE);
        model.addAttribute("ysize", board.Y_SIZE);

        model.addAttribute("Cells", board.Cells);

        return "game";
    }
}
