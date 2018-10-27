package calulatorprogram;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Pattern;

public class Operrator extends Get {
public void getresult(ArrayList<Character> list,Indexof index, StringBuilder builder , Stack<BigDecimal> stack , BigDecimal allresult ,Character ch) {

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
		}else if(ch=='/') {																							//由于出栈顺序 是与正常相反，固number2先出栈。（这里存在除数的位置问题和被减数的位置问题）
			
			if(!Pattern.compile(StrConfiguration.REGULAR_ZERO_DOUBLE).matcher(stack.peek().toString()).find()) {
			BigDecimal mathnumber2 = new BigDecimal(stack.pop().toString());
			BigDecimal mathnumber1 = new BigDecimal(stack.pop().toString());
			stack.push(mathnumber1.divide(mathnumber2,8,BigDecimal.ROUND_HALF_DOWN));
			}else {
																																	//此处如果为零不作处理，最终栈的大小不为1，computation 方法返回null；即为错误表达式
			}																																	
			
		}
	
	}
}
