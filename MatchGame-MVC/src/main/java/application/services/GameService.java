package application.services;

import application.models.MatchBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private MatchBox matchBox;


    @Autowired
    public GameService(MatchBox matchBox) {
        this.matchBox = matchBox;
    }

    public String getCurrentNumOfMatches() {

        return "Current number of matches: " + matchBox.getCurrentNumOfMatches();
    }


    public String getUserTextWithNumber(int pick) {
        return "Your pick is "+pick + ".";
    }

    public String getComputerTextWithNumber(int pick) {
        return "Computer's pick is "+pick + ".";
    }
}

