package academy.learnprogramming.main;

import academy.learnprogramming.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainContextCreation {

    // == constants ==
    private static final Logger logger = LoggerFactory.getLogger(MainContextCreation.class);
    //private static final String BEAN_CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {

        logger.info("Guess The Number Game Main Method For Beans ");

        //create spring context (container)
        ConfigurableApplicationContext context
                // = new ClassPathXmlApplicationContext(BEAN_CONFIG_LOCATION);
                = new AnnotationConfigApplicationContext(AppConfig.class);

        {

            //Returning cached instance of singleton bean 'numberGenerator'
            //Singleton means there is only one instance of the bean in the entire application

            //get number generator bean from spring context (container)
            // NumberGenerator numberGenerator
            // = context.getBean("numberGenerator", NumberGenerator.class);
            // = context.getBean(NumberGenerator.class);

            //call method next()  to get a random number
            // int number = numberGenerator.next();

            //log generated number
            // logger.info("The number is {}", number);
            // logger.info("Main number generator's hash code= {}", numberGenerator.hashCode());

            //get game bean from spring context (container)
            // Game game = context.getBean(Game.class);

            //call reset()
            // game.reset();
        }

        {
            //get message bean from spring context (container)
            // MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);

            //logger.info("getMainMessage = {}", messageGenerator.getMainMessage());
            //logger.info("getResultMessage = {}", messageGenerator.getResultMessage());
        }

        //close spring context (container)
        context.close();
    }
}
