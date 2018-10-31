package caculatorupdata;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Pattern;

public class Tool {
	//判断除数是否为0；
	public static boolean configuration(String str) {
		if(Pattern.compile("^-?0+\\.?0*$").matcher(str).find()) {//除数为0；
			return true;
		}else {
			return false;
		}
	}
	//判断括号数量和括号位置；
	public static boolean texterr(String str) {
		int lbcount =0;						//左括号数量
		int rbcount = 0;					//右括号数量
		int lbindex =0;						//左括号下标
		int rbindex =0;					//右括号下标
		char ch ;
		for(int i = 0;i<str.length();i++) {
			ch = str.charAt(i);
			switch(ch) {
			case'(':
				lbcount++;
				lbindex = i;
				break;
			case')':
				rbcount++;
				rbindex = i;
				break;
			}
		}
		if(lbcount == rbcount && lbindex <= rbindex && !str.equals("∞")) {
			return true;
		}else {
			return false;
		}
	}
	//判断数字和运算符的数量；
	public static boolean listeer(ArrayList<Character> list) {
		int operratorcount =0;
		int numbercount = 0;
		int pointcount= 0;
		boolean twopoint = false;
		char ar;
		for(char ch : list) {
			switch(ch) {
			case'+':
				operratorcount++;break;			//运算符数目加一
			case'-':
				operratorcount++;break;			
			case'*':	
				operratorcount++;break;			
			case'/':
				operratorcount++;break;	
			case'#':
				numbercount++;break;				//数字加一；
			}
		}
		for(int i = 0;i<list.size();i++) {
			ar= list.get(i);
			pointcount =0;
			while(ar!= '#' && i<list.size()-1 ) {
				if(ar=='.') { 
					pointcount++;
					}
				ar = list.get(++i);
			}
			if(pointcount >=2) {
				twopoint =true;
			}
		}
		if(numbercount>=1 && operratorcount<numbercount && twopoint==false) {
			return true;
		}else {
			return false;
		}
	}
	//用正则表达式判断是浮点数还是整数
	public static boolean isdouble(String str) {
		if(Pattern.compile("^-?\\d+\\.\\d+$").matcher(str).find()) {
			return true;
		}else {
			return false;
		}
	}
}
