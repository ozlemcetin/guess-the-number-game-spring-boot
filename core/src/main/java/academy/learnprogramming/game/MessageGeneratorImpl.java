package academy.learnprogramming.game;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    // == constants ==
    public static final String MESSAGE_PROPERTIES_KEY_MAIN_MESSAGE = "message.generator.main.message";

    // == fields ==
    private final Game game;
    private final MessageSource messageSource;

    // == constructor ==
    @Autowired
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    // == init methods (bean lifecycle callbacks)==
    @PostConstruct
    public void postConstructor() {
        log.info("MessageGeneratorImpl postConstructor() called. Game is {}", game);
    }

    // == public methods ==
    @Override
    public String getMainMessage() {

        /*
        StringBuilder builder = new StringBuilder();
        builder.append("Number is between ");
        builder.append(game.getSmallest());
        builder.append(" and ");
        builder.append(game.getBiggest());
        builder.append(". Can you guess it?");
        return builder.toString();
         */

        return getMessage(MESSAGE_PROPERTIES_KEY_MAIN_MESSAGE,
                game.getSmallest(), game.getBiggest());
    }

    @Override
    public String getResultMessage() {

        //Return message based on the guess
        StringBuilder builder = new StringBuilder();

        //Check if the game is overall
        if (game.isGameWon() || game.isGameLost()) {

            builder.append(getMessage("message.generator.number.was", game.getNumber()));

            if (game.isGameWon()) {
                builder.append(getMessage("message.generator.game.won"));

            } else if (game.isGameLost()) {
                builder.append(getMessage("message.generator.game.lost"));
            }

        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            //First guess
            builder.append(getMessage("message.generator.first.guess"));

        } else {

            //remaining guesses
            builder.append(getMessage("message.generator.remaining", game.getRemainingGuesses()));

            //Is the guess in the valid range
            if (!game.isValidNumberRange()) {
                builder.append(getMessage("message.generator.not.valid.range"));

            } else {

                //Give direction tips
                if (game.getGuess() < game.getNumber()) {
                    builder.append(getMessage("message.generator.tip.higher"));

                }else{
                    builder.append(getMessage("message.generator.tip.lower"));
                }
            }

            //range
            builder.append(getMessage("message.generator.range.is",
                    game.getSmallest() , game.getBiggest()));
        }

        return builder.toString();
    }

    //== private methods ==
    private String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
