package calulatorprogram;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class pointsymbol extends Symbol {
	public Symbol  getSymbol(char str) {
		if(str=='.') {
			return this;
		}else {
			return null;
		}
	}
	public void dispose(Stack<Character> stack,ArrayList<Character> list, Character str,String builder,Indexof  index) {
		list.add(str);
	}
}
