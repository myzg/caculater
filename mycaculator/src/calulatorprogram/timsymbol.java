package calulatorprogram;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class timsymbol extends Symbol {
	public Symbol  getSymbol(char str) {
		if(str=='-') {
			return this;
		}else {
			return null;
		}
	}
	public void dispose(Stack<Character> stack,ArrayList<Character> list, Character str,String builder,Indexof  index) {
		if(isminus(builder,index,str)) {
			/*
			 * ��������� ��� �������⣻
			 * */
			list.add('%');
		}else {
		while(!stack.empty()&&stack.peek()!='(') {
			list.add(stack.pop());
		}
		stack.push(str);
		}
		}
	private boolean isminus(String builder,Indexof  index,Character str) {
		char leftchar = builder.charAt(index.lnumber());
		System.out.println("�����ַ�����һ��"+leftchar);
		
		char rightchar = builder.charAt(index.rnumber());
		System.out.println("�����ַ��ĺ�һ��"+rightchar);
		if((index.index==2)||(leftchar == '*')||(leftchar == '/' )||(leftchar =='(' )||(leftchar =='+')||(leftchar =='-')&&(rightchar!='('))
		{
			return true;
		}else{
		return false;
		}
	}
}

