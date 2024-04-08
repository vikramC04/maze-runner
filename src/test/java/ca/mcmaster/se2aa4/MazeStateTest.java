package ca.mcmaster.se2aa4;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.maze.MazeChar;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeState;
import ca.mcmaster.se2aa4.mazerunner.maze.Tile;
import ca.mcmaster.se2aa4.mazerunner.movement.Location;

public class MazeStateTest {
     private MazeState mazeState;
    private MazeChar[][] maze;

    @BeforeEach
    public void setUp() {
        maze = new MazeChar[][] {
            { MazeChar.WALL, MazeChar.SPACE, MazeChar.WALL },
            { MazeChar.WALL, MazeChar.WALL, MazeChar.SPACE },
            { MazeChar.SPACE, MazeChar.SPACE, MazeChar.WALL }
        };
        mazeState = new MazeState(maze);
    }

    @Test
    public void testIsWall() {
        assertTrue(mazeState.isWall(new Location(0, 0))); 
        assertFalse(mazeState.isWall(new Location(1, 0))); 
    }

    @Test
    public void testIsValid() {
        assertTrue(mazeState.isValid(new Location(1, 0))); 
        assertFalse(mazeState.isValid(new Location(10, 10))); 
        assertFalse(mazeState.isValid(new Location(-3, -3))); 
    }

    @Test
    public void testIsEnd() {
        Tile endTile = new Tile(2, 2);
        assertTrue(mazeState.isEnd(endTile, new Location(2, 2))); 
        assertFalse(mazeState.isEnd(endTile, new Location(1, 1))); 
    }
    
}
