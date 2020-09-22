package com.group3.MP2;
import java.util.ArrayList;

public class MatrixCalculator {

    protected Matrix A;
    protected Matrix B;
    protected Matrix C;
    private ArrayList<Thread> threads = new ArrayList<>();

    public Matrix threadedMultiply(Matrix a, Matrix b) throws InterruptedException {
        A = a;
        B = b;
        C = Matrix.generateZeros(A.get_num_rows(), B.get_num_columns());

        // Create 5 threads with appropriate indices passed to runnables
        for(int i = 0; i < A.get_num_rows(); i+=A.get_num_rows()/5) {
            Thread thread = new Thread(new MatrixThread(i, A.get_num_rows()/5));
            threads.add(thread);
            System.out.println(i);
        }
        // Start all threads
        for (Thread thread:threads) {
            thread.start();
        }

        // Join all threads
        for (Thread thread:threads) {
            thread.join();
        }

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
            double[][] rows = new double[A.get_num_rows()][B.get_num_columns()];


            for (int r = index; r < index+length; r++) {
                rows[r] = A.getRow(r);
            }

			Matrix A_part = new Matrix(A.get_num_rows(), B.get_num_columns(), rows);
			Matrix C_part = Matrix.multiply(A_part, B);

			for (int r = index; r < index+length; r++) {
                C.setRow(C_part.getRow(r), r);
            }

        }

    }

}
