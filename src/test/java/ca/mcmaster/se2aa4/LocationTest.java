package ca.mcmaster.se2aa4;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.movement.Direction;
import ca.mcmaster.se2aa4.mazerunner.movement.Location;

public class LocationTest {
    private Location location;

    @BeforeEach
    public void setUp() {
        location = new Location(5, 5); 
    }

    @AfterEach
    public void tearDown() {
        location = null;
    }

    @Test
    public void testMoveNorth() {
        Location newLocation = location.move(Direction.NORTH);
        assertEquals(5, newLocation.getX());
        assertEquals(4, newLocation.getY());
    }

    @Test
    public void testMoveEast() {
        Location newLocation = location.move(Direction.EAST);
        assertEquals(6, newLocation.getX());
        assertEquals(5, newLocation.getY());
    }

    @Test
    public void testMoveSouth() {
        Location newLocation = location.move(Direction.SOUTH);
        assertEquals(5, newLocation.getX());
        assertEquals(6, newLocation.getY());
    }

    @Test
    public void testMoveWest() {
        Location newLocation = location.move(Direction.WEST);
        assertEquals(4, newLocation.getX());
        assertEquals(5, newLocation.getY());
    }

    @Test
    public void testGetX() {
        assertEquals(5, location.getX());
    }

    @Test
    public void testGetY() {
        assertEquals(5, location.getY());
    }

    @Test
    public void testToString() {
        assertEquals("(5,5)", location.toString());
    }

}
