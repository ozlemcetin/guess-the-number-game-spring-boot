package academy.learnprogramming.config.thymeleaf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class DecoupledLogicSetupForThymeleaf {

    // == fields ==
    private final SpringResourceTemplateResolver templateResolver;

    // ==constructor ==
    @Autowired
    public DecoupledLogicSetupForThymeleaf(SpringResourceTemplateResolver templateResolver) {
        this.templateResolver = templateResolver;
    }

    // == init methods ==
    @PostConstruct
    public void init() {
        templateResolver.setUseDecoupledLogic(true);
        log.info("decoupled template logic enabled");
    }
}
