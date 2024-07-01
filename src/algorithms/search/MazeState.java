package algorithms.search;
<<<<<<< HEAD

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {


    /**
     * Constructor for MazeState
     * @param position the position representing the MazeState
=======
import algorithms.mazeGenerators.Position;

/**
 * MazeState represents a state in a maze. It extends the AState class and provides
 * functionality specific to maze states, such as checking validity and equality.
 */
public class MazeState extends AState {

    /**
     * Constructs a MazeState with the given position.
     * @param position the position of the state in the maze
     * @throws IllegalArgumentException if the provided position is null or has negative indexes
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
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
<<<<<<< HEAD
     * Checks if the MazeState is legal (i.e., not representing a wall or out-of-bounds position)
     * @return true if the state is legal, false otherwise
=======
     * Checks if the state is valid (i.e., not an invalid position).
     * @return true if the state is valid, false otherwise
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
     */
    @Override
    public boolean validState() {
        Position position = (Position) this.state;
        return !(position.getRowIndex() == -1 && position.getColumnIndex() == -1);
    }
<<<<<<< HEAD

    /**
     * Compares this MazeState with another AState to determine if they are equal
     * @param other the state to compare with
     * @return true if the states are equal, false otherwise
     */
    @Override

=======
    /**
     * Checks if this state is equal to another state.
     * @param other the state to compare to
     * @return true if the states are equal, false otherwise
     * @throws IllegalArgumentException if the provided state is null
     */
    @Override
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
    public boolean equalsState(AState other) {
        if (other == null) {
            throw new IllegalArgumentException("The provided AState is null.");
        }
        Position thisPosition = (Position) this.state;
        Position otherPosition = (Position) other.getState();

        return thisPosition.getRowIndex() == otherPosition.getRowIndex() &&  thisPosition.getColumnIndex() == otherPosition.getColumnIndex();
    }

<<<<<<< HEAD
    /**
     * Provides a string representation of the MazeState
     * @return the string representation of the MazeState's position
     */
=======

>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
    @Override
    public String toString() {return this.state.toString();}
}