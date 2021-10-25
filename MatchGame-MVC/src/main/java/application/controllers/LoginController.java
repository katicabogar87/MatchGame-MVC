package application.controllers;

import application.models.LoginForm;

import application.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private UserController userController;


    @Autowired
    public LoginController(UserController userController) {
        this.userController = userController;
    }

    @GetMapping(value = {"/login"})
    public String loadLogin(@ModelAttribute("loginForm") LoginForm loginForm) {

        return "login";
    }

    @PostMapping("/login")
    public String sendForm(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {


       if (validateUser(loginForm)){return "welcomeUser";}


        model.addAttribute("loginError", "Login error. Try again.");

        return "login";
    }


    private boolean validateUser(LoginForm loginForm){
        for (int i = 0; i < userController.getRegisteredUsers().size(); i++) {
             User currentUser = userController.getRegisteredUsers().get(i);
            if (currentUser.getUsername().equals(loginForm.getUsername())){
                if(currentUser.getPassword().equals(loginForm.getPassword())){
                    return true;
                }
            }
        }
        return false;
    }
}
