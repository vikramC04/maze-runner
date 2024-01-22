package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MazeSolver {
    private String filepath;
    private String path_sequence;
    private static final Logger logger = LogManager.getLogger();
    public MazeSolver(String filepath, String path_sequence) throws FileNotFoundException {
        this.filepath = filepath;
        this.path_sequence = path_sequence;

    }
    public void solve() {
        try {
            Tile west = getStartingCoordinates();
            Tile east = getEndingCoordinates();
            boolean verdict = verifyPath(west,east,0);
            if(verdict) {
                System.out.println("correct path");
            } else {
                System.out.println("incorrect path");
            }
        }catch(Exception e) {
            logger.error("error occured");
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
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

    public boolean verifyPath(Tile start, Tile end, int starting_direction) throws IOException {
        int direction = starting_direction;
        int x = start.getX();
        int y = start.getY();
        for(char c : path_sequence.toCharArray()) {
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

