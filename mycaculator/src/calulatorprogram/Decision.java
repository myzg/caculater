package calulatorprogram;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public  class Decision {
		public static Symbol decisions(char decisionchar) {
			switch(decisionchar) {
			case'+':return new addsymbol();
			case'-':return new timsymbol();
			case'*':return new subsymbol();
			case'/':return new Divsymbol();
//			case'.':return new pointsymbol();
			case'(':return new Lbracket();
			case')':return new Rbracket();
			case' ':return new minussymbol();
			default:
				return new Numbersymbol();
			}
		}
		public static void dodispose(Symbol symbol,Stack<Character> stack,ArrayList<Character> list, Character str,String builder,Indexof   index) {
			symbol.dispose(stack, list, str, builder, index);
			
		}
}
