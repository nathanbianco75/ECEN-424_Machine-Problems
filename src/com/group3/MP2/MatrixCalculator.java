package com.group3.MP2;

import java.util.ArrayList;

public class MatrixCalculator {

    protected Matrix A;
    protected Matrix B;
    protected Matrix C;
    private ArrayList<Thread> threads;

    public Matrix threadedMultiply(Matrix a, Matrix b) {
        A = a;
        B = b;
        C = Matrix.generateZeros(A.get_num_rows(), B.get_num_columns());

        // Create 5 threads with appropriate indices passed to runnables

        // Start all threads

        // Join all threads

        return C;
    }


    class MatrixThread implements Runnable {

        private int index;
        private int length;

        MatrixThread(int i, int l) {
            index = i;
            length = l;
        }

        public void run() {
            double[][] rows;

            /*
            for r = index to index+length
			    add A.getRow(r) to rows

			Matrix A_part = new Matrix(A.get_num_rows(), A.get_num_columnsrows)
			Matrix C_part = Matrix.multiply(A_part, B)

			for r = index to index+length
				C.getRow(r) = C_part.get(r-index)

            return
             */
        }

    }

}
