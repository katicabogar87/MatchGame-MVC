package application.services;

import application.models.LoginForm;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
    public String getNameWithText(LoginForm loginForm) {
        return "Welcome, " + loginForm.getUsername() + " you are logged in!";
    }
}
