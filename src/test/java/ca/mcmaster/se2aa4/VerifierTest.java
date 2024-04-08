package ca.mcmaster.se2aa4;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Player;
import ca.mcmaster.se2aa4.mazerunner.Verifier;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeChar;
import ca.mcmaster.se2aa4.mazerunner.maze.Tile;
import ca.mcmaster.se2aa4.mazerunner.movement.Direction;

public class VerifierTest {
    private Verifier verifier;
    private Verifier falseVerifier;
    private MazeChar[][] mazeBinary;

    @BeforeEach
    public void setUp() {
        mazeBinary = new MazeChar[][] {
            { MazeChar.WALL, MazeChar.WALL, MazeChar.WALL, MazeChar.WALL },
            { MazeChar.SPACE, MazeChar.SPACE, MazeChar.SPACE, MazeChar.SPACE},
            { MazeChar.WALL, MazeChar.WALL, MazeChar.WALL, MazeChar.WALL },
        };
        verifier = new Verifier("FFF");
        falseVerifier = new Verifier("RRFFFFFLRLRF");
    }

    @Test
    public void testValidPath() throws IOException {
        Tile startTile = new Tile(0, 1); 
        Tile endTile = new Tile(3, 1); 
        Player player = new Player(startTile, Direction.EAST, mazeBinary); 
        assertTrue(verifier.verifyPath(endTile, player, mazeBinary));
        assertFalse(falseVerifier.verifyPath(endTile, player, mazeBinary));
    }
}
