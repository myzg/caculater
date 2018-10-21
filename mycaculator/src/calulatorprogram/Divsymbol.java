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
	//遇到‘/’和'*'处理方式一样，优先级大于
	public void dispose(Stack<Character> stack,ArrayList<Character> list, Character str,String builder,Indexof  index) {
		while(!stack.empty()  &&  stack.peek()!='('  && (stack.peek()=='*' || stack.peek()=='/') ){
			list.add(stack.pop());
		}
		stack.push(str);
	}
}
