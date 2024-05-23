package test;

public class Infix2Postfix2 {

    // exp에 저장된 수식을 중위표기법 -> 후위표기법으로 변환하는 메소드
    public static String convert(String exp) {
        if (exp == null || exp.length() == 0) return null; // 입력이 없거나 빈 문자열인 경우 null을 반환
        Stack<String> stack = new Stack<>(); // 연산자를 저장할 스택
        StringBuffer buf = new StringBuffer(); // 후위표기법을 저장할 버퍼

        for (int i = 0; i < exp.length(); i++) { // 수식의 각 문자에 대해 반복
            char c = exp.charAt(i);

            if (Character.isWhitespace(c)) { // 공백 문자는 무시
                continue;
            }

            if (Character.isDigit(c) || c == '.') { // 숫자이거나 소수점일 경우
                StringBuilder num = new StringBuilder();
                num.append(c);
                while (i + 1 < exp.length() && (Character.isDigit(exp.charAt(i + 1)) || 					exp.charAt(i + 1) == '.')) { // 숫자나 소수점이 연속되는 경우
                    num.append(exp.charAt(++i));
                }
                buf.append(num).append(' '); // 숫자를 버퍼에 추가
            } else if (c == '(') { // 여는 괄호는 스택에 추가
                stack.push(Character.toString(c));
            } else if (c == ')') { // 닫는 괄호일 경우
                while (!stack.isEmpty() && !stack.peek().equals("(")) { 
		// 여는 괄호를 만날 때까지 스택에서 연산자를 버퍼에 추가
                    buf.append(stack.pop()).append(' ');
                }
                stack.pop(); // 짝에 맞는 닫는 괄호가 들어오면 여는 괄호 제거
            } else if (isOperator(c)) { // 연산자인 경우
                if (c == '-' && (i == 0 || exp.charAt(i - 1) == '(' || isOperator(exp.charAt(i - 1)))) { 
		// 단항 마이너스(-) 처리
                    StringBuilder num = new StringBuilder();
                    num.append(c);
                    while (i + 1 < exp.length() && (Character.isDigit(exp.charAt(i + 1)) 
				|| exp.charAt(i + 1) == '.')) { // 숫자나 소수점이 연속되는 경우
                        num.append(exp.charAt(++i));
                    }
                    buf.append(num).append(' '); // 단항 마이너스를 포함한 숫자를 버퍼에 추가
                } else {
                    while (!stack.isEmpty() && getPriority(stack.peek().charAt(0)) >= getPriority(c)) { 
		    // 스택에 있는 연산자의 우선순위가 현재 연산자보다 높거나 같은 경우
                        buf.append(stack.pop()).append(' '); // 스택에서 버퍼로 연산자를 추가
                    }
                    stack.push(Character.toString(c)); // 현재 연산자를 스택에 추가
                }
            }
        }

        while (!stack.isEmpty()) { // 스택에 남아있는 모든 연산자를 버퍼에 추가
            buf.append(stack.pop()).append(' ');
        }

        return buf.toString().trim(); // 변환된 후위표기법을 반환
    }

    // 연산자의 타입을 반환하는 메소드
    public static int opType(String op) {
        op = op.trim();
        if (op.length() > 1 || op.length() == 0) {
            return -1; // 유효하지 않은 연산자일 경우 -1을 반환
        }
        char c = op.charAt(0);
        switch (c) {
            case '+':
                return 1; // 덧셈
            case '-':
                return 2; // 뺄셈
            case '*':
                return 3; // 곱셈
            case '/':
                return 4; // 나눗셈
            case '(':
                return 5; // 여는 괄호
            case ')':
                return 6; // 닫는 괄호
        }
        return -1; // 유효하지 않은 연산자
    }

    // 연산자의 우선순위를 반환하는 메소드
    private static int getPriority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1; // 덧셈과 뺄셈의 우선순위
            case '*':
            case '/':
                return 2; // 곱셈과 나눗셈의 우선순위
            case '(':
                return 0; // 여는 괄호의 우선순위 (가장 낮음)
            default:
                return -1; // 유효하지 않은 연산자
        }
    }

    // 주어진 문자가 연산자인지 확인하는 메소드
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/'; // 연산자 확인
    }
}
