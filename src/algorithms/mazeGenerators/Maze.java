package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.*;

public class Maze implements Serializable {
    private static final long serialVersionUID = 1L; // for serialization compatibility

    private int rows;
    private int columns;
    private int[][] maze;
    private Position startPosition;
    private Position goalPosition;

    /**
     * constructor 1
     * setting the startPosition of the Maze to be {0, 0}
     * setting the goalPosition of the Maze to be {mazeArr.length - 1, mazeArr[0].length - 1}
     * @param maze 2DArray that presenting the Maze
     */
    public Maze(int[][] maze) {
        if (maze == null) {
            throw new RuntimeException("Array is not valid - null");
        }
        if ((maze.length <= 1) || (maze[0].length <= 1)) {
            throw new RuntimeException("The size of the rows and columns should be greater than 2");
        }
        this.maze = maze;
        // Start position is (0,0)
        this.startPosition = new Position(0, 0);
        // Goal position is (mazeArr.length - 1, mazeArr[0].length - 1)
        this.goalPosition = new Position(maze.length - 1, maze[0].length - 1);
        this.rows = maze.length;
        this.columns = maze[0].length;
    }

    /**
     * constructor 2
     * setting the new Maze by the byte Arr b
     * @param b the byteArray that presenting the Maze
     */
    public Maze(byte[] b) {
        if (b == null) {
            throw new RuntimeException("Array is not valid - null");
        }
        this.rows = bytesToInt(b[0], b[1]);
        this.columns = bytesToInt(b[2], b[3]);
        this.startPosition = new Position(bytesToInt(b[4], b[5]), bytesToInt(b[6], b[7]));
        this.goalPosition = new Position(bytesToInt(b[8], b[9]), bytesToInt(b[10], b[11]));

        if ((this.rows <= 1) || (this.columns <= 1)) {
            throw new RuntimeException("The size of the rows and columns should be greater than 2");
        }
        // initialize the maze
        int[][] maze = new int[this.rows][this.columns];
        int thisIndex = 12;
        for (int i = 0; i < maze.length; i++)
        {
            for (int j = 0; j < maze[0].length; j++)
            {
                maze[i][j] = b[thisIndex];
                thisIndex++;
            }
        }
        this.maze = maze;
    }

    /**
     * gets two bytes and chain them into one int
     * @param a first byte
     * @param b second byte
     * @return int the chained int of the two bytes (int)
     */
    private static int bytesToInt(byte a, byte b) {
        String one = String.valueOf(a);
        String two = String.valueOf(b);
        String c = one + two;
        return Integer.parseInt(c);
    }

    public Position getStartPosition() {return startPosition;}

    public Position getGoalPosition() {return goalPosition;}

    public int[][] getMaze() {return maze;}

    public int getRows() {return rows;}

    public int getColumns() {return columns;}

    /**
     * Turns the Maze representation of the maze to bytes
     * first two indexes represent the rows number
     * next two indexes represent the columns number
     * next four indexes represent the starting position, and then next 4 the goal position
     * the rest of the indexes represent The contents of the maze in one-dimension
     * @return byte[] representation of the maze in format above (byte[])
     */
    public byte[] toByteArray() {
        ArrayList<Integer> IntegerCompressed = new ArrayList<>();

        int row = this.rows;
        int column = this.columns;
        // indexes: 0 - 1
        updateArrayList(row, IntegerCompressed);
        // indexes: 2 - 3
        updateArrayList(column, IntegerCompressed);

        Position s = this.getStartPosition();
        Position f = this.getGoalPosition();
        // indexes: 4 - 5
        updateArrayList(s.getRowIndex(), IntegerCompressed);
        // indexes: 6 - 7
        updateArrayList(s.getColumnIndex(), IntegerCompressed);
        // indexes: 8 - 9
        updateArrayList(f.getColumnIndex(), IntegerCompressed);
        // indexes: 10 - 11
        updateArrayList(f.getColumnIndex(), IntegerCompressed);

        for (int i = 0; i < this.maze.length; i++) {
            for (int j = 0; j < this.maze[0].length; j++) {
                IntegerCompressed.add(this.maze[i][j]);
            }
        }
        return ArrayToByte(IntegerCompressed);
    }

    /**
     * function used to add the first 12 values to our maze array format
     * @param num some number to add to an array list in the our format
     * @param Arr the array to add the number to
     */
    private static void updateArrayList(int num, ArrayList<Integer> Arr) {
        if (Arr == null) {
            throw new RuntimeException("The Array that supplied is not legal (null)");
        }
        if (num <= 127) {
            Arr.add(0);
            Arr.add(num);
        } else {
            Arr.add(num / 10);
            Arr.add(num % 10);
        }
    }

    /**
     * gets an Integer array and turns into a byte array
     * @param Arr Integer array to turn into byte array
     * @return the Updated array (byte[])
     */
    private static byte[] ArrayToByte(ArrayList<Integer> Arr) {
        if (Arr == null) {
            throw new RuntimeException("Array is not valid - null");
        }

        byte[] byteArr = new byte[Arr.size()];
        for (int i = 0; i < Arr.size(); i++) {
            int n = Arr.get(i);
            byteArr[i] = ((byte) n);
        }
        return byteArr;
    }

    /**
     * Gets a maze and checks if another maze is equivalent to it
     * By converting them to byte arrays and comparing them
     * @param other the other Maze
     * @return weather the mazes are equal - true/false (boolean)
     */
    public boolean isEqual(Maze other) {
        byte[] thisMazeArr = this.toByteArray(); // this maze represented by a byte Arr
        byte[] otherMazeArr = other.toByteArray(); // other maze represented by a byte Arr
        if (Arrays.equals(thisMazeArr, otherMazeArr))
            return true;
        return false;
    }

    /**
     * return the maze presentation by the simpleCompressor method
     * @return The maze representation by HashStr (String)
     */
    public String getHashStr() {
        byte[] byteArr = this.toByteArray();
        ArrayList<String> compressedArr = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            compressedArr.add("" + byteArr[i]);
        }

        boolean flag = false;
        byte lastByte = 0;
        int counter = 0;
        for (int i = 12; i < byteArr.length; i++) {
            if ((byteArr[i] == lastByte) && (!flag)) {
                counter++;
                if (counter == 128) {
                    compressedArr.add("127");
                    compressedArr.add("0");
                    counter = 1;
                }
            } else if ((byteArr[i] == lastByte) && (flag)) {
                counter++;
                if (counter == 128) {
                    compressedArr.add("127");
                    compressedArr.add("0");
                    counter = 1;
                }
            } else if ((byteArr[i] != lastByte) && (flag)) {
                compressedArr.add(String.valueOf(counter));
                counter = 1;
                flag = false;
                lastByte = byteArr[i];
            } else if ((byteArr[i] != lastByte) && (!flag)) {
                compressedArr.add(String.valueOf(counter));
                counter = 1;
                flag = true;
                lastByte = byteArr[i];
            }
        }
        if (counter != 0)
            compressedArr.add(String.valueOf(counter));
        String hashStr = "";
        for (int i = 0; i < compressedArr.size(); i++) {
            hashStr += compressedArr.get(i);
        }
        return hashStr;
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
    /*/
    public void print() {
        System.out.println("{");
        System.out.print("{ S ");
        for (int k = 1; k < mazeArr[0].length; k++) {
            if (k != mazeArr[0].length - 1)
                System.out.print(mazeArr[0][k] + " ");
            else
                System.out.println(mazeArr[0][k] + " }");
        }
        for (int i = 1; i < this.mazeArr.length - 1; i++) {
            System.out.print("{ ");
            for (int j = 0; j < mazeArr[0].length; j++) {
                if (j != mazeArr[0].length - 1)
                    System.out.print(mazeArr[i][j] + " ");
                else
                    System.out.println(mazeArr[i][j] + " }");
            }
        }
        System.out.print("{ ");
        for (int t = 0; t < mazeArr[0].length; t++) {
            if (t != mazeArr[0].length - 1)
                System.out.print(mazeArr[mazeArr.length - 1][t] + " ");
            else
                System.out.println("E }");
        }
        System.out.println("}");
    }
     */

}
