package calulatorprogram;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class addsymbol extends Symbol {
	public Symbol  getSymbol(char str) {
		if(str == '+') {
		return this;
		}else {
		return null;
		}
	}
	//遇到‘+’其优先级不大于栈顶任何符号，直接加入list，保持了从左到右的顺序;
	public void dispose(Stack<Character> stack,ArrayList<Character> list, Character str,String builder,Indexof  index) {
		while(!stack.empty()&&stack.peek()!='(') {
			list.add(stack.pop());
		}
		stack.push(str);
		}
}
