package algorithms.mazeGenerators;

public class Position {

    private final int rowIndex;
    private final int columnIndex;

    public Position(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || columnIndex < 0) {
            throw new IllegalArgumentException("Row and column indices must be non-negative.");
        }
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {return rowIndex;}

    public int getColumnIndex() {return columnIndex;}

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

    @Override
    public int hashCode() {
        int result = rowIndex;
        result = 31 * result + columnIndex;
        return result;
    }
}