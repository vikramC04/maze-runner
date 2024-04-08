package ca.mcmaster.se2aa4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.algorithms.RightHandSolver;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeChar;
import ca.mcmaster.se2aa4.mazerunner.maze.Tile;

public class RightHandSolverTest {
    private RightHandSolver rhsSolver;
    private MazeChar[][] maze;

    @BeforeEach
    public void setUp() {
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

        rhsSolver = new RightHandSolver(maze, startTile, endTile);
        rhsSolver.solve();
        String path = rhsSolver.getPath();

        assertEquals("RFLFFRFF", path);
        assertFalse(path.contains("Unsolvable Maze"));
    }

    @Test
    public void testSolveWithSwappedStartEnd() {
        Tile startTile = new Tile(2, 3);
        Tile endTile = new Tile(0, 0);

        rhsSolver = new RightHandSolver(maze, startTile, endTile);
        rhsSolver.solve();
        String path = rhsSolver.getPath();

        assertEquals("LFFLFFRF", path);
        assertFalse(path.contains("Unsolvable Maze"));
    }

    @Test
    public void testExecutionTime() {
        Tile startTile = new Tile(0, 0);
        Tile endTile = new Tile(2, 3);

        rhsSolver = new RightHandSolver(maze, startTile, endTile);
        rhsSolver.solve();
        double executionTime = rhsSolver.getExecutionTime();
        
        assertTrue(executionTime >= 0);
    }
}
