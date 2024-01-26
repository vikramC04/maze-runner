package ca.mcmaster.se2aa4.mazerunner;

public class Tile {
    private int x;
    private int y;
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public boolean isX(int x) {
        return this.x == x;
    }

    public boolean isY(int y) {
        return this.y == y;
    }

}
