package ca.mcmaster.se2aa4;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.benchmarking.Baseline;
import ca.mcmaster.se2aa4.mazerunner.configurations.Algorithm;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeExtract;
import ca.mcmaster.se2aa4.mazerunner.maze.Tile;

public class BaselineTest {
    private Baseline baseline;

    @BeforeEach
    public void setUp() throws IOException {
        
        MazeExtract mazeExtract = new Maze("./examples/giant.maz.txt");
        
        Tile startTile = new Tile(0, 0);
        Tile endTile = new Tile(0, 0);
        
        Algorithm algorithm = Algorithm.BFS;
        Algorithm baselineAlgorithm = Algorithm.RIGHTHAND;
        
        baseline = new Baseline(mazeExtract, algorithm, startTile, endTile, baselineAlgorithm);
    }

    @Test
    public void testBenchmark() {
        assertDoesNotThrow(() -> baseline.benchmark());
    }
    
}
