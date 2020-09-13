package academy.learnprogramming.config;

import academy.learnprogramming.annotations.GuessCount;
import academy.learnprogramming.annotations.MaxNumber;
import academy.learnprogramming.annotations.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    /*
        @Value("${number.generator.maxNumber:20}")
        Default value ise 50.
     */

    // == fields ==
    @Value("${number.generator.maxNumber:50}")
    private int maxNumber;
    // = 50;

    @Value("${number.generator.minNumber:35}")
    private int minNumber;
    // = 35;

    @Value("${game.guessCount:3}")
    private int guessCount;
    // = 3;

    // == bean methods ==
    @Bean
    @MaxNumber
    public int maxNumberForNumberGenerator() {
        return maxNumber;
    }

    @Bean
    @MinNumber
    public int minNumberForNumberGenerator() {
        return minNumber;
    }

    @Bean
    @GuessCount
    public int guessCountForGame() {
        return guessCount;
    }
}
