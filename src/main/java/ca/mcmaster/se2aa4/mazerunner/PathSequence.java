package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PathSequence {
    List<Moves> moves;

    public PathSequence() {
        moves = new ArrayList<>();
    }
    public PathSequence(PathSequence path) {
        moves = path.getMoves().stream().collect(Collectors.toList());
    }
    
    public void addMove(Moves move) {
        moves.add(move);
    }

    public String getString() {
        String path = "";
        for(int i=0; i < moves.size(); i++) {
            path += moves.get(i);
        }
        return path;
    }

    public List<Moves> getMoves() {
        return moves;
    }
}
