package academy.learnprogramming.console;

import academy.learnprogramming.game.Game;
import academy.learnprogramming.game.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Slf4j
@Component
public class ConsoleNumberGuessContextListener {

    // == fields ==
    private final Game game;
    private final MessageGenerator messageGenerator;


    // == constructors ==
    @Autowired
    public ConsoleNumberGuessContextListener(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    // == events ==
    @EventListener(ContextRefreshedEvent.class)
    public void myEventListenerMethod() {

        log.info("Container ready for use.");

        /*
            Loop breaks when the player stops the game.
            The player will get a message for playing again once
            the game is won or lost.
            For reading user input , Scanner class is used.
         */
        Scanner scanner = new Scanner(System.in);

        //game start messages
        System.out.println(messageGenerator.getMainMessage());
        System.out.println(messageGenerator.getResultMessage());

        while (true) {

            //read the guess
            int guess = scanner.nextInt();
            //to handle the enter key
            scanner.nextLine();

            //set guess and check
            game.setGuess(guess);
            game.check();

            //game continue messages
            System.out.println(messageGenerator.getResultMessage());

            if (game.isGameWon() || game.isGameLost()) {

                System.out.println("Would you like to play again (Y/N)? ");
                String playAgain = scanner.nextLine().trim();

                if (!"Y".equalsIgnoreCase(playAgain)) {
                    break;

                } else {

                    //game reset
                    game.reset();

                    //game start messages
                    System.out.println(messageGenerator.getMainMessage());
                    System.out.println(messageGenerator.getResultMessage());
                }
            }
        } //while loop

    }
}
