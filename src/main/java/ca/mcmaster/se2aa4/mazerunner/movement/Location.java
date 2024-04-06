package ca.mcmaster.se2aa4.mazerunner.movement;

import ca.mcmaster.se2aa4.mazerunner.movement.Direction;

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
                return new Location(this.x, this.y-1);
            }
            case EAST -> {
                return new Location(this.x+1, this.y);
            }
            case SOUTH -> {
                return new Location(this.x, this.y+1);
            }
            case WEST -> {
                return new Location(this.x-1, this.y);
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

    @Override
    public String toString() {
        String result = String.format("(%d,%d)", this.x,this.y);
        return result;
    }
    
}
