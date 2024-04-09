package ca.mcmaster.se2aa4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.movement.Direction;

public class DirectionTest {
    @Test
    public void testNextRight() {
        assertEquals(Direction.EAST, Direction.NORTH.nextRight());
        assertEquals(Direction.SOUTH, Direction.EAST.nextRight());
        assertEquals(Direction.WEST, Direction.SOUTH.nextRight());
        assertEquals(Direction.NORTH, Direction.WEST.nextRight());
    }

    @Test
    public void testNextLeft() {
        assertEquals(Direction.WEST, Direction.NORTH.nextLeft());
        assertEquals(Direction.NORTH, Direction.EAST.nextLeft());
        assertEquals(Direction.EAST, Direction.SOUTH.nextLeft());
        assertEquals(Direction.SOUTH, Direction.WEST.nextLeft());
    }
}
