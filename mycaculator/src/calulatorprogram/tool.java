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
			Decision.dodispose(Decision.decisions(str), stack, list, str, builder, index);							//���ù����Ͳ��Է���
			str=builder.charAt(index.getIndex());													
			index.add();
		}
	}
	public static void compute(ArrayList<Character> list,Indexof index, StringBuilder builder , Stack<BigDecimal> stack , BigDecimal  allresult ,Character ch) {
		 while(ch!='\0') {
			 chiose.choseGetresult(chiose.chioseNO(ch), list, index, builder, stack, allresult, ch);				//���� ���� ���� ѡ�񷽷�
			 index.add();
			 ch = list.get(index.getIndex());
		 }
	}
	/*�������ͣ�1�����Ŵ��ڵ������֡�2�����г�������С���㡣3������������ȡ�4����Ϊ0�� 5 �����Ϊ0��6 ����С�� 1��
	*
	*1����������=��������-1�� 
	*2��ѭ���ų�
	*3��ѭ�������Ƚ���������������
	*4���ƶ���Numbers���� ����
	*5�������������
	*6
	*/
	public static boolean expression (ArrayList<Character> list) {
		int Lbrackcount=0;																					//�����ż���
		int Rbrackcount=0;																					//�����ż���
		int numbercount = 0;																				//���ּ���
		int operratorcount=0;																			//���������
		int  pointercount =0 ;																				//С�������
		
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
		
		for(int i =0;i<list.size();i++) {																//����ÿһ�������е�С����ĸ�����
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
		int Lbrackcount=0;																					//�����ż���
		int Rbrackcount=0;																					//�����ż���
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
