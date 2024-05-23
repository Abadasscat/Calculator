package test;

public class Calc2 {
    // 후위표기법 수식을 평가하는 메소드
    public static double eval(String exp) {
        StringTokenizer st = new StringTokenizer(exp); // 후위표기법 수식을 숫자와 연산자를 구분(토큰화)
        Stack<Double> stack = new Stack<>(); // 연산을 위한 스택

        while (st.hasMoreTokens()) { // 토큰이 남아 있는 동안 반복
            String tok = st.nextToken();
            if (Infix2Postfix.opType(tok) > 0) { // 연산자인 경우
                double v1 = stack.pop(); // 스택에서 피연산자 1을 꺼냄
                double v2 = stack.pop(); // 스택에서 피연산자 2를 꺼냄
                double value = 0;
                switch (Infix2Postfix.opType(tok)) { // opType()에서 반환된 숫자에 따라 계산 수행
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
                stack.push(value); // 결과를 스택에 저장
            } else {
                stack.push(Double.parseDouble(tok)); // 숫자인 경우 스택에 저장
            }
        }
        return stack.pop(); // 수식 계산 결과 반환
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        System.out.print("Enter infix expression: "); 
        String infix = sc.nextLine(); // 중위표기법 수식 입력 받음
        String postfix = Infix2Postfix.convert(infix); // 중위표기법 수식을 후위표기법으로 변환
        double value = Calc.eval(postfix); // 후위표기법 수식을 평가하여 결과 계산
        System.out.printf("%s = %.2f\n", infix, value); // 계산 결과 출력
    }
}
