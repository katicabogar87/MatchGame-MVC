package application.controllers;

import application.models.Person;
import application.models.Robot;
import application.models.SetupForm;
import application.models.Strategy;
import application.services.SetupFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SetupController {

   private SetupFormService service;
   private Robot robot;
   private Person person;

    @Autowired
    public SetupController(SetupFormService service, Robot robot, Person person) {
        this.service = service;
        this.robot = robot;
        this.person = person;
    }

    @GetMapping("/setup")
    public String getSetupFormPage(@ModelAttribute("setupForm") SetupForm form) {
        return "setup";
    }

    @PostMapping("/setup")
    public String sendForm(@ModelAttribute("setupForm") SetupForm userForm, Model model) {
            person.setName(userForm.getName());

            model.addAttribute("name", person.getName());
            model.addAttribute("level", service.getDifficultyWithText(userForm));

            setRobotStrategy(userForm);

            return "setup";
        }

        private void setRobotStrategy(SetupForm userForm){
            switch (userForm.getLevel()) {
                case 1 ->
                    robot.setStrategy(Strategy.ONE_BY_ONE);
                case 2 ->
                    robot.setStrategy(Strategy.RANDOM);
                case 3 ->
                    robot.setStrategy(Strategy.MATH);
                default -> robot.setStrategy(Strategy.ONE_BY_ONE);
            }
        }
    }


