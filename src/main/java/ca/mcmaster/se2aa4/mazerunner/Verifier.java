package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

public class Verifier {
    private String path;

    public Verifier(String path) {
        this.path = path;
    }

    public boolean verifyPath(Tile end, Player player, MazeChar[][] mazeBinary) throws IOException {
        MazeState state = new MazeState(mazeBinary);
        for(char c : path.toCharArray()) {
            if(!state.isValid(player.playerLocation())) return false;
            if(c == 'F') {
                player.moveForward();
            } else if(c == 'R') {
                player.turnRight();
            } else if(c == 'L') {
                player.turnLeft();
            }
        }
        return state.isEnd(end, player.playerLocation());
    }
}
