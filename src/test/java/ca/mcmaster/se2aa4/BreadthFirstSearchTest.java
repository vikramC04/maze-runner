package ca.mcmaster.se2aa4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.algorithms.BreadthFirstSearch;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeChar;
import ca.mcmaster.se2aa4.mazerunner.maze.Tile;

public class BreadthFirstSearchTest {
    private BreadthFirstSearch bfsSolver;
    private MazeChar[][] maze;

    @BeforeEach
    public void setUp() {
        // Initialize a simple maze configuration
        maze = new MazeChar[][] {
            { MazeChar.SPACE, MazeChar.WALL, MazeChar.WALL, MazeChar.WALL },
            { MazeChar.SPACE, MazeChar.SPACE, MazeChar.SPACE, MazeChar.WALL },
            { MazeChar.WALL, MazeChar.WALL, MazeChar.SPACE, MazeChar.WALL },
            { MazeChar.WALL, MazeChar.SPACE, MazeChar.SPACE, MazeChar.WALL },
            { MazeChar.WALL, MazeChar.WALL, MazeChar.WALL, MazeChar.WALL }
        };
    }

    @Test
    public void testSolve() {
        Tile startTile = new Tile(0, 0);  
        Tile endTile = new Tile(2, 3);    
        bfsSolver = new BreadthFirstSearch(maze, startTile, endTile);
        bfsSolver.solve();
        String path = bfsSolver.getPath();
        assertEquals("RFLFFRFF", path);
    }

    @Test
    public void testSolveWithSwappedStartEnd() {
        Tile startTile = new Tile(2, 3);  
        Tile endTile = new Tile(0, 0);   

        bfsSolver = new BreadthFirstSearch(maze, startTile, endTile);
        bfsSolver.solve();
        String path = bfsSolver.getPath();

        assertEquals("LFFLFFRF", path);
    }

    @Test
    public void testExecutionTime() {
        Tile startTile = new Tile(0, 0);  
        Tile endTile = new Tile(2, 3);    

        bfsSolver = new BreadthFirstSearch(maze, startTile, endTile);
        bfsSolver.solve();
        double executionTime = bfsSolver.getExecutionTime();
        
        assertTrue(executionTime >= 0); 
    }
}
