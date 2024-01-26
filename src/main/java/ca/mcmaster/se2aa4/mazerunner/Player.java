package ca.mcmaster.se2aa4.mazerunner;

public class Player {
    private int x;
    private int y;
    private Direction direction;

    private int maze_height;

    private int maze_width;

    int[][] maze_binary;

    public Player(Tile position, Direction start_direction, int[][] maze_map) {
        x = position.getX();
        y = position.getY();
        maze_binary = maze_map.clone();
        direction = start_direction;
        maze_height = maze_map.length;
        maze_width = maze_map[0].length;
    }
    public void setDirection() {

    }
    public boolean isPositionValid() {
        if((y >= maze_height || x >= maze_width || x < 0 || y < 0 || isWall(x,y))) {
            return false;
        }

        return true;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    private boolean isWall(int x, int y) {
        if(maze_binary[y][x] == 1) return true;

        return false;
    }
    public void moveForward() {
        if(direction == Direction.EAST) {
            x++;
        } else if(direction == Direction.SOUTH) {
            y++;
        } else if(direction == Direction.WEST) {
            x--;
        } else if(direction == Direction.NORTH) {
            y--;
        }
    }
    public void turnRight() {
        if(direction == Direction.EAST) {
            direction = Direction.SOUTH;
        } else if(direction == Direction.SOUTH) {
            direction = Direction.WEST;
        } else if(direction == Direction.WEST) {
            direction = Direction.NORTH;
        }else if(direction == Direction.NORTH) {
            direction = Direction.EAST;
        }
    }
    public void turnLeft() {
        if(direction == Direction.EAST) {
            direction = Direction.NORTH;
        } else if(direction == Direction.NORTH) {
            direction = Direction.WEST;
        } else if(direction == Direction.WEST) {
            direction = Direction.SOUTH;
        } else if(direction == Direction.SOUTH) {
            direction = Direction.EAST;
        }
    }
    public boolean isEnd(Tile end) {
        return end.getX() == x && end.getY() == y;
    }
}
