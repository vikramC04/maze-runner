package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    private String filepath;
    private int[][] maze_binary;
    private static final Logger logger = LogManager.getLogger();
    public Maze(String filepath) throws IOException {
        this.filepath = filepath;
        createMaze();
    }
    private void createMaze() throws IOException {
        logger.info("Creating maze");
        ArrayList<String> maze = new ArrayList<>();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        while((line = reader.readLine()) != null) {
            maze.add(line);
        }
        maze_binary = new int[maze.size()][maze.get(0).length()];
        for(int i=0; i < maze.size(); i++) {
            for(int j=0; j < maze.get(0).length(); j++) {
                //logger.info("i: " + i + " " + "j: " + j);
                if(maze.get(i).isEmpty() || maze.get(i).substring(j).isEmpty()||maze.get(i).substring(j, j+1).isBlank() || maze.get(i).charAt(j) == ' ') {
                    maze_binary[i][j] = 0;
                } else if(maze.get(i).charAt(j) == '#') {
                    maze_binary[i][j] = 1;
                }
            }
        }
    }
    public void printMap() {
        for(int i=0; i < maze_binary.length; i++) {
            for(int j=0; j < maze_binary[0].length; j++) {
                logger.info(maze_binary[i][j] + " ");
            }
            logger.info("\n");
        }
        logger.info("Printing from maze");
        logger.info(Arrays.deepToString(maze_binary));


    }
    public int[][] getMaze() {
        return maze_binary;
    }
}
