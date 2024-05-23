package test;

public class MyCalc2 {
	JButton btnNewButton_14 = new JButton("=");

	btnNewButton_14.addActionListener(new ActionListener() { // "=" 버튼을 클릭하면 실행
	    public void actionPerformed(ActionEvent e) { 
	        String infix = textField.getText(); // 텍스트 필드에서 중위 표기법 수식을 가져옴
	        try {
	            String postfix = Infix2Postfix.convert(infix); // 중위 표기법 수식을 후위 표기법으로 변환
	            double value = Calc.eval(postfix); // 후위 표기법 수식을 평가하여 결과 계산
	            textField.setText(String.valueOf(value)); // 계산된 결과를 문자열로 변환하여 텍스트 필드에 반환
	        } catch (Exception ex) {
	            textField.setText("Error"); // 예외 발생 시 "Error" 메시지를 텍스트 필드에 반환
	        }
	    }
	});
	panel_2.add(btnNewButton_14);


	JButton btnNewButton_19 = new JButton("<<");

	btnNewButton_19.addActionListener(new ActionListener() { // "<<" 버튼을 클릭하면 실행
	    public void actionPerformed(ActionEvent e) {
	        if (!exp.isEmpty()) { // exp가 비어 있지 않은 경우에만 실행
	            exp = exp.substring(0, exp.length() - 1); // exp에서 마지막 문자를 제거
	            textField.setText(exp); // 수정된 exp를 텍스트 필드에 반환
	        }
	    }
	});
	panel_2.add(btnNewButton_19);

}
