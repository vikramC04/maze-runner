package ca.mcmaster.se2aa4.mazerunner.maze;

public class Tile {
    private int x;
    private int y;
    
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] findCoords() {
        return new int[] {x,y};
    }

    public boolean isX(int x) {
        return this.x == x;
    }

    public boolean isY(int y) {
        return this.y == y;
    }

}
