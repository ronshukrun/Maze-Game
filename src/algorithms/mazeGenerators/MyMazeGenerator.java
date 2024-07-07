package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        if ((rows <= 1) || (columns <= 1)) {
            throw new RuntimeException("The size of the rows and columns should be greater than 2");
        }
        if(rows == 2 && columns == 2){
            return generate2on2();
        }
        int[][] maze = new int[rows][columns];

        for (int i = 0; i < maze.length; i++)
        {
            for (int j = 0; j < maze[0].length; j++)
                maze[i][j] = 1;
        }
        //start position
        maze[0][0] = 0;
        ArrayList<Position> FrontVal1 = new ArrayList<Position>();
        Position thisPose = new Position(0, 0);
        Position[] startPoseNeighbors = findLegalNeighbors(thisPose, maze.length, maze[0].length);
        for (int i = 0; i < 4; i++)
        {
            if (startPoseNeighbors[i] == null)
                break;
            if ((maze[startPoseNeighbors[i].getRowIndex()][startPoseNeighbors[i].getColumnIndex()] == 1))
                FrontVal1.add(startPoseNeighbors[i]);
        }

        while (!(FrontVal1.isEmpty())) {
            int randomPick = (int) (Math.random() * (FrontVal1.size()));
            // Random Wall
            Position randFront = FrontVal1.get(randomPick);
            ArrayList<Position> BackVal0 = new ArrayList<Position>();
            Position[] randFrontNeighbors = findLegalNeighbors(randFront, maze.length, maze[0].length);

            for (int i = 0; i < 4; i++)
            {
                if (randFrontNeighbors[i] == null)
                    break;
                if ((maze[randFrontNeighbors[i].getRowIndex()][randFrontNeighbors[i].getColumnIndex()] == 0))
                    BackVal0.add(randFrontNeighbors[i]);
                if ((maze[randFrontNeighbors[i].getRowIndex()][randFrontNeighbors[i].getColumnIndex()] == 1))
                    FrontVal1.add(randFrontNeighbors[i]);
            }

            int randomNei = (int) (Math.random() * (BackVal0.size()));
            // choose a random Position from randNeighbor and change the value of the cell between them and the randFront to 0
            Position randBack = BackVal0.get(randomNei);
            // updating the values of the wanted Positions
            updatePositionsVal(randFront, randBack, maze);
            FrontVal1.remove(randFront);
        }
        FinishMaze(maze);
        maze[maze.length-1][maze[0].length-1] = 0;
        Maze newMaze = new Maze(maze);
        return newMaze;
    }

    /**
     * gets a Position, and returns all the Possible legal moves (Positions) from
     * the specific Position by the row and columns borders
     * @param position The Specific Position
     * @param rows The number of rows in the maze
     * @param columns The number of columns in the maze
     * @return List of possible Positions (Position[])
     */
    public static Position[] findLegalNeighbors(Position position, int rows, int columns) {
        if (position == null) {
            throw new RuntimeException("The Position is not valid - null");
        }
        if ((position.getRowIndex() < 0) || (position.getColumnIndex() < 0)) {
            if (!((position.getRowIndex() == -1) && (position.getColumnIndex() == -1)))
                throw new RuntimeException("One or more of the Position indexes are not legal! Position can't have negative indexes");
        }
        if((rows <= 1) || (columns <= 1)) {
            throw new RuntimeException("The size of the rows and columns should be greater than 2");
        }

        int NeighborsCounter = 0;
        Position[] poseArr = new Position[4];
        if (position.getRowIndex() - 2 >= 0) {
            poseArr[NeighborsCounter] = new Position(position.getRowIndex() - 2, position.getColumnIndex()); // upper
            NeighborsCounter++;
        }
        if (position.getRowIndex() + 2 < rows) {
            poseArr[NeighborsCounter] = new Position(position.getRowIndex() + 2, position.getColumnIndex()); // lower
            NeighborsCounter++;
        }
        if (position.getColumnIndex() + 2 < columns) {
            poseArr[NeighborsCounter] = new Position(position.getRowIndex(), position.getColumnIndex() + 2); // right
            NeighborsCounter++;
        }
        if (position.getColumnIndex() - 2 >= 0) {
            poseArr[NeighborsCounter] = new Position(position.getRowIndex(), position.getColumnIndex() - 2); // left
        }
        return poseArr;
    }

    /**
     * Update the values of 2 Positions:
     * 1. Back Position
     * 2. the Position between the Front and the Back
     * @param front The "front" Position
     * @param back The "back" Position
     * @param mazeArr The 2D Array representing the Maze
     */
    private void updatePositionsVal(Position front, Position back, int[][] mazeArr) {
        if((front == null) || (back == null) || (mazeArr == null)) {
            throw new RuntimeException("One of the Arguments supplied is not valid - null");
        }
        if((front.getRowIndex() < 0) || (front.getColumnIndex() < 0)) {
            if(!((front.getRowIndex() == -1) && (front.getColumnIndex() == -1)))
                throw new RuntimeException("One or more of the Position indexes are not legal! Position can't have negative indexes");
        }
        if((back.getRowIndex() < 0) || (back.getColumnIndex() < 0)) {
            if(!((back.getRowIndex() == -1) && (back.getColumnIndex() == -1)))
                throw new RuntimeException("One or more of the Position indexes are not legal! Position can't have negative indexes");
        }

        if (front.getRowIndex() == back.getRowIndex()) {
            mazeArr[front.getRowIndex()][front.getColumnIndex()] = 0;
            mazeArr[front.getRowIndex()][(front.getColumnIndex() + back.getColumnIndex()) / 2] = 0;
        }
        if (front.getColumnIndex() == back.getColumnIndex()) {
            mazeArr[front.getRowIndex()][front.getColumnIndex()] = 0;
            mazeArr[(front.getRowIndex() + back.getRowIndex()) / 2][front.getColumnIndex()] = 0;
        }
    }

    /**
     * In cases of different Row/Columns combinations,
     * we Make sure there will be a Path to the GoalState
     * @param maze The 2D Array representing the Maze
     */
    private void FinishMaze(int[][] maze) {
        if(maze == null) {
            throw new RuntimeException("The Array that  is not valid -null");
        }
        if(maze.length % 2 != 0 && maze[0].length % 2 != 0)
            return;
        if(maze.length % 2 == 0 && maze[0].length % 2 == 0) { // even even
            for(int i = 0; i < maze.length - 1; i++){
                maze[i][maze[0].length-1] = (int) (Math.random() * 2);
            }
            for(int j = 0; j < maze[0].length - 1; j++){
                maze[maze.length - 1][j] = (int) (Math.random() * 2);
            }
            maze[maze.length-2][maze[0].length-1] = 0;
        }
        if(maze.length % 2 == 0 && maze[0].length % 2 != 0) { //even odd
            for(int j = 0; j < maze[0].length - 1; j++){
                maze[maze.length - 1][j] = (int) (Math.random() * 2);
            }
        }
        if(maze.length % 2 != 0 && maze[0].length % 2 == 0) { //odd even
            for(int i = 0; i < maze.length - 1; i++){
                maze[i][maze[0].length-1] = (int) (Math.random() * 2);
            }
        }
    }

    /**
     * In a case the Row=Column=2
     * @return the new maze (Maze)
     */
    public Maze generate2on2(){
        int[][] maze = new int[2][2];
        int randomizeMaze = (int) (Math.random() * 2);
        if(randomizeMaze == 0){
            maze[1][0] = 1;
        }
        if(randomizeMaze == 1){
            maze[0][1] = 1;
        }
        Maze newMaze = new Maze(maze);
        return newMaze;
    }
}
