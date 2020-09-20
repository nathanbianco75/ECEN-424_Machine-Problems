package com.group3.MP2;

public class Matrix {

    private int num_rows;
    private int num_columns;
    // Actual 2D data structure
    private double[][] rows;

    // Default Matrix constructor - 2D array of zeros
    Matrix(int nrows, int ncolumns) {
        num_rows = nrows;
        num_columns = ncolumns;
        rows = new double[nrows][ncolumns];
    }

    // Matrix constructor taking a 2D array
    Matrix(int nrows, int ncolumns, double[][] r) {
        num_rows = nrows;
        num_columns = ncolumns;
        rows = r;
    }

    // Return the Matrix product of A and B
    public Matrix multiply(Matrix A, Matrix B) {
        return A;
    }

    // Return a new Matrix of random doubles
    public static Matrix generateRandom(int nrows, int ncolumns) {
        double[][] rows = new double[nrows][ncolumns];
        // TODO: replace 0's with random numbers
        return new Matrix(nrows, ncolumns, rows);
    }

    // Return a new Matrix of zeros
    public static Matrix generateZeros(int nrows, int ncolumns) {
        double[][] rows = new double[nrows][ncolumns];
        return new Matrix(nrows, ncolumns, rows);
    }

    // Return a row from the Matrix
    public double[] getRow(int r) {
        // TODO: this could throw an index out of bounds exception!
        return rows[r];
    }

    public void print() {
        // TODO: print this matrix to the console
    }

    // num_rows Getter
    public int get_num_rows() {
        return num_rows;
    }

    // num_columns Getter
    public int get_num_columns() {
        return num_columns;
    }

}
