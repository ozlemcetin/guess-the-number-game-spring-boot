package academy.learnprogramming.controller;

import academy.learnprogramming.service.GameService;
import academy.learnprogramming.util.AttributeNames;
import academy.learnprogramming.util.MappingNames;
import academy.learnprogramming.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class GameController {

    //== fields ==
    private final GameService gameService;

    //== constructors ==
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    //== request methods ==

    @GetMapping(MappingNames.PLAY_MAPPING)
    public String getPlay(Model model) {

        model.addAttribute(AttributeNames.MAIN_MESSAGE, gameService.getMainMessage());
        model.addAttribute(AttributeNames.RESULT_MESSAGE, gameService.getResultMessage());
        log.debug("model attributes set to  = {}", model);

        if (gameService.isGameOver()) {
            return ViewNames.GAME_OVER_VIEW;
        }

        return ViewNames.PLAY_VIEW;
    }

    @PostMapping(MappingNames.PLAY_MAPPING)
    public String processPlay(@RequestParam int guess) {

        log.debug("guess received = {}", guess);
        gameService.checkGuess(guess);

        return "redirect:/" + ViewNames.PLAY_VIEW;
    }

    @GetMapping(MappingNames.RESET_MAPPING)
    public String getReset() {

        gameService.resetGame();
        log.debug("game is reset");

        return "redirect:/" + ViewNames.PLAY_VIEW;
    }

}
