package ca.mcmaster.se2aa4.mazerunner.maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze implements MazeExtract {
    private String filepath;
    private MazeChar[][] mazeBinary;
    private long execTime;
    private Tile start;
    private Tile end;
    private static final Logger logger = LogManager.getLogger();

    public Maze(String filepath)  throws IOException {
        this.filepath = filepath;
        long startTime = System.nanoTime();
        createMaze();
        long stopTime = System.nanoTime();
        execTime = stopTime - startTime;
    }

    @Override
    public Tile getStartingCoordinates() throws IOException {
        return start;
    }

    private Tile findStart(BufferedReader reader) throws IOException {
        reader.reset();
        String line;
        int counter = 0;
        while((line = reader.readLine()) != null) {
            if(line.isEmpty() || line.charAt(0) == ' ') {
                return new Tile(0,counter);
            }
            counter++;
        }

        return null;
    }
    
    @Override
    public Tile getEndingCoordinates() throws IOException {
        return end;
    }

    private Tile findEnd(BufferedReader reader) throws IOException {
        reader.reset();
        int counter = 1;
        String line;
        int ending = reader.readLine().stripTrailing().length() - 1;
        while((line = reader.readLine()) != null) {
            if(line.isEmpty() || line.charAt(ending) == ' ') {
                return new Tile(ending,counter);
            }
            counter++;
        }

        return null;
    }
    

    private void createMaze() throws IOException {
        
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath));) {
            ArrayList<String> maze = new ArrayList<>();
            String line;
            reader.mark(100000);
            while((line = reader.readLine()) != null) {
                maze.add(line);
            }
            mazeBinary = new MazeChar[maze.size()][maze.get(0).length()];
            for(int i=0; i < maze.size(); i++) {
                for(int j=0; j < maze.get(0).length(); j++) {
                    if(maze.get(i).isEmpty() || maze.get(i).substring(j).isEmpty()||maze.get(i).substring(j, j+1).isBlank() || maze.get(i).charAt(j) == ' ') {
                        mazeBinary[i][j] = MazeChar.SPACE;
                    } else if(maze.get(i).charAt(j) == '#') {
                        mazeBinary[i][j] = MazeChar.WALL;
                    } 
                }
            }
            this.start = findStart(reader);
            this.end = findEnd(reader);
        } catch (Exception e) {
            logger.info(e.getStackTrace());
        } 
        
    }

    @Override
    public MazeChar[][] getMaze() {
        return mazeBinary;
    }
    
    @Override
    public double getExecutionTime() {
        return execTime * Math.pow(10,-6);
    }
}
