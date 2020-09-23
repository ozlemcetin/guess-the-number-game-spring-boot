package academy.learnprogramming.game;

import academy.learnprogramming.annotations.GuessCount;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Getter
@Component
public class GameImpl implements Game {

    // == fields ==
    @Getter(AccessLevel.NONE)
    private final NumberGenerator numberGenerator;

    private final int guessCount;
    private int number;

    @Setter
    private int guess;

    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange;

    // == constructors ==
    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    // == init methods (bean lifecycle callbacks)==
    @PostConstruct
    @Override
    public void reset() {

        number = numberGenerator.next();
        guess = numberGenerator.getMinNumber();
        smallest = numberGenerator.getMinNumber();
        biggest = numberGenerator.getMaxNumber();
        remainingGuesses = guessCount;
        validNumberRange = true;

        log.debug("GameImpl reset() called.");
        log.info("GameImpl number generator's hash code= {}", numberGenerator.hashCode());
        log.info("GameImpl number is {}, guessCount = ", number, guessCount);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("GameImpl preDestroy() called.");
    }

    // == public methods ==
    @Override
    public void check() {

        checkValidNumberRange();

        if (isValidNumberRange()) {
            if (guess > number) {
                biggest = guess - 1;
            }
            if (guess < number) {
                smallest = guess + 1;
            }
        }

        remainingGuesses--;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    // == private methods ==
    private void checkValidNumberRange() {
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}
