package academy.learnprogramming.main;

import academy.learnprogramming.game.Game;
import academy.learnprogramming.game.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/*
    A: If a bean that implements the ApplicationListener interface is deployed into the context,
    every time an ApplicationEvent gets published to the Application Context, the bean is notified.
    This is the standard Observer design pattern.

    B: Instead of implementing the ApplicationListener interface, spring provides the @EventListener annotation
    that we can add under the method that's going to be executed once the event gets fired.
 */
@Slf4j
@Component
public class ConsoleNumberGuessContextListener {
    //implements ApplicationListener<ContextRefreshedEvent> {

    // == constants ==
    //private static final Logger log = LoggerFactory.getLogger(ConsoleNumberGuessContextListener.class);

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

    /*
        ContextRefreshedEvent is published when the application context  is initialized
        (all the beans are loaded, post processor beans are detected and activated,
        singletons are pre instantiated and the application context object is ready for use ) or refreshed
        for example using the refresh method on the configurable application context interface.
     */

    /*
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info(" ConsoleNumberGuess implementing ApplicationListener, onApplicationEvent(). Container ready for use. ");
    }
    */

    /*
        Event parameter is mandatory for event  listener method.
        Parameter type ( like ContextRefreshedEvent) determines the type of event:

        @EventListener
        public void myEventListenerMethod(ContextRefreshedEvent contextRefreshedEvent) {...}

         If the parameter is not used, we can add a event type to the annotation  @EventListener:

         @EventListener(ContextRefreshedEvent.class)
         public void myEventListenerMethod() {..}
     */

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
