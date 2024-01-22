package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MazeSolver {
    private String filepath;
    private String path_sequence;
    private static final Logger logger = LogManager.getLogger();
    private ArrayList<String> maze = new ArrayList<>();
    public MazeSolver(String filepath, String path_sequence) throws FileNotFoundException {
        this.filepath = filepath;
        this.path_sequence = path_sequence;

    }
    public void solve() {
        try {
            Tile west = getStartingCoordinates();
            Tile east = getEndingCoordinates();
            map_maze(filepath);
            String[] maze_map = new String[maze.size()];
            maze_map = maze.toArray(maze_map);
            path_sequence = processPathSequence(path_sequence);
            boolean verdict = verifyPath(west,east,0, maze_map)
                    || verifyPath(east,west,2, maze_map);
            if(verdict) {
                System.out.println("correct path");
            } else {
                System.out.println("incorrect path");
            }
        }catch(Exception e) {
            logger.error("error occured");
            logger.error(e.getMessage());
        }


    }
    public void map_maze(String filepath) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        while((line = reader.readLine()) != null) {
            maze.add(line);
        }
    }
    public Tile getStartingCoordinates() throws IOException {
        String line;
        int counter = 0;
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        while((line = reader.readLine()) != null) {
            if(line.isEmpty() || line.charAt(0) == ' ') {
                reader.close();
                return new Tile(0,counter);
            }
            counter++;
        }

        return null;
    }
    public Tile getEndingCoordinates() throws IOException {
        int counter = 1;
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        int ending = reader.readLine().stripTrailing().length() - 1;
        while((line = reader.readLine()) != null) {
            if(line.isEmpty() || line.charAt(ending) == ' ') {
                reader.close();
                return new Tile(ending,counter);
            }
            counter++;
        }
        
        return null;
    }

    public String processPathSequence(String path_sequence) {
        String path = "";
        int i=0;
        if(path_sequence.matches(".*\\d.*")) {
            while(i < path_sequence.length()) {
                char current = path_sequence.charAt(i);
                 if(Character.isDigit(current)) {
                     path += path_sequence.substring(i+1,i+2).repeat(path_sequence.charAt(i) - '0');
                     i++;
                 }else if(i+1 >= path_sequence.length() || Character.isDigit(path_sequence.charAt(i+1))) {
                     path += current;
                 }
                 i++;
            }
        } else {
            path = path_sequence;
        }
        logger.info("Path sequence: " + path);
        return path;
    }

    public boolean verifyPath(Tile start, Tile end, int starting_direction, String[] maze_map) throws IOException {
        int direction = starting_direction;
        int x = start.getX();
        int y = start.getY();
        for(char c : path_sequence.toCharArray()) {
            if((y >= maze_map.length || x >= maze_map[0].length() || x < 0 || y < 0) || !(maze_map[y].isEmpty()) && maze_map[y].charAt(x) == '#') {
                return false;
            }
            if(c == 'F') {
                if(direction == 0) {
                    x++;
                } else if(direction == 1) {
                    y++;
                } else if(direction == 2) {
                    x--;
                } else if(direction == 3) {
                    y--;
                }
            } else if(c == 'R') {
                if(direction == 3) {
                    direction = 0;
                } else {
                    direction += 1;
                }
            } else if(c == 'L') {
                if(direction == 0) {
                    direction = 3;
                } else {
                    direction -= 1;
                }
            }
        }
        return (x == end.getX() && y == end.getY());
    }
}


