package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Direction nextRight() {
        Direction nextDir;
        switch (this) {
            case NORTH:
                nextDir = EAST;
                break;
            case SOUTH:
                nextDir = WEST;
                break;
            case WEST:
                nextDir = NORTH;
                break;
            case EAST:
                nextDir = SOUTH;
                break;
            default:
                throw new IllegalStateException(String.format("Unexpected value: %s", this));

        }
        return nextDir;
    }

    public Direction nextLeft() {
        Direction nextDir;
        switch (this) {
            case NORTH:
                nextDir = WEST;
                break;
            case SOUTH:
                nextDir = EAST;
                break;
            case WEST:
                nextDir = SOUTH;
                break;
            case EAST:
                nextDir = NORTH;
                break;
            default:
                throw new IllegalStateException(String.format("Unexpected value: %s", this));

        }
        return nextDir;
    }
}
