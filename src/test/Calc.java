package test;
import java.util.*;

public class Calc {
    public static double eval(String exp) {
        StringTokenizer st = new StringTokenizer(exp);
        Stack<Double> stack = new Stack<>();

        while (st.hasMoreTokens()) {
            String tok = st.nextToken();
            if (Infix2Postfix.opType(tok) > 0) {
                double v1 = stack.pop();
                double v2 = stack.pop();
                double value = 0;
                switch (Infix2Postfix.opType(tok)) {
                    case 1: // 덧셈
                        value = v2 + v1;
                        break;
                    case 2: // 뺄셈
                        value = v2 - v1;
                        break;
                    case 3: // 곱셈
                        value = v2 * v1;
                        break;
                    case 4: // 나눗셈
                        value = v2 / v1;
                        break;
                }
                stack.push(value);
            } else {
                stack.push(Double.parseDouble(tok));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter infix expression: ");
        String infix = sc.nextLine();
        String postfix = Infix2Postfix.convert(infix);
        double value = Calc.eval(postfix);
        System.out.printf("%s = %.2f\n", infix, value);
    }
}
