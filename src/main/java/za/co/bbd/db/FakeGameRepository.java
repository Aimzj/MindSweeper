package za.co.bbd.db;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FakeGameRepository {
    Map<String,Game> games = new HashMap<String,Game>();

    public Game save(Game game)
    {
        if(game.getId() == null)
        {
            String id = UUID.randomUUID().toString();
            game.setId(id);
            games.put(id,game);
        }
        else
        {
            games.replace(game.getId(),game);
        }
        return game;
    }

    public Game findById(String id)
    {
        return games.get(id);
    }




}
