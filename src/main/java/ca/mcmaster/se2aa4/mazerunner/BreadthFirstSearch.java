package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BreadthFirstSearch implements Solver {
    private static final Logger logger = LogManager.getLogger();
    private Tile west;
    private Tile east;
    private final MazeChar[][] maze_map;
    private Direction direction = Direction.EAST;
    private Location location;
    private MazeState mazeState;
    private long executionTime;
    private String path;

    public BreadthFirstSearch(MazeChar[][] maze_m, Tile s, Tile e)  {
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
            logger.info("Executing BFS");
            //Player player = new Player(west, Direction.EAST, maze_map);
            long startTime = System.nanoTime();
            path = pathFinding(east);
            long endTime = System.nanoTime();
            executionTime = endTime - startTime;
            System.out.println(processor.factorizePath(path));
        }catch(Exception e) {
            logger.error("error occured");
            logger.error(e.getMessage());
        }
    }

    public String pathFinding(Tile end) {
        Map<Location, String> map = new HashMap<>();
        Map<Location, Direction> nodeDir = new HashMap<>();
        Queue<Location> nodes = new LinkedList<>();
        
        map.put(location, "");
        nodeDir.put(location, direction);
        nodes.offer(location);
        while(!nodes.isEmpty()) {
            Location curPosition = nodes.poll();
            if(mazeState.isEnd(end, curPosition)) return map.get(curPosition);
            Direction curDirection = nodeDir.get(curPosition);
            Location newPosition;
            Direction newDirection;
            if(mazeState.isValid(curPosition.move(curDirection.nextRight()))) {
                newPosition = curPosition.move(curDirection.nextRight());
                newDirection = curDirection.nextRight();
                map.put(newPosition, map.get(curPosition) + "RF");
                nodeDir.put(newPosition, newDirection);
                nodes.offer(newPosition);
            } 
            if(mazeState.isValid(curPosition.move(curDirection))) {
                newPosition = curPosition.move(curDirection);
                newDirection = curDirection;
                map.put(newPosition, map.get(curPosition) + "F");
                nodeDir.put(newPosition, newDirection);
                nodes.offer(newPosition);
            } 
            if(mazeState.isValid(curPosition.move(curDirection.nextLeft()))) {
                newPosition = curPosition.move(curDirection.nextLeft());
                newDirection = curDirection.nextLeft();
                map.put(newPosition, map.get(curPosition) + "LF");
                nodeDir.put(newPosition, newDirection);
                nodes.offer(newPosition);
            }
        }
        return "Unsolvable Maze";
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
