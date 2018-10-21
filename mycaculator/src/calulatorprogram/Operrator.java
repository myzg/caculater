package calulatorprogram;

import java.util.ArrayList;
import java.util.Stack;

public class Operrator extends Get {
public void getresult(ArrayList<Character> list,Indexof index, StringBuilder builder , Stack<Double> stack , Double allresult ,Character ch) {
	double number1 =0;
	double number2 =0;
	if(ch=='+') {
				number2 = stack.pop();//由于出栈顺序 是与正常相反，固number2先出栈。（这里存在除数的位置问题和被减数的位置问题）
				number1 = stack.pop();
				allresult = (number1 + number2);
				stack.push(allresult);
		}else if (ch=='-') {
				number2 = stack.pop();
				number1 = stack.pop();
				allresult = (number1 - number2);
				stack.push(allresult);
		}else if(ch=='*') {
				number2 = stack.pop();
				number1 = stack.pop();
				allresult = (number1 * number2);
				stack.push(allresult);		
		}else if(ch=='/') {
				number2 = stack.pop();
				number1 = stack.pop();
				allresult = (number1 / number2);
				stack.push(allresult);
		}
	
	}
}
