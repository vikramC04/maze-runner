package ca.mcmaster.se2aa4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.path.Moves;
import ca.mcmaster.se2aa4.mazerunner.path.PathSequence;

public class PathSequenceTest {
    private PathSequence pathSequence;

    @BeforeEach
    public void setUp() {
        pathSequence = new PathSequence();
    }

    @Test
    public void testAddMoveAndGetMoves() {
        pathSequence.addMove(Moves.F);
        pathSequence.addMove(Moves.L);
        pathSequence.addMove(Moves.R);
        
        List<Moves> moves = pathSequence.getMoves();
        
        assertEquals(3, moves.size());
        assertEquals(Moves.F, moves.get(0));
        assertEquals(Moves.L, moves.get(1));
        assertEquals(Moves.R, moves.get(2));
    }

    @Test
    public void testGetString() {
        pathSequence.addMove(Moves.F);
        pathSequence.addMove(Moves.L);
        pathSequence.addMove(Moves.R);
        
        assertEquals("FLR", pathSequence.getString());
    }

    @Test
    public void testGetMoves() {
        pathSequence.addMove(Moves.F);
        pathSequence.addMove(Moves.L);
        pathSequence.addMove(Moves.R);

        List<Moves> moves = pathSequence.getMoves();
        
        assertEquals(3, moves.size());
        assertEquals(Moves.F, moves.get(0));
        assertEquals(Moves.L, moves.get(1));
        assertEquals(Moves.R, moves.get(2));
    }
    
}
