package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {


    /**
     * Constructor for MazeState
     * @param position the position representing the MazeState
     */
    public MazeState(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("The provided position is null.");
        }
        if (position.getRowIndex() < 0 || position.getColumnIndex() < 0) {
            if (!(position.getRowIndex() == -1 && position.getColumnIndex() == -1)) {
                throw new IllegalArgumentException("Position indexes cannot be negative.");
            }
        }
        this.state = position;
    }

    /**
     * Checks if the MazeState is legal (i.e., not representing a wall or out-of-bounds position)
     * @return true if the state is legal, false otherwise
     */
    @Override
    public boolean validState() {
        Position position = (Position) this.state;
        return !(position.getRowIndex() == -1 && position.getColumnIndex() == -1);
    }

    /**
     * Compares this MazeState with another AState to determine if they are equal
     * @param other the state to compare with
     * @return true if the states are equal, false otherwise
     */
    @Override

    public boolean equalsState(AState other) {
        if (other == null) {
            throw new IllegalArgumentException("The provided AState is null.");
        }
        Position thisPosition = (Position) this.state;
        Position otherPosition = (Position) other.getState();

        return thisPosition.getRowIndex() == otherPosition.getRowIndex() &&  thisPosition.getColumnIndex() == otherPosition.getColumnIndex();
    }

    /**
     * Provides a string representation of the MazeState
     * @return the string representation of the MazeState's position
     */
    @Override
    public String toString() {return this.state.toString();}
}