package assignment1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF uf;
    private final int size;
    private boolean[] sites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        validate(n);
        this.size = n;
        this.sites = new boolean[n*n + 2];  // top and bottom
        sites[0] = true;
        sites[n*n+1] = true;
        this.uf = new WeightedQuickUnionUF(n*n + 2);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int index = xoy(row, col);
        sites[index] = true;

        if (row > 1 && sites[index - size]) {
            uf.union(index, index - size);
        }

        if (col > 1 && sites[index - 1]) {
            uf.union(index, index - 1);
        }

        if (col < size && sites[index + 1]) {
            uf.union(index, index + 1);
        }

        if (row < size && sites[index + size]) {
            uf.union(index, index + size);
        }

        if (row == 1) {
            uf.union(index, 0);
        }
        if (row == size) {
            uf.union(index, size*size + 1);
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return isFull(row, col) || sites[xoy(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf.connected(0, xoy(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int numberOfOpenSites = 0;
        for (int i = 0; i < size*size; i++) {
            if (sites[i + 1]) {
                numberOfOpenSites++;
            }
        }
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, size*size+1);
    }

    private void validate(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
    }
    private void validate(int row, int col) {
        if (row < 0 || row > size || col < 0 || col > size) {
            throw new IllegalArgumentException();
        }
    }

    private int xoy(int row, int col) {
        validate(row, col);
        return (row - 1) * size + col;
    }

    // test client (optional)
    public static void main(String[] args) {
        // UncommentedEmptyMethodBody
    }
}