package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MazeSolver {
    private String filepath;
    private String path_sequence;
    private static final Logger logger = LogManager.getLogger();
    Maze maze;



    private int[][] maze_binary;
    public MazeSolver(String filepath, String path_sequence, Maze maze) throws FileNotFoundException {
        this.filepath = filepath;
        this.path_sequence = path_sequence;
        maze_binary = maze.getMaze().clone();
        logger.info("Printing from maze solver: ");
        logger.info(Arrays.deepToString(maze_binary));
        this.maze = maze;

    }
    public void solve() {
        try {
            Tile west = maze.getStartingCoordinates();
            Tile east = maze.getEndingCoordinates();
            if(path_sequence != null) {
                path_sequence = processPathSequence(path_sequence);
                boolean verdict = verifyPath(west,east,Direction.EAST, maze_binary)
                        || verifyPath(east,west,Direction.WEST, maze_binary);
                if(verdict) {
                    System.out.println("correct path");
                } else {
                    System.out.println("incorrect path");
                }
            } else {
                logger.info("\nExecuting right hand path finding");
                String computed_path = rightHandPathFinding(west, east, maze_binary, Direction.EAST);
                System.out.println("The path is: " + computed_path);

            }
        }catch(Exception e) {
            logger.error("error occured");
            logger.error(e.getMessage());
        }


    }

    public String processPathSequence(String path_sequence) {
        String path = "";
        int i=0;
        if(path_sequence.matches(".*\\d.*")) {
            while(i < path_sequence.length()) {
                char current = path_sequence.charAt(i);
                 if(Character.isDigit(current)) {
                     String number = String.valueOf(current);
                     i++;
                     while(Character.isDigit(path_sequence.charAt(i))) {
                         number += path_sequence.charAt(i);
                     }
                     int value = Integer.parseInt(number);
                     path += path_sequence.substring(i, i+1).repeat(value);

                 }else if(i+1 >= path_sequence.length() || Character.isDigit(path_sequence.charAt(i+1))) {
                     path += current;
                 }
                i++;
            }
        } else {
            path = path_sequence;
        }
        logger.info("This is the Path sequence: " + path);
        return path;
    }

    public boolean verifyPath(Tile start, Tile end, Direction direction, int[][] maze_map) throws IOException {
        //int direction = 0;
        int x = start.getX();
        int y = start.getY();
        for(char c : path_sequence.toCharArray()) {
            if((y >= maze_map.length || x >= maze_map[0].length || x < 0 || y < 0) || maze_map[y][x] == 1) {
                return false;
            }
            if(c == 'F') {
                if(direction == Direction.EAST) {
                    x++;
                } else if(direction == Direction.SOUTH) {
                    y++;
                } else if(direction == Direction.WEST) {
                    x--;
                } else if(direction == Direction.NORTH) {
                    y--;
                }
            } else if(c == 'R') {
                if(direction == Direction.EAST) {
                    direction = Direction.SOUTH;
                } else if(direction == Direction.SOUTH) {
                    direction = Direction.WEST;
                } else if(direction == Direction.WEST) {
                    direction = Direction.NORTH;
                }else if(direction == Direction.NORTH) {
                    direction = Direction.EAST;
                }
            } else if(c == 'L') {
                if(direction == Direction.EAST) {
                    direction = Direction.NORTH;
                } else if(direction == Direction.NORTH) {
                    direction = Direction.WEST;
                } else if(direction == Direction.WEST) {
                    direction = Direction.SOUTH;
                } else if(direction == Direction.SOUTH) {
                    direction = Direction.EAST;
                }
            }
        }
        return (x == end.getX() && y == end.getY());
    }
    public String rightHandPathFinding(Tile start, Tile end, int[][] maze_map, Direction direction) {
        int x = start.getX();
        int y = start.getY();
        String path = "";
        while(x != end.getX() || y != end.getY()) {
            if(direction == Direction.EAST) {
                if(maze_map[y+1][x] == 0) {
                    direction = Direction.SOUTH;
                    y++;
                    path += "RF";
                } else if(maze_map[y][x+1] == 0) {
                    x++;
                    path += "F";
                } else if(maze_map[y-1][x] == 0) {
                    direction = Direction.NORTH;
                    y--;
                    path += "LF";
                } else if(maze_map[y][x-1] == 0) {
                    direction = Direction.WEST;
                    x--;
                    path += "RRF";
                }
            } else if(direction == Direction.SOUTH) {
                if (maze_map[y][x-1] == 0) {
                    direction = Direction.WEST;
                    x--;
                    path += "RF";
                } else if (maze_map[y+1][x] == 0) {
                    y++;
                    path += "F";
                } else if (maze_map[y][x + 1] == 0) {
                    direction = Direction.EAST;
                    x++;
                    path += "LF";
                } else if (maze_map[y-1][x] == 0) {
                    direction = Direction.NORTH;
                    y--;
                    path += "RRF";
                }
            } else if(direction == Direction.WEST) {
                if(maze_map[y-1][x] == 0) {
                    direction = Direction.NORTH;
                    y--;
                    path += "RF";
                } else if(maze_map[y][x-1] == 0) {
                    x--;
                    path += "F";
                } else if(maze_map[y+1][x] == 0) {
                    direction = Direction.SOUTH;
                    y++;
                    path += "LF";
                } else if(maze_map[y][x+1] == 0) {
                    direction = Direction.EAST;
                    x++;
                    path += "RRF";
                }
            } else if(direction == Direction.NORTH) {
                if(maze_map[y][x+1] == 0) {
                    direction = Direction.EAST;
                    x++;
                    path += "RF";
                } else if(maze_map[y-1][x] == 0) {
                    y--;
                    path += "F";
                } else if(maze_map[y][x-1] == 0) {
                    direction = Direction.WEST;
                    x--;
                    path += "LF";
                } else if(maze_map[y+1][x] == 0) {
                    direction = Direction.SOUTH;
                    y++;
                    path += "RRF";
                }
            }
        }

        return path;
    }
}


