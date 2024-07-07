package algorithms.mazeGenerators;


import java.io.Serializable;
import java.util.Objects;

//Position represents a specific location in a maze with row and column indices.
public class Position implements Serializable {

    private static final long serialVersionUID = 1L; // for serialization compatibility
    private int rowIndex;
    private int columnIndex;
    /**
     * Constructs a Position with the specified row and column indices.
     * @param rowIndex the row index of the position
     * @param columnIndex the column index of the position
     * @throws IllegalArgumentException if the row or column indices are negative
     */
    public Position(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || columnIndex < 0) {
            throw new IllegalArgumentException("Row and column indices must be non-negative.");
        }
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {return rowIndex;}

    public int getColumnIndex() {return columnIndex;}


    public void setRowIndex(int rowIndex) {this.rowIndex = rowIndex;}


    public void setColumnIndex(int columnIndex) {this.columnIndex = columnIndex;}


    @Override
    public String toString() {
        return "{" + rowIndex + "," + columnIndex + "}";
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return rowIndex == position.rowIndex && columnIndex == position.columnIndex;
    }

//    @Override
//    public int hashCode() {
//        int result = rowIndex;
//        result = 31 * result + columnIndex;
//        return result;
//    }
//    @Override
//    public int hashCode() {
//        return Objects.hash(rowIndex, columnIndex);
//    }
}