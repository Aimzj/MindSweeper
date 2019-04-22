package game;

import model.Board;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        Board board = new Board();
        SpringApplication.run(Application.class, args);
    }

}