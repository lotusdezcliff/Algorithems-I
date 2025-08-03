public class Percolation {
    private final boolean[][] grid;
    private boolean[][] visited;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        grid = new boolean[n][n];
        visited = new boolean[n][n];
        /* 
        //Default as false, so no need to loop it
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
        */
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        grid[row][col] = true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // check the edge
        if (row < 0 || row >= grid.length || col < 0 || col >= grid.length) {
            return false;
        }
        // check the visited site
        if (visited[row][col]) {
            return false;
        }
        // directions
        boolean up = grid[row - 1][col];
        boolean down = grid[row + 1][col];
        boolean left = grid[row][col - 1];
        boolean right = grid[row][col + 1];

        // edge case (exist path)
        if (row == 0) {
            return true;
        }

        // edge case (not a open site)
        if (isOpen(row, col) == false) {
            return false;
        }

        // edge case (not exist)
        if (!(up && down && left && right)) {
            return false;
        }
        
        // Recursion
        visited[row][col] = true;
        return isFull(row - 1, col) || isFull(row + 1, col) || isFull(row, col - 1) || isFull(row, col + 1);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {

    }

    // does the system percolate?
    public boolean percolates() {

    }

    // test client (optional)
    public static void main(String[] args) {

    }
}