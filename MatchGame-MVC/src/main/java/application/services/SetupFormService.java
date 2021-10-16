package application.services;

import application.models.SetupForm;

import org.springframework.stereotype.Service;

@Service
public class SetupFormService {

    public boolean validate(SetupForm setupForm) {
        return  0 < setupForm.getLevel() && setupForm.getLevel()  < 4;
    }

    public String getNameWithText(SetupForm setupForm) {
        return "Hello,  " + setupForm.getName() + "!";
    }

    public String getDifficultyWithText(SetupForm setupForm) {
        return "Difficulty level: " + setupForm.getLevel() + ".";
    }

}


