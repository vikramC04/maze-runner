package ca.mcmaster.se2aa4.mazerunner;


public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Location move(Direction direction) {
        switch (direction) {
            case NORTH -> {
                this.y -= 1;
                break;
            }
            case EAST -> {
                this.x += 1;
                break;
            }
            case SOUTH -> {
                this.y += 1;
                break;
            }
            case WEST -> {
                this.x -= 1;
                break;
            }
        }
        throw new IllegalStateException("Uncounted Value: " + this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}
