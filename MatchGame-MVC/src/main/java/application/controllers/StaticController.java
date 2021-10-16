package application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {

    @GetMapping(value = {"/", "/index"})
    public String getHomePage() {
        return "index";}

    /*@GetMapping(value = { "/setup"})
    public String getSetupPage() {
        return "setup";
    }

    @GetMapping(value = { "/game"})
    public String getGamePage() {
        return "game";
    }*/

}
