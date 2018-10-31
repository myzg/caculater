package caculatorupdata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Detail{
	public static ArrayList<Character> trans (String str){
		StringBuilder builder = new StringBuilder(str);
		ArrayList<Character> list = new ArrayList<>();
		Stack<Character> stack = new Stack();
		builder.append('\0');
		char ch;
		int i=0;
		ch = builder.charAt(i);
		i++;
		while(ch != '\0') {
			switch(ch) {
			case'(':
				stack.push(ch);
				break;
			case')':
				while(stack.peek()!='(') {
					list.add(stack.pop());
				}
				stack.pop();
				break;
			case'+':
				while(!stack.empty()&&stack.peek()!='(') {
					list.add(stack.pop());
				}
				stack.push(ch);
				break;
			case'-':
					while(!stack.empty() && stack.peek()!='(') {
						list.add(stack.pop());
					}
					stack.push(ch);
				break;
			case'*':
			case'/':
				while(!stack.empty() && stack.peek()!='(' &&(stack.peek()=='*' || stack.peek()=='/')) {
					list.add(stack.pop());
				}
				stack.push(ch);
				break;
			case' ':
				break;
			default:
					while(ch>='0' && ch<='9' || ch=='.') {
						list.add(ch);
						ch = builder.charAt(i);
						i++;
					}
					i--;
					list.add('#');
			}
			ch = builder.charAt(i);
			i++;
		}
	while(!stack.empty()) {
		list.add(stack.pop());
	}
		list.add('\0');
		return list;
	}
	public static BigDecimal cucalate(ArrayList<Character> list) {
		Stack<BigDecimal> stack = new Stack();
		StringBuilder builder = new StringBuilder();
		char ch;
		int i= 0;
		ch =list.get(i);
		i++;
		BigDecimal mathnumber2;
		BigDecimal mathnumber1;
		while(ch !='\0') {
			switch(ch) {
			case'+':
				mathnumber2 = new BigDecimal(stack.pop().toString());//由于出栈顺序 是与正常相反，固number2先出栈。（这里存在除数的位置问题和被减数的位置问题）
				mathnumber1 = new BigDecimal(stack.pop().toString());
				stack.push(mathnumber1.add(mathnumber2));
				break;
			case'-':
				 mathnumber2 = new BigDecimal(stack.pop().toString());//由于出栈顺序 是与正常相反，固number2先出栈。（这里存在除数的位置问题和被减数的位置问题）
				 mathnumber1 = new BigDecimal(stack.pop().toString());
				stack.push(mathnumber1.subtract(mathnumber2));
				break;
			case'*':
				mathnumber2 = new BigDecimal(stack.pop().toString());//由于出栈顺序 是与正常相反，固number2先出栈。（这里存在除数的位置问题和被减数的位置问题）
				mathnumber1 = new BigDecimal(stack.pop().toString());
				stack.push(mathnumber1.multiply(mathnumber2));
				break;
			case'/':
				if(Tool.configuration(stack.peek().toString())) {				//最终判断根据栈大小确定除数为零；
					
				}else {
				mathnumber2 = new BigDecimal(stack.pop().toString());
				mathnumber1 = new BigDecimal(stack.pop().toString());
				stack.push(mathnumber1.divide(mathnumber2,8,BigDecimal.ROUND_HALF_DOWN));	
				}
				break;
			default:
				while(ch!='#') {
					builder.append(ch);
					ch = list.get(i);
					i++;
				}
				stack.push(new BigDecimal(builder.toString()));
				builder.delete(0, builder.length());
			}
			ch = list.get(i);
			i++;
		}
		if(stack.size()==1) {
			return stack.pop();
		}else {
			return null;
		}
	}
	
}




































