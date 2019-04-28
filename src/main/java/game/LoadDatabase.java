package game;

import lombok.extern.slf4j.Slf4j;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(PlayerRepository repository){
        return args -> {
            log.info("Preloading " + repository.save(new Player("Aimee Handley", 9001)));
            log.info("Preloading " + repository.save(new Player("Ronan Constantine", 12)));
        };
    }
}
