package ca.mcmaster.se2aa4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Player;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeChar;
import ca.mcmaster.se2aa4.mazerunner.maze.Tile;
import ca.mcmaster.se2aa4.mazerunner.movement.Direction;
import ca.mcmaster.se2aa4.mazerunner.movement.Location;

public class PlayerTest {
    private Player player;
    private MazeChar[][] mazeMap;


    @BeforeEach
    public void setUp() {
        // Initialize maze map with a simple maze configuration
        mazeMap = new MazeChar[][] {
            { MazeChar.WALL, MazeChar.SPACE, MazeChar.SPACE },
            { MazeChar.WALL, MazeChar.WALL, MazeChar.SPACE },
            { MazeChar.SPACE, MazeChar.SPACE, MazeChar.SPACE }
        };
        // Create player at position (0,0) facing EAST
        player = new Player(new Tile(0, 0), Direction.EAST, mazeMap);
    }

    @Test
    public void testMoveForward() {
        player.moveForward();
        Location expectedLocation = new Location(1, 0);
        assertEquals(expectedLocation.getX(), player.playerLocation().getX());
        assertEquals(expectedLocation.getY(), player.playerLocation().getY());
    }
}
