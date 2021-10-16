package application.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Getter @Setter
@Component
public class Robot implements Player {


    private String name="robot";
    private Strategy strategy;

    public Robot() {}

    public int pickSomeMatches(MatchBox matchBox) {

        int maxPick = matchBox.getAllowedPicks().get(matchBox.getAllowedPicks().size() - 1);
        int current = matchBox.getCurrentNumOfMatches();
        switch (strategy) {
            case RANDOM -> {
                int top = Math.min(matchBox.getCurrentNumOfMatches(), maxPick);
                return (int) (Math.random() * (top - 1 + 1)) + 1;
            }
            case ONE_BY_ONE -> {
                return 1;
            }
            case MATH -> {
                int magicNumber = maxPick + 1;
                int modulo = current % (magicNumber);
                if (modulo == 0) {
                    return magicNumber - 1;
                }
                if (modulo == 1) {
                    return 1;
                }
                return modulo - 1;
            }

        }
        return 1;
    }

    @Override    public String toString() {
        return name;
    }
}
