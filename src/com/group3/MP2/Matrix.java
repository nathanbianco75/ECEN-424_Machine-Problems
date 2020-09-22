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
    public static Matrix multiply(Matrix A, Matrix B) {
        // Requirement for Matrix multiplication: A(mxn) * B(nxk) = C(mxk)
        // i.e. number of columns in A has to equal number of rows in B
        if (A.get_num_columns() != B.get_num_rows()) {
            return null;
        }

        Matrix C = Matrix.generateZeros(A.get_num_rows(), B.get_num_columns());

        for (int r = 0; r < A.get_num_rows(); r++) {
            for (int c = 0; c < B.get_num_columns(); c++) {
                double sum = 0;
                for (int i = 0; i < A.get_num_columns(); i++)
                {
                    sum += A.getElement(r, i) * B.getElement(i, c);
                }
                C.setElement(r, c, sum);
            }
        }
        return C;
    }

    // Return a new Matrix made up of random doubles
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

    // Return a new Matrix made up of given value
    public static Matrix generateByValue(int nrows, int ncolumns, double value) {
        double[][] rows = new double[nrows][ncolumns];
        for (int r = 0; r < nrows; r++) {
            for (int c = 0; c < ncolumns; c++) {
                rows[r][c] = value;
            }
        }
        return new Matrix(nrows, ncolumns, rows);
    }

    // Return a new Matrix made up of zeros
    public static Matrix generateZeros(int nrows, int ncolumns) {
        double[][] rows = new double[nrows][ncolumns];
        return new Matrix(nrows, ncolumns, rows);
    }

    // Return a single element from the Matrix
    public double getElement(int r, int c) {
        return rows[r][c];
    }

    // Set a single element in the Matrix
    public void setElement(int r, int c, double value) {
        rows[r][c] = value;
    }

    // Return a row from the Matrix
    public double[] getRow(int index) {
        // TODO: this could throw an index out of bounds exception!
        return rows[index];
    }

    public void setRow(double[] r, int index) {
        // TODO: this could throw an index out of bounds exception!
        rows[index] = r;
    }

    public void print(int round) {
        // round = (<= -1: no rounding), (0: truncate decimal), (1 - inf: round this many decimals)
        for (int row = 0; row < num_rows; row++) {
            System.out.print("|\t ");
            for (int column = 0; column < num_columns; column++) {
                if (round <= -1)
                    System.out.print(rows[row][column] + "\t ");
                else if (round == 0)
                    System.out.print(((int) rows[row][column]) + "\t ");
                else
                    System.out.print(Math.floor(rows[row][column] * Math.pow(10, round)) / Math.pow(10, round) + "\t ");
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
