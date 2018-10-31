package caculatorupdata;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Pattern;

public class Tool {
	//�жϳ����Ƿ�Ϊ0��
	public static boolean configuration(String str) {
		if(Pattern.compile("^-?0+\\.?0*$").matcher(str).find()) {//����Ϊ0��
			return true;
		}else {
			return false;
		}
	}
	//�ж���������������λ�ã�
	public static boolean texterr(String str) {
		int lbcount =0;						//����������
		int rbcount = 0;					//����������
		int lbindex =0;						//�������±�
		int rbindex =0;					//�������±�
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
		if(lbcount == rbcount && lbindex <= rbindex && !str.equals("��")) {
			return true;
		}else {
			return false;
		}
	}
	//�ж����ֺ��������������
	public static boolean listeer(ArrayList<Character> list) {
		int operratorcount =0;
		int numbercount = 0;
		int pointcount= 0;
		boolean twopoint = false;
		char ar;
		for(char ch : list) {
			switch(ch) {
			case'+':
				operratorcount++;break;			//�������Ŀ��һ
			case'-':
				operratorcount++;break;			
			case'*':	
				operratorcount++;break;			
			case'/':
				operratorcount++;break;	
			case'#':
				numbercount++;break;				//���ּ�һ��
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
	//��������ʽ�ж��Ǹ�������������
	public static boolean isdouble(String str) {
		if(Pattern.compile("^-?\\d+\\.\\d+$").matcher(str).find()) {
			return true;
		}else {
			return false;
		}
	}
}
