package application.controllers;

import application.models.LoginForm;
import application.models.RegisterForm;
import application.services.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class WelcomeController {

    private WelcomeService service;

    @Autowired
    public WelcomeController(WelcomeService service) {
        this.service = service;
    }

    @GetMapping(value = {"/welcomeUser"})
    public String loadWelcomeUser(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {

        model.addAttribute("username", service.getNameWithText(loginForm));

        return "welcomeUser";
    }

}
