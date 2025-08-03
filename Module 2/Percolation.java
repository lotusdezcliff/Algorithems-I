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
        
        // edge case (exist path)
        if (row == 0) {
            return true;
        }

        // edge case (not a open site)
        if (isOpen(row, col) == false) {
            return false;
        }
        
        // Recursion
        visited[row][col] = true;
        return isFull(row - 1, col) || isFull(row + 1, col) || isFull(row, col - 1) || isFull(row, col + 1);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int counter = 0;
        for (boolean[] row : grid) {
            for (boolean site: row) {
                if (site) {
                    counter++;
                }
            }
        }
        return counter;
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
        int N = 5;
        Percolation perc = new Percolation(N);

        perc.open(0, 2);
        perc.open(1, 2);
        perc.open(2, 2);
        perc.open(3, 2);
        perc.open(4, 2);  // Now full path from top to bottom

        System.out.println("Percolates? " + perc.percolates()); // Should print true
    }

}