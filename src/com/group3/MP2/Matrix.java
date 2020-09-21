package com.group3.MP2;

import java.util.Random;
import java.lang.Math;

public class Matrix {

    // Maximum double chosen when generating random matrices
    private static final int MAX_RAND = 10;
    // Number of decimal places to round doubles when generating random matrices
    private static final int DECIMAL_LENGTH = 1;

    // Numbers matrix rows and columns
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
        Random rand = new Random();

        for (int row = 0; row < nrows; row++) {
            for (int column = 0; column < ncolumns; column++) {
                // Choose a pseudorandom double my_rand, such that: 0.00 <= my_rand < (1.00 * MAX_RAND)
                double my_rand = rand.nextDouble() * MAX_RAND;
                // Round my_rand to only 2 decimal places
                my_rand = Math.floor(my_rand * Math.pow(10, DECIMAL_LENGTH)) / Math.pow(10, DECIMAL_LENGTH);
                // 50% my_rand is negative
                my_rand *= Math.pow(-1, rand.nextInt(2));
                rows[row][column] = my_rand;
            }
        }
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
        for (int row = 0; row < num_rows; row++) {
            System.out.print("|\t ");
            for (int column = 0; column < num_columns; column++) {
                System.out.print(rows[row][column] + "\t ");
            }
            System.out.println('|');
        }
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
