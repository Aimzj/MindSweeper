package za.co.bbd.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import za.co.bbd.db.BoardRepository;

import za.co.bbd.model.Board;
import za.co.bbd.model.Board1;

@Controller
public class BoardController {
    //private final BoardRepository repo;

    @GetMapping("/game")
    public String GetData(@RequestParam(name="row", required=false) Integer row, @RequestParam(name="col", required=false) Integer col, Model model){
        //Board board = new Board();
        Board1 b = null;
        if(row == null && col == null) {
            b = new Board1();
        } else {
            b = new Board1();   //get board from repository
            b.fields[row][col] = 2; //basic cell revealing 
        }
        //store board in repository
        //model.addAttribute("xsize", board.X_SIZE);
        //model.addAttribute("ysize", board.Y_SIZE);

        //model.addAttribute("Cells", board.Cells);

        model.addAttribute("board", b.fields);
        model.addAttribute("xsize", b.SIZE);
        model.addAttribute("ysize", b.SIZE);

        return "game";
    }



    @GetMapping("/about")
    public void about(){    
    }
    
}
