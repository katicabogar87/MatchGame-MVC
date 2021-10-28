package application.controllers;

import application.models.*;
import application.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GameController {

    private GameService service;
    private MatchBox matchBox;
    private Robot robot;
    private Person person;
    private List<Player> players;

    @Autowired
    public GameController(GameService service, MatchBox matchBox, Robot robot, Person person) {
        this.service = service;
        this.matchBox = matchBox;
        this.robot = robot;
        this.person = person;
        players = List.of(person, robot);
    }

    @GetMapping(value ="/game")
    public String getGamePage(@ModelAttribute("gameForm") GameForm form, Model model) {
        model.addAttribute("playerName", "Hello, " + person.getName() + " !");
        model.addAttribute("difficulty", "Difficulty level: " + (robot.getStrategy().ordinal() +1));
        String currentMatches = service.getCurrentNumOfMatches();
        model.addAttribute("currentMatches", matchBox.getCurrentNumOfMatches());


        return "game";
    }

    @GetMapping(value ="/gameForUser")
    public String getGameForUserPage(@ModelAttribute("gameForm") GameForm form, Model model) {
        model.addAttribute("playerName", "Hello, " + person.getName() + " !");
        model.addAttribute("difficulty", "Difficulty level: " + (robot.getStrategy().ordinal() +1));
        String currentMatches = service.getCurrentNumOfMatches();
        model.addAttribute("currentMatches", matchBox.getCurrentNumOfMatches());


        return "gameForUser";
    }



    @PostMapping(value = { "/game"})
    public String getPick(@ModelAttribute("gameForm") GameForm form, Model model) {
        model.addAttribute("playerName", "Hello, " + person.getName() + " !");
        model.addAttribute("difficulty", "Difficulty level: " + (robot.getStrategy().ordinal() +1));

        int userNumber = form.getPick();
        if(validateUserPick(userNumber, matchBox)){
            int robotNumber = robot.pickSomeMatches(matchBox);

            String currentMatches = service.getCurrentNumOfMatches();
            String userText = service.getUserTextWithNumber(userNumber);
            String programText = service.getComputerTextWithNumber(robotNumber);

            model.addAttribute("currentMatches", matchBox.getCurrentNumOfMatches());
            model.addAttribute("userText", userText);
            model.addAttribute("programText", programText);

            Player winner = getWinner(form, model);
            if(winner!=null){
                model.addAttribute("winner", winnerMessage(winner));
            }
        }
        else {
            model.addAttribute("pickError", "wrong pick!");}

        return "game";
    }

    @GetMapping(value ="/rematch")
    public String getNewGamePage(@ModelAttribute("gameForm") GameForm form, Model model) {
        matchBox.setCurrentNumOfMatches(matchBox.generateStartingNumOfMatches());
        model.addAttribute("playerName", "Hello, " + person.getName() + " !");
        model.addAttribute("difficulty", "Difficulty level: " + (robot.getStrategy().ordinal() +1));
        String currentMatches = service.getCurrentNumOfMatches();
        model.addAttribute("currentMatches", matchBox.getCurrentNumOfMatches());
        return "rematch";}

    @PostMapping(value = { "/rematch"})
    public String getNewPick(@ModelAttribute("newGameForm") GameForm form, Model model) {

        model.addAttribute("playerName", "Hello, " + person.getName() + " !");
        model.addAttribute("difficulty", "Difficulty level: " + (robot.getStrategy().ordinal() +1));

        int userNumber = form.getPick();
        if(validateUserPick(userNumber, matchBox)){
            int robotNumber = robot.pickSomeMatches(matchBox);

            String currentMatches = service.getCurrentNumOfMatches();
            String userText = service.getUserTextWithNumber(userNumber);
            String programText = service.getComputerTextWithNumber(robotNumber);

            model.addAttribute("currentMatches", matchBox.getCurrentNumOfMatches());
            model.addAttribute("userText", userText);
            model.addAttribute("programText", programText);

            Player winner = getWinner(form, model);
            if(winner!=null){
                model.addAttribute("winner", winnerMessage(winner));
            }
        }
        else {
            model.addAttribute("pickError", "wrong pick!");}

        return "rematch";
    }

    private boolean validateUserPick(int userNumber, MatchBox matchBox) {
        return userNumber >0 && userNumber <4 && userNumber <= matchBox.getCurrentNumOfMatches();
    }

    public Player getWinner(GameForm form, Model model){

        Player winner = null;
        int pick;

        for (Player player:players) {
            if (player.getClass().equals(person.getClass()))
            {pick = form.getPick();}
            else pick = player.pickSomeMatches(matchBox);
            matchBox.decrementCurrentNumOfMatches(pick);
            model.addAttribute("currentMatches", matchBox.getCurrentNumOfMatches());


            if(matchBox.getCurrentNumOfMatches()==1){winner = player;}
            else
            if(matchBox.getCurrentNumOfMatches()==0){
                for (Player currentPlayer:players) {
                    if(!currentPlayer.equals(player)){
                        winner = currentPlayer;
                    }
                }
            }
        }
        return winner;
    }

    private String winnerMessage(Player winner){
        if (winner.getClass().equals(person.getClass())){
            return "Congratulations, you are the winner!";
        }
        return "Sorry, you lost!";
    }
}


