public class Percolation {
    private final boolean[][] grid;
    private boolean[][] visited;
    private int openSitesCount;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
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
    
    // Index checker
    private void validateIndices(int row, int col) {
    if (row < 1 || row > grid.length || col < 1 || col > grid.length) {
        throw new IllegalArgumentException("Invalid Index");
    }
}
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateIndices(row, col);
        grid[row - 1][col - 1] = true;
        openSitesCount++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        // check the edge
        if (row < 0 || row >= grid.length || col < 0 || col >= grid.length) {
            return false;
        }
        // check the visited site
        if (visited[row][col]) {
            return false;
        }

        // edge case (exist path)
        if (row == 0) {
            return true;
        }

        // edge case (not a open site)
        if (!isOpen(row, col)) {
            return false;
        }
        
        // Recursion
        visited[row][col] = true;
        return isFull(row - 1, col) || isFull(row + 1, col) || isFull(row, col - 1) || isFull(row, col + 1);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < grid[0].length; i++) {
            if (isFull(grid.length - 1, i)) {
                return true;
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 5;
        Percolation perc = new Percolation(n);

        perc.open(0, 2);
        perc.open(1, 2);
        perc.open(2, 2);
        perc.open(3, 2);
        perc.open(4, 3);  // Now full path from top to bottom

        System.out.println("Percolates? " + perc.percolates()); // Should print true
    }

}