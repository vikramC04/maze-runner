package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathSequence {
    List<Moves> moves = new ArrayList<>();

    public PathSequence() {
    }
    
    public void addMove(Moves move) {
        moves.add(move);
    }

    public String getString() {
        return Arrays.toString(moves.toArray()).replace("[", "")
        .replace("]", "").replace(",","");
    }
}
