package com.group3.MP1;

import java.util.Scanner;

public class Main_MP1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Calculator mycalc = new Calculator();
        mycalc.setname("Group 3");

        while (true) {
            // Welcome user to Calculator and take user input
            System.out.println("\nWelcome to the Calculator designed by " + mycalc.getname() + ".\n"
                + "Enter 'A' to Add, 'S' to Subtract, 'M' to Multiply, or 'Q' to Quit.\n");
            String user_choice = input.nextLine();

            // if user chooses to quit
            if (user_choice.contains("Q")) {
                // User chose to quit
                break;
            }
            // else if user's input is invalid
            else if (!(user_choice.contains("A")
                    || user_choice.contains("S")
                    || user_choice.contains("M"))) {
                continue;
            }

            // Take argument inputs, making the user retry for invalid inputs
            String arg1_input = "";
            String arg2_input = "";
            Float arg1 = 0f;
            Float arg2 = 0f;
            while (true) {
                System.out.println("Enter argument 1: ");
                arg1_input = input.nextLine();
                try {
                    arg1 = Float.parseFloat(arg1_input + 'f');
                }
                catch (Exception e) {
                    continue;
                }
            }
            while (true) {
                System.out.println("Enter argument 2: ");
                arg2_input = input.nextLine();
                try {
                    arg2 = Float.parseFloat(arg2_input + 'f');
                }
                catch (Exception e) {
                    continue;
                }
            }

            // Perform calculation and print result
            String result_type = "";
            Float ans = 0f;
            if (user_choice.contains("A")) {
                result_type = "sum";
                ans = mycalc.addition(arg1, arg2);
            }
            else if (user_choice.contains("S")) {
                result_type = "difference";
                ans = mycalc.subtraction(arg1, arg2);
            }
            else {
                result_type = "product";
                ans = mycalc.multiplication(arg1, arg2);
            }
            System.out.println("\nThe " + result_type + " of " + arg1 + " and " + arg2 + " is " + ans);
        }
    }

}
