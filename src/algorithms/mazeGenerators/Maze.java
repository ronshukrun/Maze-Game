package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {

    private int rows;
    private int columns;
    private int[][] maze;
    private Position startPosition;
    private Position endPosition;


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
        setEndPosition(p2);
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
    public void setStartPosition(Position startPosition) {this.startPosition = startPosition;}

    /**
     * Sets the end position of the maze.
     * @param endPosition The end position to set.
     */
    public void setEndPosition(Position endPosition) {this.endPosition = endPosition;}

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
    public Position getEndPosition() {return endPosition;}

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

    /////////////////////////////////////////////////////////////////////////////////////////////////////


//
//    public Maze(byte[] byteArr){
//        //rows = 0;
//        rows = byteArr[0] < 0 ? byteArr[0]+256 : byteArr[0];
//        rows |= (byteArr[1] << 8);
//        columns = byteArr[2]< 0 ? byteArr[2]+256 : byteArr[2];;
//        columns |= (byteArr[3] << 8);
//        int rowStart = byteArr[4]< 0 ? byteArr[4]+256 : byteArr[4];;
//        rowStart |= (byteArr[5] << 8);
//        int colStart= byteArr[6]< 0 ? byteArr[6]+256 : byteArr[6];;
//        colStart |= (byteArr[7] << 8);
//        int rowEnd = byteArr[8]< 0 ? byteArr[8]+256 : byteArr[8];;
//        rowEnd |= (byteArr[9] << 8);
//        int colEnd= byteArr[10]< 0 ? byteArr[10]+256 : byteArr[10];;
//        colEnd |= (byteArr[11] << 8);
//        setStartPoint(rowStart, colStart);
//        setEndPoint(rowEnd,colEnd);
//        this.mazeArr= new int[rows][columns];
//        int b = 12;
//        for (int r=0; r<rows; r++){
//            for (int c=0; c<columns; c++){
//                setMazeArr(r,c,byteArr[b]);
//                b++;
//            }
//        }
//    }






//    /**
//     * @return number of the rows in the maze
//     */
//    public int getRows() {
//        return mazeArr.length;
//    }
//
//    /**
//     * @return number of the columns in the maze
//     */
//    public int getColumns() {
//        return mazeArr[0].length;
//    }


//    /**
//     * print the maze
//     */
//    public void print(){
//        int StartIdxRow =getStartPosition().getRowIndex();
//        int StartIdxCol = getStartPosition().getColumnIndex();
//        int GoalIdxRow= getGoalPosition().getRowIndex();
//        int GoalIdxCol= getGoalPosition().getColumnIndex();
//        System.out.print("{");
//        for (int i=0; i< rows; i++){
//            System.out.print("{");
//            for ( int j=0; j< columns; j++){
//                if (i== StartIdxRow && j== StartIdxCol){
//                    System.out.print("S");
//                }
//                else if (i== GoalIdxRow && j== GoalIdxCol){
//                    System.out.print("E");
//                }
//                else {
//                    System.out.print(mazeArr[i][j] );
//                }
//                if (j!=columns-1){
//                    System.out.print(',');
//                }
//            }
//            if (i!=rows-1)
//                System.out.print("}\n," );
//            else
//                System.out.print("}");
//        }
//        System.out.print("}\n");
//    }
//
//    /**
//     *  the details of the maze get in an array of bytes
//     * @return the maze in byte array
//     */
//    public byte[] toByteArray(){
//        byte[] byteArr = new byte[rows*columns+12];
//        byteArr[0] = (byte)(rows & 255);
//        byteArr[1] = (byte)((rows >> 8) & 255);
//        byteArr[2] = (byte)(columns & 255);
//        byteArr[3] = (byte)((columns >> 8) & 255);
//        byteArr[4] = (byte)(getStartPosition().getRowIndex()& 255);
//        byteArr[5] = (byte)((getStartPosition().getRowIndex() >> 8 ) & 255);
//        byteArr[6] = (byte)(getStartPosition().getColumnIndex() & 255);
//        byteArr[7] = (byte)((getStartPosition().getColumnIndex() >> 8 ) & 255);
//        byteArr[8] = (byte)(getGoalPosition().getRowIndex() & 255);
//        byteArr[9] = (byte)((getGoalPosition().getRowIndex() >> 8 ) & 255);
//        byteArr[10] = (byte)(getGoalPosition().getColumnIndex() & 255);
//        byteArr[11] = (byte)((getGoalPosition().getColumnIndex() >> 8 ) & 255);
//        int b=12;
//        for(int r=0; r<rows; r++){
//            for(int c=0; c<columns; c++){
//                byteArr[b] = (byte)getCellValue(r,c);
//                b++;
//            }
//        }
//        return byteArr;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Maze maze = (Maze) o;
//        return rows == maze.rows && columns == maze.columns && Arrays.equals(mazeArr, maze.mazeArr) && StartPoint.equals(maze.StartPoint) && EndPoint.equals(maze.EndPoint);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hash(rows, columns, StartPoint, EndPoint);
//        result = 31 * result + Arrays.hashCode(mazeArr);
//        return result;
//    }
}
