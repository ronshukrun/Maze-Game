package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {

    private int rows;
    private int columns;
    private int[][] maze;
    private Position startPosition;
    private Position goalPosition;


    /**
     * Constructs a maze with the specified number of rows and columns.
     *
     * @param rows The number of rows in the maze.
     * @param columns The number of columns in the maze.
     */
    public Maze(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.maze = new int[rows][columns];

        // Set start and end positions
        Position p1 = generateFramePosition(rows, columns);
        Position p2 = generateFramePosition(rows, columns);
        while (p1.equals(p2)) {
            p2 = generateFramePosition(rows, columns);
        }
        setStartPosition(p1);
        setGoalPosition(p2);
    }

    /**
     * Sets the value of a cell in the maze.
     * @param row  The row index of the cell.
     * @param column The column index of the cell.
     * @param value  The value to set.
     */
    public void setMaze(int row, int column, int value) {this.maze[row][column] = value;}

    /**
     * Sets the start position of the maze.
     * @param startPosition The start position to set.
     */
    public void setStartPosition(Position startPosition) {
        if (startPosition.getRowIndex() < 0 || startPosition.getRowIndex() >= maze.length || startPosition.getColumnIndex() < 0 || startPosition.getColumnIndex() >= maze[0].length) {
            throw new IllegalArgumentException("Invalid start position");
        }
        this.startPosition = startPosition;
    }

    /**
     * Sets the end position of the maze.
     * @param goalPosition The end position to set.
     */
    public void setGoalPosition(Position goalPosition) {
        if (goalPosition.getRowIndex() < 0 || goalPosition.getRowIndex() >= maze.length || goalPosition.getColumnIndex() < 0 || goalPosition.getColumnIndex() >= maze[0].length) {
            throw new IllegalArgumentException("Invalid goal position");
        }
        this.goalPosition = goalPosition;
    }

    /**
     * Sets the number of rows in the maze.
     * @param rows The number of rows to set.
     */
    public void setRows(int rows) {this.rows = rows;}

    /**
     * Sets the number of columns in the maze.
     * @param columns The number of columns to set.
     */
    public void setColumns(int columns) {this.columns = columns;}

    /**
     * Gets the number of rows in the maze.
     * @return The number of rows.
     */
    public int getRows() {return rows;}

    /**
     * Gets the number of columns in the maze.
     * @return The number of columns.
     */
    public int getColumns() {return columns;}

    /**
     * Gets the end position of the maze.
     * @return The end position.
     */
    public Position getGoalPosition() {return goalPosition;}

    /**
     * Gets the start position of the maze.
     * @return The start position.
     */
    public Position getStartPosition() {return startPosition;}

    /**
     * Gets the value of a cell in the maze.
     * @param row    The row index of the cell.
     * @param column The column index of the cell.
     * @return The value of the cell.
     */
    public int getValue(int row, int column) {return maze[row][column];}

    /**
     * Gets the maze as a 2D array.
     * @return The maze.
     */
    public int[][] getMaze() {return maze;}

    /**
     * Returns a randomly chosen position from the frame of the maze.
     * @param rows The number of rows in the maze.
     * @param columns The number of columns in the maze.
     * @return A randomly chosen position from the frame of the maze.
     */
    public Position generateFramePosition(int rows, int columns) {
        List<Position> framePositions = new ArrayList<>();
        //
        // Add points from the top and bottom rows
        for (int j = 0; j < columns; j++) {
            framePositions.add(new Position(0, j));                      // Top row
            framePositions.add(new Position(rows - 1, j));               // Bottom row
        }

        // Add points from the left and right columns
        for (int i = 1; i < rows - 1; i++) {
            framePositions.add(new Position(i, 0));                      // Left column
            framePositions.add(new Position(i, columns - 1));            // Right column
        }

        // Choose a random point from the list
        Random random = new Random();
        int index = random.nextInt(framePositions.size());
        return framePositions.get(index);
    }
    /**
     * print the maze
     */
    public void print1(){
        int StartIdxRow = getStartPosition().getRowIndex();
        int StartIdxCol = getStartPosition().getColumnIndex();
        int GoalIdxRow = getGoalPosition().getRowIndex();
        int GoalIdxCol = getGoalPosition().getColumnIndex();
        System.out.print("{");
        for (int i=0; i< rows; i++){
            System.out.print("{");
            for ( int j=0; j< columns; j++){
                if (i== StartIdxRow && j== StartIdxCol){
                    System.out.print("S");
                }

                else if (i== GoalIdxRow && j== GoalIdxCol){
                    System.out.print("E");
                }
                else {
                    System.out.print(maze[i][j] );
                }
                if (j!=columns-1){
                    System.out.print(',');
                }
            }
            if (i!=rows-1)
                System.out.print("}\n," );
            else
                System.out.print("}");
        }
        System.out.print("}\n");
    }
    public void print() {

        int startRow = getStartPosition().getRowIndex();
        int startCol = getStartPosition().getColumnIndex();
        int goalRow = getGoalPosition().getRowIndex();
        int goalCol = getGoalPosition().getColumnIndex();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == startRow && j == startCol) {
                    System.out.print("S ");
                } else if (i == goalRow && j == goalCol) {
                    System.out.print("E ");
                } else {
                    System.out.print(maze[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    /**
     * Constructs a maze with a specific size and a known start and goal position.
     *
     * @param size The size of the maze (both rows and columns).
     * @param goalPosition The goal position in the maze.
     */
    public Maze(int size, Position goalPosition) {
        this(size, size); // Create a square maze of size x size
        setGoalPosition(goalPosition);
        setStartPosition(new Position(0, 0)); // Assuming start position is always (0, 0) for testing
    }


}
