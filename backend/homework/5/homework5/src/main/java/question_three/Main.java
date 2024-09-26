package question_three;

import org.student.Logging;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Logging.logInfo("Enter a number : ");
        int num = sc.nextInt();
        Factorial factorialThread = new Factorial(num);
        Factor factorThread = new Factor(num);

        factorThread.start();
        factorialThread.start();

        factorialThread.join();
        factorThread.join();

        Logging.logInfo("Main function exists now!");
    }
}
