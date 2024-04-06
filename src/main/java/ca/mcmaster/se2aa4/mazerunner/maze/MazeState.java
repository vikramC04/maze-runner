package ca.mcmaster.se2aa4.mazerunner.maze;

import ca.mcmaster.se2aa4.mazerunner.movement.Location;

public class MazeState {
    private MazeChar[][] maze;
    public MazeState(MazeChar[][] mazeBinary) {
        maze = mazeBinary.clone();
    }

    public boolean isWall(Location location) {
        return maze[location.getY()][location.getX()] == MazeChar.WALL;
    }
    public boolean isValid(Location location) {
        if((location.getY() >= maze.length || location.getX() >= maze[0].length
        || location.getX() < 0 || location.getY() < 0 || isWall(location))) {
            return false;
        }

        return true;
    }
    public boolean isEnd(Tile end, Location location) {
        return end.isX(location.getX()) && end.isY(location.getY());
    }
}
