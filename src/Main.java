import static util.CalculatorUtils.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] expression = new Scanner(System.in).nextLine().split(" ");
        if (!isValidExpression(expression)) {
            throw new RuntimeException("Not valid expression");
        }

        System.out.println(doCalculation(expression[0], expression[2], expression[1]));

    }
}