package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {

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


    @Override
    public boolean validState() {
        Position position = (Position) this.state;
        return !(position.getRowIndex() == -1 && position.getColumnIndex() == -1);
    }

    @Override
    public boolean equalsState(AState other) {
        if (other == null) {
            throw new IllegalArgumentException("The provided AState is null.");
        }
        Position thisPosition = (Position) this.state;
        Position otherPosition = (Position) other.getState();

        return thisPosition.getRowIndex() == otherPosition.getRowIndex() &&  thisPosition.getColumnIndex() == otherPosition.getColumnIndex();
    }


    @Override
    public String toString() {return this.state.toString();}
}