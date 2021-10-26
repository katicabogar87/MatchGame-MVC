package application.controllers;

import application.models.User;
import application.models.UserRole;
import lombok.Getter;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    public UserController() {
        addExampleUsers();;
    }

    @Getter
    private List <User> registeredUsers=new ArrayList<>();

    private void addExampleUsers(){
        User firstUser = new User("firstUser", "password", 20, UserRole.USER);
        User admin = new User("admin" , "password", 30, UserRole.ADMIN);
        registeredUsers.add(firstUser);
        registeredUsers.add(admin);
    }
}
