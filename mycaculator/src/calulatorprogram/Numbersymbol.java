package calulatorprogram;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Numbersymbol extends Symbol {
			public Symbol  getSymbol(char str) {
		if(str>='0' && str<='9') {
			return this;
			}else {
				return null;
			}
		}
		//�����׺���ʽ�е����֣�
		public void dispose(Stack<Character> stack,ArrayList<Character> list, Character str,String builder,Indexof  index) {
			while(str>='0'&& str<='9'||str=='.' ) {
				
				list.add(str);
				str  = builder.charAt(index.getIndex());
				index.add();
			}
			index.sub();
			list.add('#');
		}
}