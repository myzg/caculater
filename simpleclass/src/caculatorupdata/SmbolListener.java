package caculatorupdata;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class SmbolListener implements ActionListener{
	private String result;																	//上传的字符串；
	private String text;																	//得到并判断上次字符串的异常问题
	
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
					||(builder.toString().length()==1 && builder.toString().equals("∞")) ) {
				builder.deleteCharAt(0);																								//若果上次结果为初始或错误删除添加空格；
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
				if(builder.toString().length()<=1) {																						//如果显示器中的字符串长度为1，则说明用户没有输入任何信息，设置默认字符；（初始化为‘0’，删除和归零操作设置为‘（空格） ’ ）
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
			if(command.equals("=")) {																											//判断为等于符号
				if(Tool.texterr(builder.toString())) {																						//判断括号数量，和位置，true 真确，false 错误；
					ArrayList<Character> list=Detail.trans(builder.toString());
					System.out.println(list);
					if(Tool.listeer(list) && Detail.cucalate(list)!=null) {														//判断数字和 符号的数量，是否为错误，除数为0；
						BigDecimal result =	Detail.cucalate(list);
						StringBuilder doublestr = new StringBuilder(result.toString());
						if(Tool.isdouble(doublestr.toString())) {																		//判断为浮点数；
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
						builder.append('∞');
					}
				}else {
					builder.delete(0, builder.length());
					builder.append('∞');
				}
			}else if(command.equals("(")|| command.equals(")")) {
				if(builder.length()==1 && (builder.toString().equals("0")||builder.toString().equals("∞"))) {
					builder.delete(0, builder.length());
				}
				builder.append(command);
			}else if(command.equals("-")) {
				if(builder.length()==1&& builder.toString().equals("∞")) {
					builder.delete(0, builder.length());
					builder.append('0');
				}
				if(builder.charAt(builder.length()-1)>'9' || builder.charAt(builder.length()-1)<'0') {
					builder.append("(0");
				}
				builder.append(command);
			}else {
				if(builder.length()==1&&builder.toString().equals("∞")) {
					builder.delete(0, builder.length());
					builder.append('0');
				}
				builder.append(command);
			}
			setResult(builder.toString());
		}
		
	}	

}
