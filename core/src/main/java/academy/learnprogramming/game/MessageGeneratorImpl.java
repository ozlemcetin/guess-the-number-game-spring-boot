package academy.learnprogramming.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageGeneratorImpl implements MessageGenerator {

    // == constants ==
    private static final Logger logger = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    // == fields ==
    private final Game game;

    // == constructor ==

    @Autowired
    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    // == init methods (bean lifecycle callbacks)==

    @PostConstruct
    public void postConstructor() {
        logger.info("MessageGeneratorImpl postConstructor() called. Game is {}", game);
    }

    // == public methods ==

    @Override
    public String getMainMessage() {

        StringBuilder builder = new StringBuilder();
        builder.append("Number is between ");
        builder.append(game.getSmallest());
        builder.append(" and ");
        builder.append(game.getBiggest());
        builder.append(". Can you guess it?");

        return builder.toString();
    }

    @Override
    public String getResultMessage() {

        //Return message based on the guess
        StringBuilder builder = new StringBuilder();

        //Check if the game is overall
        if (game.isGameWon() || game.isGameLost()) {

            builder.append("The game is over. ");
            builder.append("The number was ");
            builder.append(game.getNumber());
            builder.append(". ");

            if (game.isGameWon()) {
                builder.append("You guessed it! ");

            } else if (game.isGameLost()) {
                builder.append("The game is lost. ");
            }

            //First guess
        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            builder.append("What is your first guess? ");

        } else {

            //remaining guesses
            builder.append("You have ");
            builder.append(game.getRemainingGuesses());
            builder.append(" guesses left. ");

            //Is the guess in the valid range
            if (!game.isValidNumberRange()) {
                builder.append("Your guess is not in the valid range! ");

            } else {

                //Give direction tips
                String direction = "lower";
                if (game.getGuess() < game.getNumber()) {
                    direction = "higher";
                }

                builder.append("Tip: Go ");
                builder.append(direction);
                builder.append("! ");
            }

            //range
            builder.append("Range is [ " + game.getSmallest() + " , " + game.getBiggest() + "]. ");
        }

        return builder.toString();
    }
}
