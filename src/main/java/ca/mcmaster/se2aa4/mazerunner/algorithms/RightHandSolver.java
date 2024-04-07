package ca.mcmaster.se2aa4.mazerunner.algorithms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Processor;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeChar;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeState;
import ca.mcmaster.se2aa4.mazerunner.maze.Tile;
import ca.mcmaster.se2aa4.mazerunner.movement.Direction;
import ca.mcmaster.se2aa4.mazerunner.movement.Location;
import ca.mcmaster.se2aa4.mazerunner.path.Moves;
import ca.mcmaster.se2aa4.mazerunner.path.PathSequence;


public class RightHandSolver implements Solver {
    private static final Logger logger = LogManager.getLogger();
    private Tile west;
    private Tile east;
    private final MazeChar[][] maze_map;
    private Direction direction = Direction.EAST;
    private Location location;
    private MazeState mazeState;
    private long executionTime;
    private String path;
    PathSequence moveSet = new PathSequence();

    public RightHandSolver(MazeChar[][] maze_m, Tile s, Tile e)  {
        maze_map = maze_m.clone();
        west = s;
        east = e;
        int coords[] = west.findCoords().clone();
        location = new Location(coords[0],coords[1]);
        mazeState= new MazeState(maze_map);
    }
    @Override
    public void solve() {
        try {
            Processor processor = new Processor();
            logger.info("Executing right hand path finding");
            //Player player = new Player(west, Direction.EAST, maze_map);
            long startTime = System.nanoTime();
            path = pathFinding(east);
            System.out.println(processor.factorizePath(path));
            long endTime = System.nanoTime();
            executionTime = endTime - startTime;
        }catch(Exception e) {
            logger.error("error occured");
            logger.error(e.getMessage());
        }
    }
   
    public void movePlayer() {
        if(mazeState.isValid(location.move(direction.nextRight()))) {
            location = location.move(direction.nextRight());
            moveSet.addMove(Moves.R);
            moveSet.addMove(Moves.F);
            direction = direction.nextRight();
        } else if(mazeState.isValid(location.move(direction))) {
            location = location.move(direction);
            moveSet.addMove(Moves.F);
        } else if(mazeState.isValid(location.move(direction.nextLeft()))) {
            location = location.move(direction.nextLeft());
            moveSet.addMove(Moves.L);
            moveSet.addMove(Moves.F);
            direction = direction.nextLeft();
        } else {
            moveSet.addMove(Moves.L);
            moveSet.addMove(Moves.L);
            direction = direction.nextLeft().nextLeft();
        }
    }

    private String pathFinding(Tile end) {
        while(!mazeState.isEnd(end, location)) {
            movePlayer();
        }
        return moveSet.getString();
    }

    @Override
    public double getExecutionTime() {
        return executionTime * Math.pow(10,-6);
    }

    @Override
    public String getPath() {
        return path;
    }
   

}


