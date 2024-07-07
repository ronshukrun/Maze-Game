package algorithms.search;
import algorithms.mazeGenerators.Position;

import java.io.Serializable;

/**
 * MazeState represents a state in a maze. It extends the AState class and provides
 * functionality specific to maze states, such as checking validity and equality.
 */
public class MazeState extends AState implements Serializable {

    /**
     * Constructs a MazeState with the given position.
     * @param position the position of the state in the maze
     * @throws IllegalArgumentException if the provided position is null or has negative indexes
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
     * Checks if the state is valid (i.e., not an invalid position).
     * @return true if the state is valid, false otherwise
     */
    @Override
    public boolean validState() {
        Position position = (Position) this.state;
        return !(position.getRowIndex() == -1 && position.getColumnIndex() == -1);
    }
    /**
     * Checks if this state is equal to another state.
     * @param other the state to compare to
     * @return true if the states are equal, false otherwise
     * @throws IllegalArgumentException if the provided state is null
     */
    @Override
    public boolean equalsState(AState other) {
        if (other == null) {
            throw new IllegalArgumentException("The provided AState is null.");
        }

        Object thisState = this.getState();
        Object otherState = other.getState();

        Position thisPose = ((Position) thisState);
        Position otherPose = ((Position) otherState);

        return (thisPose.getRowIndex() == otherPose.getRowIndex()) && (thisPose.getColumnIndex() == otherPose.getColumnIndex());
    }


    @Override
    public String toString() {
        if (this.state == null) {
            return "State is not initialized";
    }
        return this.state.toString();}
}