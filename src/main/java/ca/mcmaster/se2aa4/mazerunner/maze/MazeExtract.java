package ca.mcmaster.se2aa4.mazerunner.maze;

import java.io.IOException;

public interface MazeExtract {
    public MazeChar[][] getMaze();
    public double getExecutionTime();
    public Tile getEndingCoordinates() throws IOException;
    public Tile getStartingCoordinates() throws IOException;
}
