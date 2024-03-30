package ca.mcmaster.se2aa4.mazerunner;

public class MazeState {
    private int[][] maze;
    public MazeState(int[][] mazeBinary) {
        maze = mazeBinary.clone();
    }

    public boolean isWall(Location location) {
        return maze[location.getY()][location.getX()] == 1;
    }
    public boolean isValid(Location location) {
        if((location.getY() >= maze.length || location.getX() >= maze[0].length
        || location.getX() < 0 || location.getY() < 0 || isWall(location))) {
            return false;
        }

        return true;
    }
}
