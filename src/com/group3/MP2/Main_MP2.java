package com.group3.MP2;

public class Main_MP2 {

    public static void main(String[] args) {
        // Generate and Display Random Matrices A and B
        Matrix A = Matrix.generateRandom(20, 20);
        Matrix B = Matrix.generateRandom(20, 20);
        System.out.println("\nRandom Matrix A:");
        A.print();
        System.out.println("\nRandom Matrix B:");
        B.print();

        // Calculate C = A*B using multiple threads and Print C
        try {
            Matrix C = new MatrixCalculator().threadedMultiply(A, B);
            System.out.println("\nMatrix C = A*B:");
            C.print();
        }
        catch(InterruptedException e){
            System.err.println("Caught InterruptedException: " + e.getMessage());
        }
    }
}
