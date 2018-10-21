package calulatorprogram;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Divsymbol extends Symbol {
	public Symbol  getSymbol(char str) {
		if(str=='/') {
			return this;
		}else {
			return null;
		}
	}
	//������/����'*'����ʽһ�������ȼ�����
	public void dispose(Stack<Character> stack,ArrayList<Character> list, Character str,String builder,Indexof  index) {
		while(!stack.empty()  &&  stack.peek()!='('  && (stack.peek()=='*' || stack.peek()=='/') ){
			list.add(stack.pop());
		}
		stack.push(str);
	}
}
