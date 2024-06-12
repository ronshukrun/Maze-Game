package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable {

    private final int rowIndex;
    private final int columnIndex;

    /**
     * Constructor to initialize a new Position with the specified row and column indices.
     *
     * @param rowIndex    The row index of the Position
     * @param columnIndex The column index of the Position
     */
    public Position(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || columnIndex < 0) {
            throw new IllegalArgumentException("Row and column indices must be non-negative.");
        }
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    /**
     * @return The row index of the Position
     */
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * @return The column index of the Position
     */
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     * @return A string representation of the Position in the format "{row,column}"
     */
    @Override
    public String toString() {
        return "{" + rowIndex + "," + columnIndex + "}";
    }

    /**
     * Calculate the Manhattan distance from this Position to another Position.
     *
     * @param other The other Position to calculate the distance to
     * @return The Manhattan distance to the other Position
     */
    public int getDistance(Position other) {
        if (other == null) {
            throw new IllegalArgumentException("Other position cannot be null.");
        }
        int deltaX = Math.abs(this.getColumnIndex() - other.getColumnIndex());
        int deltaY = Math.abs(this.getRowIndex() - other.getRowIndex());
        return deltaX + deltaY;
    }

    /**
     * Check if this Position is equal to another Position
     *
     * @param obj The object to compare to
     * @return true if the positions are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return rowIndex == position.rowIndex && columnIndex == position.columnIndex;
    }

    /**
     * @return A hash code for this Position
     */
    @Override
    public int hashCode() {
        int result = rowIndex;
        result = 31 * result + columnIndex;
        return result;
    }
}