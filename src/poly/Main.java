package poly;

import java.util.Scanner;

import static poly.Polytest.add;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int option;

        do {
            option = printMenu();

            switch (option) {
                case 1:
                    add();
                    break;

                case 2:
                    multiply();
                    break;

                case 3:
                    evaluate();
                    break;
            }

        } while (option != 4);
    }

    public static void add() {
        System.out.println("add");
    }

    public static void multiply() {
        System.out.println("multiply");
    }

    public static void evaluate() {
        System.out.println("evaluate");
    }

    public static int printMenu() {

        int choice;

        System.out.println("\nFUNCTIONS TO PREFORM ON POLYNOMIALS\n");
        System.out.print("1. ADD polynomial\n"
                            + "2. MULTIPLY polynomial\n"
                            + "3. EVALUATE polynomial\n"
                            + "4. QUIT\n\n");
        System.out.println(". . . please enter the corresponding number . . .\n");

        choice = scanner.nextInt();



        return choice;
    }

}
