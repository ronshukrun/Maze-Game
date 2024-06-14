package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MyMazeGenerator extends AMazeGenerator{

    private Maze myMaze;
    private Position[][] positions;
    private List<Position> candidates;


    public MyMazeGenerator() {candidates = new ArrayList<>();}

    @Override
    public Maze generate(int rows, int columns) {
        if (rows < 2) rows = 2;
        if (columns < 2) columns = 2;

        myMaze = new Maze(rows, columns);
        positions = new Position[rows][columns];
        initializeMaze(rows, columns);
        candidates.add(myMaze.getStartPosition());

        while (!candidates.isEmpty()) {
            Position currPosition = candidates.get(new Random().nextInt(candidates.size()));
            if (CanBeChange(currPosition)) {
                if (CanBeChange(currPosition)) {
                    myMaze.setMaze(currPosition.getRowIndex(), currPosition.getColumnIndex(), 0);
                }
                Position prePosition = getPrePosition(currPosition);
                breakingWall(prePosition, currPosition);
                addCandidates(currPosition);
            }
            candidates.remove(currPosition);
        }
        if (rows == 2 && columns == 2)
            Maze2x2();
        else EndPosition();

        return myMaze;
    }


    public void initializeMaze(int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                myMaze.setMaze(i, j, 1);
                positions[i][j] = new Position(i, j);
            }
        }
    }

    public void Maze2x2() {
        myMaze.setMaze(myMaze.getStartPosition().getRowIndex(), myMaze.getStartPosition().getColumnIndex(), 0);

        myMaze.setMaze(myMaze.getGoalPosition().getRowIndex(), myMaze.getGoalPosition().getColumnIndex(), 0);

        int colIndexS= myMaze.getStartPosition().getColumnIndex();
        int rowIndexS= myMaze.getStartPosition().getRowIndex();
        int colIndexE= myMaze.getGoalPosition().getColumnIndex();
        int rowIndexE= myMaze.getGoalPosition().getRowIndex();

        if (colIndexS != colIndexE && rowIndexS != rowIndexE)
        {
            if ((colIndexS == 0 && rowIndexS == 0) || (colIndexE == 0 && rowIndexE == 0))
                myMaze.setMaze(0, 1, 0);
            else
                myMaze.setMaze(0, 0, 0);
        }
    }

    public Position getPrePosition(Position position) {

        List<Position> neighbours = Neighbours(position);
        List<Position> prePositionPotential = new ArrayList<>();

        for (Position neighbour : neighbours) {
            if (myMaze.getValue(neighbour.getRowIndex(), neighbour.getColumnIndex()) == 0)
                prePositionPotential.add(neighbour);
        }

        if (prePositionPotential.isEmpty())
        {
            return null;
        }
        return prePositionPotential.get(new Random().nextInt(prePositionPotential.size()));
    }

    private boolean breakingWall(Position p1, Position p2) {
        if (p1 == null || p2 == null)
        {
            return false;
        }

        if (p1.getRowIndex() == p2.getRowIndex())
        {
            myMaze.setMaze(p1.getRowIndex(), Math.min(p1.getColumnIndex(), p2.getColumnIndex()) + 1, 0);
        }
        else if (p1.getColumnIndex() == p2.getColumnIndex())
        {
            myMaze.setMaze(Math.min(p1.getRowIndex(), p2.getRowIndex()) + 1, p1.getColumnIndex(), 0);
        }
        return true;
    }

    public List<Position> Neighbours(Position p) {

        List<Position> neighbours = new ArrayList<>();
        int row = p.getRowIndex();
        int col = p.getColumnIndex();
        if (isValid(row - 2, col))
            neighbours.add(positions[row - 2][col]);
        if (isValid(row + 2, col))
            neighbours.add(positions[row + 2][col]);

        if (isValid(row, col - 2))
            neighbours.add(positions[row][col - 2]);

        if (isValid(row ,col + 2))
            neighbours.add(positions[row][col + 2]);

        return neighbours;
    }

    public void addCandidates(Position p) {

        List<Position> neighbours = Neighbours(p);
        for (Position neighbour : neighbours) {
            if (myMaze.getValue(neighbour.getRowIndex(), neighbour.getColumnIndex()) == 1 && CanBeChange(neighbour))
            {
                candidates.add(neighbour);
            }
        }
    }

    public boolean CanBeChange(Position p) {
        if (myMaze.getValue(p.getRowIndex(), p.getColumnIndex()) == 1)
            return true;
        return false ;
    }

    public boolean isValid(int row, int column) {
        if (row >= 0 && row < myMaze.getRows() && column >= 0 && column < myMaze.getColumns())
            return true;
        return false ;
    }


    public void EndPosition() {
        boolean b = false;
        while (!b) {
            Position p = myMaze.generateFramePosition(myMaze.getRows(), myMaze.getColumns());
            if (myMaze.getValue(p.getRowIndex(), p.getColumnIndex()) == 0 &&
                    !p.equals(myMaze.getStartPosition())) {
                Position position = new Position(p.getRowIndex(), p.getColumnIndex());
                myMaze.setGoalPosition(position);
                b = true;
            }
        }
    }
}