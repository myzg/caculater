package calulatorprogram;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Pattern;

public class Operrator extends Get {
public void getresult(ArrayList<Character> list,Indexof index, StringBuilder builder , Stack<BigDecimal> stack , BigDecimal allresult ,Character ch) {

	if(ch=='+') {
		BigDecimal mathnumber2 = new BigDecimal(stack.pop().toString());//���ڳ�ջ˳�� ���������෴����number2�ȳ�ջ����������ڳ�����λ������ͱ�������λ�����⣩
		BigDecimal mathnumber1 = new BigDecimal(stack.pop().toString());
		stack.push(mathnumber1.add(mathnumber2));
		}else if (ch=='-') {
			BigDecimal mathnumber2 = new BigDecimal(stack.pop().toString());//���ڳ�ջ˳�� ���������෴����number2�ȳ�ջ����������ڳ�����λ������ͱ�������λ�����⣩
			BigDecimal mathnumber1 = new BigDecimal(stack.pop().toString());
			stack.push(mathnumber1.subtract(mathnumber2));
		}else if(ch=='*') {
			BigDecimal mathnumber2 = new BigDecimal(stack.pop().toString());//���ڳ�ջ˳�� ���������෴����number2�ȳ�ջ����������ڳ�����λ������ͱ�������λ�����⣩
			BigDecimal mathnumber1 = new BigDecimal(stack.pop().toString());
			stack.push(mathnumber1.multiply(mathnumber2));
		}else if(ch=='/') {																							//���ڳ�ջ˳�� ���������෴����number2�ȳ�ջ����������ڳ�����λ������ͱ�������λ�����⣩
			
			if(!Pattern.compile(StrConfiguration.REGULAR_ZERO_DOUBLE).matcher(stack.peek().toString()).find()) {
			BigDecimal mathnumber2 = new BigDecimal(stack.pop().toString());
			BigDecimal mathnumber1 = new BigDecimal(stack.pop().toString());
			stack.push(mathnumber1.divide(mathnumber2,8,BigDecimal.ROUND_HALF_DOWN));
			}else {
																																	//�˴����Ϊ�㲻����������ջ�Ĵ�С��Ϊ1��computation ��������null����Ϊ������ʽ
			}																																	
			
		}
	
	}
}
