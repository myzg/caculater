package calulatorprogram;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;

public class tool {
	public static void clear(Stack<Character> stack,ArrayList<Character> list) {
		while(!stack.isEmpty()) {
			list.add(stack.pop());
		}
	}
	public static void handle(Stack<Character> stack,ArrayList<Character> list, Character str,String builder,Indexof   index) {
		while(str!='\0') {
			Decision.dodispose(Decision.decisions(str), stack, list, str, builder, index);							//调用工厂和策略方法
			str=builder.charAt(index.getIndex());													
			index.add();
		}
	}
	public static void compute(ArrayList<Character> list,Indexof index, StringBuilder builder , Stack<BigDecimal> stack , BigDecimal  allresult ,Character ch) {
		 while(ch!='\0') {
			 chiose.choseGetresult(chiose.chioseNO(ch), list, index, builder, stack, allresult, ch);				//调用 工厂 策略 选择方法
			 index.add();
			 ch = list.get(index.getIndex());
		 }
	}
	public static boolean expression (String str) {
		int Lcount = 0;
		int Rcount=0;
		char ch;
		for(int i  = 0; i< str.length();i++) {
			ch = str.charAt(i);
			switch(ch) {
			case'(':Lcount++;break;
			case')':Rcount++;break;
			}
		}
		
		if(Lcount==Rcount) {
		return true;
		}else {
		return false;
		}
	}
}
