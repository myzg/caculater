package calulatorprogram;

import java.util.ArrayList;
import java.util.Stack;

public class Numbers extends Get {
	public void getresult(ArrayList<Character> list , Indexof index , StringBuilder builder , Stack<Double> stack , Double allresult ,Character ch) {
	 
	while(ch!='#') {
		if(ch>='0'&&ch<='9'||ch=='%'||ch=='.') {
			if(ch=='%') {																						//%是负号的特殊处理；
				builder.append('-');
			}else if(ch=='.') {
				builder.append(ch);
			}else {
				builder.append(ch);
			}
			}
		index.add();
		ch=list.get(index.getIndex());
		}
	index.sub();
	stack.push(new Double(builder.toString()));
	builder.delete(0, builder.length());
	}
}
