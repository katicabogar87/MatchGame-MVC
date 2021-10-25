package application.controllers;

import application.models.LoginForm;
import application.models.RegisterForm;
import application.models.User;
import application.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private UserController userController;

    @Autowired
    public RegisterController(UserController userController) {
        this.userController = userController;
    }

    @GetMapping(value = {"/register"})
    public String loadRegister(@ModelAttribute("registerForm") RegisterForm registerForm) {

        return "register";
    }

    @PostMapping("/register")
    public String sendForm(@ModelAttribute("registerForm") RegisterForm registerForm, Model model) {


        model.addAttribute("username", registerForm.getUsername());
        model.addAttribute("password", registerForm.getPassword());
        model.addAttribute("age", registerForm.getAge());
        //validateFields
        User user = new User(registerForm.getUsername(), registerForm.getPassword(), registerForm.getAge(), UserRole.USER);

        userController.getRegisteredUsers().add(user);


        return "register";
    }
}
