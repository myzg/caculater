package calulatorprogram;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Stack;

public class Operrator extends Get {
public void getresult(ArrayList<Character> list,Indexof index, StringBuilder builder , Stack<BigDecimal> stack , BigDecimal allresult ,Character ch) {
	double number1 =0;
	double number2 =0;
	if(ch=='+') {
		BigDecimal mathnumber2 = new BigDecimal(stack.pop().toString());//由于出栈顺序 是与正常相反，固number2先出栈。（这里存在除数的位置问题和被减数的位置问题）
		BigDecimal mathnumber1 = new BigDecimal(stack.pop().toString());
		stack.push(mathnumber1.add(mathnumber2));
		}else if (ch=='-') {
			BigDecimal mathnumber2 = new BigDecimal(stack.pop().toString());//由于出栈顺序 是与正常相反，固number2先出栈。（这里存在除数的位置问题和被减数的位置问题）
			BigDecimal mathnumber1 = new BigDecimal(stack.pop().toString());
			stack.push(mathnumber1.subtract(mathnumber2));
		}else if(ch=='*') {
			BigDecimal mathnumber2 = new BigDecimal(stack.pop().toString());//由于出栈顺序 是与正常相反，固number2先出栈。（这里存在除数的位置问题和被减数的位置问题）
			BigDecimal mathnumber1 = new BigDecimal(stack.pop().toString());
			stack.push(mathnumber1.multiply(mathnumber2));
		}else if(ch=='/') {
			BigDecimal mathnumber2 = new BigDecimal(stack.pop().toString());//由于出栈顺序 是与正常相反，固number2先出栈。（这里存在除数的位置问题和被减数的位置问题）
			BigDecimal mathnumber1 = new BigDecimal(stack.pop().toString());
			mathnumber1.setScale(8, RoundingMode.HALF_UP);
			stack.push(mathnumber1.add(mathnumber2));
		}
	
	}
}
//除法还未做处理；