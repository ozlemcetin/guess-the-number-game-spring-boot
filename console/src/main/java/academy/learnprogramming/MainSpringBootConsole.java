package academy.learnprogramming;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MainSpringBootConsole {

    public static void main(String[] args) {

        log.info("Guess The Number Game Main Method");
        SpringApplication.run(MainSpringBootConsole.class, args);
    }
}
