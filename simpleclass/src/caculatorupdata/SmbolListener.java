package caculatorupdata;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class SmbolListener implements ActionListener{
	private String result;																	//�ϴ����ַ�����
	private String text;																	//�õ����ж��ϴ��ַ������쳣����
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}	
	
	public void setResult(String str) {
		this.result= str;
	}
	
	public String getResult() {
		return result;
	}
	
	
	
	@Override
	
	public void actionPerformed(ActionEvent e) {
		ActionListener listener=null; 
		String command = e.getActionCommand();
		if(command.equals("+")|| command.equals("-")|| command.equals("*")||command.equals("/") ||command.equals("(") ||command.equals(")")
				||command.equals("=")) {
			listener = new operratorActionListener();
		}else if(command.equals("<")||command.equals("CE")) {
			listener = new specialoperratorActionListener();
		}else {
			listener = new numbersActionListener();
		}
		listener.actionPerformed(e);
	}
	

	
	private class numbersActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String command =e.getActionCommand();
			StringBuilder builder = new StringBuilder(getText());
			if((builder.toString().length()==1 && builder.toString().equals(" "))|| (builder.toString().length()==1 && builder.toString().equals("0"))
					||(builder.toString().length()==1 && builder.toString().equals("��")) ) {
				builder.deleteCharAt(0);																								//�����ϴν��Ϊ��ʼ�����ɾ����ӿո�
				builder.append(" ");
			} 
			if(command.equals(".")) {
				if(builder.length()>=1) {
				char ch =builder.charAt(builder.length()-1);
				if(!(ch<='9' && ch>='0') || (builder.length()==1 && !(builder.charAt(0)>='0'&& builder.charAt(0) <='9'))) {
					builder.append("0");
					}
				}
			}
			builder.append(command);
			setResult(builder.toString());
		}
		
	}
	private class specialoperratorActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String command =e.getActionCommand();
			StringBuilder builder =new StringBuilder(getText());
			if(command.equals("<")) {
				if(builder.toString().length()<=1) {																						//�����ʾ���е��ַ�������Ϊ1����˵���û�û�������κ���Ϣ������Ĭ���ַ�������ʼ��Ϊ��0����ɾ���͹����������Ϊ�����ո� �� ��
					builder.delete(0, builder.length());
					builder.append("0");
				}else {
					builder.deleteCharAt(builder.length()-1);
				}		
			}else {
				builder.delete(0, builder.length());
				builder.append("0");
			}
			setResult(builder.toString());
		}
		
	}
	private class operratorActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String command =e.getActionCommand();
			StringBuilder builder = new StringBuilder(getText());
			if(command.equals("=")) {																											//�ж�Ϊ���ڷ���
				if(Tool.texterr(builder.toString())) {																						//�ж�������������λ�ã�true ��ȷ��false ����
					ArrayList<Character> list=Detail.trans(builder.toString());
					System.out.println(list);
					if(Tool.listeer(list) && Detail.cucalate(list)!=null) {														//�ж����ֺ� ���ŵ��������Ƿ�Ϊ���󣬳���Ϊ0��
						BigDecimal result =	Detail.cucalate(list);
						StringBuilder doublestr = new StringBuilder(result.toString());
						if(Tool.isdouble(doublestr.toString())) {																		//�ж�Ϊ��������
							while(doublestr.charAt(doublestr.length()-1)=='0' && doublestr.length()>1
									  ||doublestr.charAt(doublestr.length()-1)=='.' && doublestr.length()>1) {
								doublestr.deleteCharAt(doublestr.length()-1);
							}
							builder.delete(0, builder.length());
							builder.append(doublestr.toString());
						}else {
							builder.delete(0, builder.length());
							builder.append(result.intValue());
						}
					}else {
						builder.delete(0, builder.length());
						builder.append('��');
					}
				}else {
					builder.delete(0, builder.length());
					builder.append('��');
				}
			}else if(command.equals("(")|| command.equals(")")) {
				if(builder.length()==1 && (builder.toString().equals("0")||builder.toString().equals("��"))) {
					builder.delete(0, builder.length());
				}
				builder.append(command);
			}else if(command.equals("-")) {
				if(builder.length()==1&& builder.toString().equals("��")) {
					builder.delete(0, builder.length());
					builder.append('0');
				}
				if(builder.charAt(builder.length()-1)>'9' || builder.charAt(builder.length()-1)<'0') {
					builder.append("(0");
				}
				builder.append(command);
			}else {
				if(builder.length()==1&&builder.toString().equals("��")) {
					builder.delete(0, builder.length());
					builder.append('0');
				}
				builder.append(command);
			}
			setResult(builder.toString());
		}
		
	}	

}
