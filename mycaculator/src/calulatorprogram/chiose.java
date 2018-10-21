package calulatorprogram;

import java.util.ArrayList;
import java.util.Stack;

public class chiose {
	public static Get chioseNO(Character ch) {
		Get chose =null;
		switch(ch) {
		case'+':	 chose = new Operrator();break;
		case'-':	chose = new Operrator();break;
		case'*':	chose = new Operrator();break;
		case'/':	chose = new Operrator();break;
		case'#':	chose = new specialoperrator();break;
		case'%':	chose = new Numbers();break;
		default:
						chose = new Numbers();break;
		}
		return chose;
	}
 public static void choseGetresult(Get get,ArrayList<Character> list,Indexof index, StringBuilder builder , Stack<Double> stack , Double allresult ,Character ch) {
	 get.getresult(list, index, builder, stack, allresult, ch);
 }
 		
}
