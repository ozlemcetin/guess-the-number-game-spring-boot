package academy.learnprogramming.game;

import academy.learnprogramming.annotations.MaxNumber;
import academy.learnprogramming.annotations.MinNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

//@Component("numberGenerator")
@Component
public class NumberGeneratorImpl implements NumberGenerator {

    // == fields ==
    private final int maxNumber;
    private final int minNumber;

    // == constructors ==
    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber,
                               @MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    // == public methods ==
    @Override
    public int next() {

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return ThreadLocalRandom.current().nextInt(minNumber, maxNumber + 1);
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }


    @Override
    public int getMinNumber() {
        return minNumber;
    }
}
