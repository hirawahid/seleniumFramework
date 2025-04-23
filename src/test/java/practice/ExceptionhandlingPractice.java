package practice;

import org.bouncycastle.oer.Switch;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionhandlingPractice {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("Enter first number: ");
                int a = scanner.nextInt();

                System.out.println("Enter second number: ");
                int b = scanner.nextInt();
                flag = false;

                System.out.println("Enter operation: ");
                String operation = scanner.next();
                try {
                    switch (operation) {
                        case "+":
                            System.out.println(a + b);
                            break;
                        case "-":
                            System.out.println(a - b);
                            break;
                        case "/":
                            try {
                                System.out.println(a / b);
                            } catch (ArithmeticException ae) {
                                System.out.println("division with zero not allowed, retry");
                            }
                            break;
                        case "*":
                            System.out.println(a * b);
                            break;
                        default:
                            throw new InvalidOperationException("Incorrect operation: try again");

                    }
                } catch (InvalidOperationException e) {
                    flag = true;
                    System.out.println(e.getMessage());
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("invalid inputs, please try again");
            }
            catch (Exception a){
                System.out.println("unexpected error occurred: please try again.");
            }
            finally {
                System.out.println("calculation complete");
            }
        }

    }
}
