package test;
import java.util.*;

public class Infix2Postfix {
    public static String convert(String exp) {
        if (exp == null || exp.length() == 0) return null;
        Stack<String> stack = new Stack<>();
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (Character.isWhitespace(c)) {
                continue;
            }

            if (Character.isDigit(c) || c == '.') {  
                StringBuilder num = new StringBuilder();
                num.append(c);
                while (i + 1 < exp.length() && (Character.isDigit(exp.charAt(i + 1)) || exp.charAt(i + 1) == '.')) {
                    num.append(exp.charAt(++i));
                }
                buf.append(num).append(' ');
            } else if (c == '(') {
                stack.push(Character.toString(c));
            } else if (c == ')') {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    buf.append(stack.pop()).append(' ');
                }
                stack.pop();
            } else if (isOperator(c)) {
                if (c == '-' && (i == 0 || exp.charAt(i - 1) == '(' || isOperator(exp.charAt(i - 1)))) {  
                    StringBuilder num = new StringBuilder();
                    num.append(c);
                    while (i + 1 < exp.length() && (Character.isDigit(exp.charAt(i + 1)) || exp.charAt(i + 1) == '.')) {
                        num.append(exp.charAt(++i));
                    }
                    buf.append(num).append(' ');
                } else {
                    while (!stack.isEmpty() && getPriority(stack.peek().charAt(0)) >= getPriority(c)) {
                        buf.append(stack.pop()).append(' ');
                    }
                    stack.push(Character.toString(c));
                }
            }
        }

        while (!stack.isEmpty()) {
            buf.append(stack.pop()).append(' ');
        }

        return buf.toString().trim();
    }

    public static int opType(String op) {
        op = op.trim();
        if (op.length() > 1 || op.length() == 0) {
            return -1;
        }
        char c = op.charAt(0);
        switch (c) {
            case '+':
                return 1;
            case '-':
                return 2;
            case '*':
                return 3;
            case '/':
                return 4;
            case '(':
                return 5;
            case ')':
                return 6;
        }
        return -1;
    }

    private static int getPriority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '(':
                return 0; 
            default:
                return -1;
        }
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
