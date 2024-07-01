package algorithms.mazeGenerators;

<<<<<<< HEAD
=======
import java.nio.ByteBuffer;
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

<<<<<<< HEAD
=======
/**
 * Maze represents a maze with a specified number of rows and columns.
 * It includes methods to set and get the values of the maze cells,
 * as well as to set and get the start and goal positions.
 */
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
public class Maze {

    private int rows;
    private int columns;
    private int[][] maze;
    private Position startPosition;
    private Position goalPosition;

<<<<<<< HEAD

    /**
     * Constructs a maze with the specified number of rows and columns.
     *
     * @param rows The number of rows in the maze.
     * @param columns The number of columns in the maze.
=======
    /**
     * Constructs a Maze with the specified number of rows and columns.
     * @param rows the number of rows in the maze
     * @param columns the number of columns in the maze
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
     */
    public Maze(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.maze = new int[rows][columns];

<<<<<<< HEAD
        // Set start and end positions
=======
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
        Position p1 = generateFramePosition(rows, columns);
        Position p2 = generateFramePosition(rows, columns);
        while (p1.equals(p2)) {
            p2 = generateFramePosition(rows, columns);
        }
        setStartPosition(p1);
        setGoalPosition(p2);
    }
<<<<<<< HEAD

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
=======
    public Maze(byte[] byteArray) {
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);

        // Read dimensions of the maze
        this.rows = buffer.getShort();
        this.columns = buffer.getShort();

        // Initialize maze array
        this.maze = new int[rows][columns];

        // Read maze content
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze[i][j] = buffer.get();
            }
        }

        // Read start and goal positions
        int startRow = buffer.getInt();
        int startCol = buffer.getInt();
        int goalRow = buffer.getInt();
        int goalCol = buffer.getInt();

        this.startPosition = new Position(startRow, startCol);
        this.goalPosition = new Position(goalRow, goalCol);
    }
    //Sets the value of a specific cell in the maze.
    public void setMaze(int row, int column, int value) {this.maze[row][column] = value;}

    //Sets the start position in the maze.
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
    public void setStartPosition(Position startPosition) {
        if (startPosition.getRowIndex() < 0 || startPosition.getRowIndex() >= maze.length || startPosition.getColumnIndex() < 0 || startPosition.getColumnIndex() >= maze[0].length) {
            throw new IllegalArgumentException("Invalid start position");
        }
        this.startPosition = startPosition;
    }
<<<<<<< HEAD

    /**
     * Sets the end position of the maze.
     * @param goalPosition The end position to set.
     */
=======
    //Sets the goal position in the maze.
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
    public void setGoalPosition(Position goalPosition) {
        if (goalPosition.getRowIndex() < 0 || goalPosition.getRowIndex() >= maze.length || goalPosition.getColumnIndex() < 0 || goalPosition.getColumnIndex() >= maze[0].length) {
            throw new IllegalArgumentException("Invalid goal position");
        }
        this.goalPosition = goalPosition;
    }

<<<<<<< HEAD
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
=======
    public void setRows(int rows) {this.rows = rows;}

    public void setColumns(int columns) {this.columns = columns;}

    public int getRows() {return rows;}

    public int getColumns() {return columns;}

    public Position getGoalPosition() {return goalPosition;}

    public Position getStartPosition() {return startPosition;}

    public int getValue(int row, int column) {return maze[row][column];}

    public int[][] getMaze() {return maze;}

    /**
     * Generates a random position on the frame (border) of the maze.
     * @param rows the number of rows in the maze
     * @param columns the number of columns in the maze
     * @return the generated frame position
     */
    public Position generateFramePosition(int rows, int columns) {
        List<Position> framePositions = new ArrayList<>();
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
        // Add points from the top and bottom rows
        for (int j = 0; j < columns; j++) {
            framePositions.add(new Position(0, j));                      // Top row
            framePositions.add(new Position(rows - 1, j));               // Bottom row
        }
<<<<<<< HEAD

=======
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
        // Add points from the left and right columns
        for (int i = 1; i < rows - 1; i++) {
            framePositions.add(new Position(i, 0));                      // Left column
            framePositions.add(new Position(i, columns - 1));            // Right column
        }
<<<<<<< HEAD

=======
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
        // Choose a random point from the list
        Random random = new Random();
        int index = random.nextInt(framePositions.size());
        return framePositions.get(index);
    }
<<<<<<< HEAD
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
=======


>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
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
<<<<<<< HEAD
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
=======
    ///opt1
    /**
     * Converts the maze to a byte array representation.
     *
     * @return the byte array representing the maze
     */
//    public byte[] toByteArray() {
//        // Calculate total size of byte array needed
//        int totalSize = 20 + (rows * columns); // 20 bytes for dimensions and positions
//
//        // Create byte array
//        byte[] byteArray = new byte[totalSize];
//
//        // Store dimensions of the maze
//        byteArray[0] = (byte) (rows >> 8);
//        byteArray[1] = (byte) rows;
//        byteArray[2] = (byte) (columns >> 8);
//        byteArray[3] = (byte) columns;
//
//        // Store maze content
//        int index = 4;
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                byteArray[index++] = (byte) maze[i][j];
//            }
//        }
//
//        // Store start and goal positions
//        byteArray[index++] = (byte) (startPosition.getRowIndex() >> 24);
//        byteArray[index++] = (byte) (startPosition.getRowIndex() >> 16);
//        byteArray[index++] = (byte) (startPosition.getRowIndex() >> 8);
//        byteArray[index++] = (byte) startPosition.getRowIndex();
//        byteArray[index++] = (byte) (startPosition.getColumnIndex() >> 24);
//        byteArray[index++] = (byte) (startPosition.getColumnIndex() >> 16);
//        byteArray[index++] = (byte) (startPosition.getColumnIndex() >> 8);
//        byteArray[index++] = (byte) startPosition.getColumnIndex();
//        byteArray[index++] = (byte) (goalPosition.getRowIndex() >> 24);
//        byteArray[index++] = (byte) (goalPosition.getRowIndex() >> 16);
//        byteArray[index++] = (byte) (goalPosition.getRowIndex() >> 8);
//        byteArray[index++] = (byte) goalPosition.getRowIndex();
//        byteArray[index++] = (byte) (goalPosition.getColumnIndex() >> 24);
//        byteArray[index++] = (byte) (goalPosition.getColumnIndex() >> 16);
//        byteArray[index++] = (byte) (goalPosition.getColumnIndex() >> 8);
//        byteArray[index++] = (byte) goalPosition.getColumnIndex();
//
//        return byteArray;
//    }
    /// opt2
    public byte[] toByteArray() {
        List<Byte> out = new ArrayList<Byte>();
        byte[] rowInByte = ByteBuffer.allocate(4).putInt(rows).array();
        byte[] colInByte = ByteBuffer.allocate(4).putInt(columns).array();
        byte[] enterPosX = ByteBuffer.allocate(4).putInt(startPosition.getRowIndex()).array();
        byte[] enterPosY = ByteBuffer.allocate(4).putInt(startPosition.getColumnIndex()).array();
        byte[] exitPosX = ByteBuffer.allocate(4).putInt(goalPosition.getRowIndex()).array();
        byte[] exitPosY = ByteBuffer.allocate(4).putInt(goalPosition.getColumnIndex()).array();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int[] array : maze) {
            for (int i : array) {
                list.add(i);
            }
        }
        ArrayToList(rowInByte,out);
        ArrayToList(colInByte,out);
        ArrayToList(enterPosX,out);
        ArrayToList(enterPosY,out);
        ArrayToList(exitPosX,out);
        ArrayToList(exitPosY,out);

        for(int i :list)
            out.add((Integer.valueOf(i).byteValue()));

        byte[] sul = new byte[out.size()];
        for(int i= 0 ; i<sul.length;i++)
            sul[i]=out.get(i);
        return sul;
    }
    private void ArrayToList( byte[] b , List<Byte> lst){
        for(int i = 0; i< b.length;i++){
            lst.add(b[i]);
        }
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
    }


}
