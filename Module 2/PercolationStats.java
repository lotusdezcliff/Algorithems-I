import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private final double[] results;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniformInt(n);
                int col = StdRandom.uniformInt(n);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }
            }
            results[i] = (double) p.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double sum = 0;
        for (int i = 0; i < results.length; i++) {
            sum += results[i];
        }
        return sum / results.length;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double diffSum = 0;
        for (int i = 0; i < results.length; i++) {
            diffSum += (results[i] - mean()) * (results[i] - mean());
        }
        return Math.sqrt(diffSum / (results.length - 1));
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(results.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(results.length));
    }

   // test client (see below)
   public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    PercolationStats stats = new PercolationStats(n, trials);
    System.out.printf("mean                    = %f\n", stats.mean());
    System.out.printf("stddev                  = %f\n", stats.stddev());
    System.out.printf("95%% confidence interval = [%f, %f]\n",
                      stats.confidenceLo(), stats.confidenceHi());
   }

}