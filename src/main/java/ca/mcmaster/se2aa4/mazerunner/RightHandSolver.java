package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RightHandSolver implements Solver {
    private static final Logger logger = LogManager.getLogger();
    private Tile west;
    private Tile east;
    private String computed_path;
    private final int[][] maze_map;
    private Direction direction = Direction.EAST;
    private Location location;
    private MazeState mazeState;

    public RightHandSolver(int[][] maze_m, Tile s, Tile e)  {
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
            computed_path = pathFinding(east);
            System.out.println(processor.factorizePath(computed_path));
        }catch(Exception e) {
            logger.error("error occured");
            logger.error(e.getMessage());
        }
    }
   
    public String movePlayer() {
        String path = "";
        if(mazeState.isValid(location.move(direction.nextRight()))) {
            location = location.move(direction.nextRight());
            path += "RF";
            direction = direction.nextRight();
        } else if(mazeState.isValid(location.move(direction))) {
            location = location.move(direction);
            path += "F";
        } else if(mazeState.isValid(location.move(direction.nextLeft()))) {
            location = location.move(direction.nextLeft());
            path += "LF";
            direction = direction.nextLeft();
        } else {
            path += "LL";
            direction = direction.nextLeft().nextLeft();
        }
        return path;
    }


    public String pathFinding(Tile end) {
        String path = "";
        logger.info("Start location: " + location.getX() + "," + location.getY());
        while(!mazeState.isEnd(end, location)) {
            path += movePlayer();
        }

        return path;
    }
   

}


