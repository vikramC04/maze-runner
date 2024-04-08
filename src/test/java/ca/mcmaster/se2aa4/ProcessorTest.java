package ca.mcmaster.se2aa4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Processor;

public class ProcessorTest {
    
    private Processor processor;
    private String unfactorizedPath = "FRRFLLLF ";
    private String factorizedPath = "F 2R F 3L F ";

    @BeforeEach
    public void setUp() {
        processor = new Processor();
    }

    @Test
    public void testProcessPathSequence() {
        assertEquals("F RR F LLL F ", processor.processPathSequence(factorizedPath));
    }

    @Test
    public void testFactorizePath() {
        assertEquals("F 2R F 3L F   ", processor.factorizePath(unfactorizedPath));
    }
}
