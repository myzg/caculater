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
	/*错误类型：1，符号大于等于数字。2数字中出现两个小数点。3括号数量不相等。4除数为0。 5 运算符为0。6 数字小于 1；
	*
	*1：符号数量=数字数量-1； 
	*2：循环排除
	*3；循环计数比较左括号于右括号
	*4：移动在Numbers类中 处理；
	*5：计数运算符；
	*6
	*/
	public static boolean expression (ArrayList<Character> list) {
		int Lbrackcount=0;																					//左括号计数
		int Rbrackcount=0;																					//右括号计数
		int numbercount = 0;																				//数字计数
		int operratorcount=0;																			//运算符计数
		int  pointercount =0 ;																				//小数点计数
		
		for(char ch : list){
			switch(ch) {
			case'(':Lbrackcount++;break;
			case')':Rbrackcount++;break;
			case'#':numbercount++;break;
			case'+':operratorcount++;break;
			case'-':operratorcount++;break;
			case'*':operratorcount++;break;
			case'/':operratorcount++;break;
			}
		}
		
		char ch='@';
		
		for(int i =0;i<list.size();i++) {																//计算每一个数字中的小数点的个数；
			ch = list.get(i);
			pointercount=0;
			while(ch!='#'&& i<list.size()-1) {
				if(ch=='.')
				pointercount++;
				ch = list.get(++i);
			}
			if(pointercount>=2) {
				return false;
			}
		}
		
		if(Lbrackcount==Rbrackcount && numbercount>=1 && operratorcount<numbercount) {
		if(numbercount>1 && numbercount==operratorcount+1) {
				return true;
			}else {
			return true;
			}
		}else {
		return false;	
		
		}
	}
	public static boolean textexpression(String str) {
		char ch ='@';
		int Lbrackcount=0;																					//左括号计数
		int Rbrackcount=0;																					//右括号计数
		int Lindex=0;
		int Rindex=0;
		for(int i = 0; i< str.length();i++) {
			ch = str.charAt(i);
			switch(ch) {
			case'(':Lbrackcount++;Lindex=i; break;
			case')':Rbrackcount++; Rindex =i;break;
			}
			}
		if(Rindex<Lindex) {
			return false;
			}
		if(Lbrackcount==Rbrackcount) {
		return true;
		}else {
		return false;
		}
	}
	
}
