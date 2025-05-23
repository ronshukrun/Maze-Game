package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * SearchableMaze implements the ISearchable interface for a maze.
 * It provides methods to get the start and goal states, check valid states,
 * and manage visited states.
 */
public class SearchableMaze implements ISearchable {

    private Maze maze;
    private int [][] visited;

    /**
     * Constructs a SearchableMaze with the given maze.
     * @param maze the maze to be searched
     * @throws IllegalArgumentException if the provided maze is null or has less than 2 rows or columns
     */
    public SearchableMaze(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException("The maze provided is null");
        }
        if (maze.getRows() < 2 || maze.getColumns() < 2) {
            throw new IllegalArgumentException("The number of rows and columns must be greater then 2.");
        }
        this.maze = maze;
        this.visited = new int[maze.getRows()][maze.getColumns()];
    }

    /**
     * Gets the valid successor states for a given state.
     * @param state the current state
     * @return a list of valid successor states
     * @throws IllegalArgumentException if the provided state is null or has negative position indexes
     */
    @Override
    public ArrayList<AState> getValidStates(AState state) { //getAllSuccessor
        if (state == null) {
            throw new IllegalArgumentException("The state provided is null.");
        }

        Position pos = (Position) state.getState();
        int row = pos.getRowIndex();
        int col = pos.getColumnIndex();

        if (row < 0 || col < 0) {
            throw new IllegalArgumentException("Position indexes cannot be negative.");
        }

        ArrayList<AState> successors = new ArrayList<>();
        checkAndAddSuccessor(successors, row - 1, col); // up
        checkAndAddSuccessor(successors, row + 1, col); // down
        checkAndAddSuccessor(successors, row, col - 1); // left
        checkAndAddSuccessor(successors, row, col + 1); // right
        checkAndAddDiagonalSuccessor(successors, row - 1, col + 1, row - 1, col, row, col + 1); // up-right
        checkAndAddDiagonalSuccessor(successors, row + 1, col + 1, row + 1, col, row, col + 1); // down-right
        checkAndAddDiagonalSuccessor(successors, row + 1, col - 1, row + 1, col, row, col - 1); // down-left
        checkAndAddDiagonalSuccessor(successors, row - 1, col - 1, row - 1, col, row, col - 1); // up-left
        return successors;
    }

    /**
     * Checks and adds a successor state to the list if valid.
     * @param successors the list of successor states
     * @param row the row index of the successor
     * @param col the column index of the successor
     */
    private void checkAndAddSuccessor(List<AState> successors, int row, int col) {
        if (isValidStates(row, col)) {
            successors.add(new MazeState(new Position(row, col)));
        }
    }

    /**
     * Checks and adds a diagonal successor state to the list if valid.
     * @param successors the list of successor states
     * @param row the row index of the diagonal successor
     * @param col the column index of the diagonal successor
     * @param adjRow1 the row index of the first adjacent state
     * @param adjCol1 the column index of the first adjacent state
     * @param adjRow2 the row index of the second adjacent state
     * @param adjCol2 the column index of the second adjacent state
     */
    private void checkAndAddDiagonalSuccessor(List<AState> successors, int row, int col, int adjRow1, int adjCol1, int adjRow2, int adjCol2) {
        if (isValidStates(row, col) && (isValidStates(adjRow1, adjCol1) || isValidStates(adjRow2, adjCol2))) {
            successors.add(new MazeState(new Position(row, col)));
        }
    }

    private boolean isValidStates(int row, int col) {
        if (row >= 0 && col >= 0 && row < maze.getRows())
        {
            if (col < maze.getColumns() && maze.getMaze()[row][col] == 0)
            {
                return true;
            }
        }
        return false ;
    }


    /**
     * Checks if a given state has been visited.
     * @param state the state to check
     * @return true if the state has been visited, false otherwise
     * @throws IllegalArgumentException if the provided state is null
     */
    @Override
    public boolean isVisited(AState state) {
        if (state == null) {
            throw new IllegalArgumentException("The state provided is null.");
        }
        Position pos = (Position) state.getState();
        return visited[pos.getRowIndex()][pos.getColumnIndex()] == 1;
    }

    /**
     * Marks a given state as visited.
     * @param state the state to mark as visited
     * @throws IllegalArgumentException if the provided state is null
     */
    @Override
    public void setVisited(AState state) {
        if (state == null) {
            throw new IllegalArgumentException("The state provided is null.");
        }
        Position pos = (Position) state.getState();
        visited[pos.getRowIndex()][pos.getColumnIndex()] = 1;
    }

    /**
     * Gets the priority order of states.
     * @param stateList the list of states
     * @return a list of states in priority order
     * @throws IllegalArgumentException if the provided state list is null
     */
    @Override
    public ArrayList<AState> getPriorityStates(ArrayList<AState> stateList) {
        if (stateList == null) {
            throw new IllegalArgumentException("The state list provided is null.");
        }

        ArrayList<AState> priorityState = new ArrayList<>();
        priorityState.add(stateList.get(0)); // up
        priorityState.add(stateList.get(3)); // right
        priorityState.add(stateList.get(1)); // down
        priorityState.add(stateList.get(2)); // left
        priorityState.add(stateList.get(4)); // up-right
        priorityState.add(stateList.get(7)); // down-right
        priorityState.add(stateList.get(5)); // down-left
        priorityState.add(stateList.get(6)); // up-left
        return priorityState;
    }

    @Override
    public void clearVisited() {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                visited[i][j] = 0;
            }
        }
    }

    @Override
    public MazeState getStartState() {return new MazeState(maze.getStartPosition());}

    @Override
    public MazeState getGoalState() {return new MazeState(maze.getGoalPosition());}
}