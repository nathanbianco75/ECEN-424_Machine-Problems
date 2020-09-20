package com.group3.MP2;

public class Main_MP2 {

    public static void main(String[] args) {
        // Generate and Display Random Matrices A and B
        Matrix A = Matrix.generateRandom(20, 20);
        Matrix B = Matrix.generateRandom(20, 20);
        A.print();
        B.print();

        // Calculate C = A*B using multiple threads and Print C
        Matrix C = new MatrixCalculator().threadedMultiply(A, B);
        C.print();
    }
}
