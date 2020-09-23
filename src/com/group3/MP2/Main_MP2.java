package com.group3.MP2;

public class Main_MP2 {

    public static void main(String[] args) {

        // Generate and Display Random Matrices A and B
        Matrix A = Matrix.generateRandom(20, 20);
        Matrix B = Matrix.generateRandom(20, 20);
        System.out.println("\nRandom Matrix A:");
        A.print(1);
        System.out.println("\nRandom Matrix B:");
        B.print(1);

        //To compare time b/w normal and threads
        //long startTime = System.nanoTime();
        // Calculate C = A*B the normal way, and Print C
        Matrix C = Matrix.multiply(A,B);
        System.out.println("\nNormal Matrix C = A*B:");
        C.print(1);
        //long endTime = System.nanoTime();
        //System.out.println("Normal Took "+(endTime - startTime) + " ns");

        //To compare time b/w normal and threads
        //startTime = System.nanoTime();
        // Calculate C = A*B using multiple threads and Print C
        try {
            C = new MatrixCalculator().threadedMultiply(A, B);
            System.out.println("\nMulti-threaded Matrix C = A*B:");
            C.print(1);
        }
        catch(InterruptedException e){
            System.err.println("Caught InterruptedException: " + e.getMessage());
            System.out.println("\n\nMatrix Multi-Threaded Calculation canceled early. Quitting...");
            System.exit(-1);
        }
        //endTime = System.nanoTime();
        //System.out.println("Threads Took "+(endTime - startTime) + " ns");
    }
}
