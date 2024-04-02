package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Maze {
    private String filepath;
    private int[][] maze_binary;
    public Maze(String filepath) throws IOException {
        this.filepath = filepath;
        createMaze();
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
        reader.close();

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

        reader.close();
        return null;
    }
    private void createMaze() throws IOException {
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
        reader.close();
    }
    public int[][] getMaze() {
        return maze_binary;
    }
}
