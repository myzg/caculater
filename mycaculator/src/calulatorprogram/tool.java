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
		
		if(Lbrackcount==Rbrackcount && numbercount>=1 && operratorcount<numbercount) {
		if(numbercount>1 && numbercount==operratorcount+1) {
			System.out.println("true1");	
			return true;
			}else {
			System.out.println("true2");
			return true;}
		}else {
			System.out.println("false");
			return false;
		}
	}
	public static boolean textexpression(String str) {
		char ch ='@';
		int Lbrackcount=0;																					//�����ż���
		int Rbrackcount=0;																					//�����ż���
		for(int i = 0; i< str.length();i++) {
			ch = str.charAt(i);
			switch(ch) {
			case'(':Lbrackcount++;break;
			case')':Rbrackcount++;break;
			}
		}
		if(Lbrackcount==Rbrackcount) {
			 	System.out.println("true3");
		return true;
		}else {
		return false;}
	}
}
	
