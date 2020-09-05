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
                + "Enter 'A' to Add, 'S' to Subtract, 'M' to Multiply, or 'Q' to Quit.");
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
            String arg1_input;
            String arg2_input;
            float arg1;
            float arg2;
            while (true) {
                System.out.print("Enter argument 1: ");
                arg1_input = input.nextLine();
                try {
                    arg1 = Float.parseFloat(arg1_input);
                    break;
                }
                catch (Exception e) { /* do nothing */ }
            }
            while (true) {
                System.out.print("Enter argument 2: ");
                arg2_input = input.nextLine();
                try {
                    arg2 = Float.parseFloat(arg2_input);
                    break;
                }
                catch (Exception e) { /* do nothing */ }
            }

            // Perform calculation and print result
            String result_type;
            float ans;
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
