package application.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MatchBox {

    @Getter @Setter
    private int currentNumOfMatches;
    @Getter
    private List<Integer> allowedPicks = List.of(1,2,3);
    private static final int minStart = 4;
    private static final int maxStart = 40;

    public MatchBox() {
        currentNumOfMatches = generateStartingNumOfMatches();

    }

    public void decrementCurrentNumOfMatches(int pick){
        currentNumOfMatches-=pick;
    }

    public int generateStartingNumOfMatches(){
        return (int)(Math.random() * (maxStart-minStart+1)) + minStart;
    }
}
