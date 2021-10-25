package application.controllers;

import application.models.LoginForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping(value = {"/login"})
    public String loadLogin(@ModelAttribute("loginForm") LoginForm loginForm) {

        return "login";
    }

    @PostMapping("/login")
    public String sendForm(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {


        model.addAttribute("username", loginForm.getUsername());
        model.addAttribute("password", loginForm.getPassword());

        // validate fields,  if success -> welcome



        return "welcomeUser";
    }


}
