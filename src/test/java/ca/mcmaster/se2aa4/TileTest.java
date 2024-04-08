package ca.mcmaster.se2aa4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.maze.Tile;

public class TileTest {
    private Tile tile;
    
    @BeforeEach
    public void setUp() {
        tile = new Tile(3, 4);
    }

    @Test
    public void testFindCoords() {
        int[] coords = tile.findCoords();
        assertEquals(2, coords.length);
        assertEquals(3, coords[0]);
        assertEquals(4, coords[1]);
    }

    @Test
    public void testIsX() {
        assertTrue(tile.isX(3));
        assertFalse(tile.isX(5));
    }

    @Test
    public void testIsY() {
        assertTrue(tile.isY(4));
        assertFalse(tile.isY(6));
    }
}
