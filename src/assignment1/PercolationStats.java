package assignment1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double confidenceLo;
    private final double confidenceHi;
    private final double stddev;
    private final double mean;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        double[] fractions = new double[trials];
        for (int numOfTrial = 0; numOfTrial < trials; ++numOfTrial) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                p.open(row, col);
            }
            fractions[numOfTrial] = (double) p.numberOfOpenSites() / (n*n);
        }
        mean = StdStats.mean(fractions);
        stddev = StdStats.stddev(fractions);
        confidenceLo = mean - CONFIDENCE_95 * stddev / Math.sqrt(trials);
        confidenceHi = mean + CONFIDENCE_95 * stddev / Math.sqrt(trials);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);

        System.out.println("mean:\t\t\t\t\t\t"+ps.mean());
        System.out.println("stddev:\t\t\t\t\t\t"+ps.stddev());
        System.out.println("95% confidence interval:\t" + "["+ps.confidenceLo()+","+ps.confidenceHi()+"]");
    }
}
