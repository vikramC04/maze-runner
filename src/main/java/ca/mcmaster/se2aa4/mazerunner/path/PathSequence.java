package ca.mcmaster.se2aa4.mazerunner.path;

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
        StringBuilder path = new StringBuilder();
        for(int i=0; i < moves.size(); i++) {
            path.append(moves.get(i));
        }
        return path.toString();
    }

    public List<Moves> getMoves() {
        return moves;
    }
}
