package game;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
class PlayerController {

    private final PlayerRepository repository;

    PlayerController(PlayerRepository repository){
        this.repository = repository;
    }

    @GetMapping("/players")
    Resources<Resource<Player>> all(){
        List<Resource<Player>> players = repository.findAll().stream()
                .map(employee -> new Resource<>(employee,
                        linkTo(methodOn(PlayerController.class).one(employee.getId())).withSelfRel(),
                        linkTo(methodOn(PlayerController.class).all()).withRel("players")))
                .collect(Collectors.toList());

        return new Resources<>(players,
                linkTo(methodOn(PlayerController.class).all()).withSelfRel());
    }

    @PostMapping("/players")
    Player newPlayer(@RequestBody Player newPlayer){
        return repository.save(newPlayer);
    }

    @GetMapping("/players/{id}")
    Resource<Player> one(@PathVariable Long id) {
        Player player = repository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));

        return new Resource<>(player,
                linkTo(methodOn(PlayerController.class).one(id)).withSelfRel(),
                linkTo(methodOn(PlayerController.class).all()).withRel("players"));
    }

    @PutMapping("/players/{id}")
    Player replacePlayer(@RequestBody Player newPlayer, @PathVariable Long id) {

        return repository.findById(id)
                .map(player -> {
                    player.setName(newPlayer.getName());
                    player.setScore(newPlayer.getScore());
                    return repository.save(player);
                })
                .orElseGet(() -> {
                    newPlayer.setId(id);
                    return repository.save(newPlayer);
                });
    }

    @DeleteMapping("/players/{id}")
    void deletePlayer(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
