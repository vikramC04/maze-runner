package ca.mcmaster.se2aa4.mazerunner;

public interface Solver {
    void solve();
    String pathFinding(Tile end, Player player);
}
