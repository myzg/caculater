package calulatorprogram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Window<operrator> extends JFrame {
		private JButton Sdisply;			//下显示器	
		private JPanel Cpanl;				//上方容器的下容器
		
																																																//初始化主窗体
	public Window() {
			
			super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			super.setBounds(100, 100, 300, 200);
			super.setVisible(true);
			super.setTitle("计算器");
			/*
			 * 设置显示器属性：不可用，文字对齐方式（右），初始文字；
			 * */
			Sdisply = new JButton("0");
			Sdisply.setHorizontalAlignment(SwingConstants.RIGHT);
			Sdisply.setEnabled(false);
			
		/*
		 * 调用方法设置按钮容器
		 * */
			andpanel();
			setlayout();
			/*
			 * 设置监听器事件
			 * */
		//	ActionListener listener =new operrator1();
		//	ActionListener special = new specialoperrator1();
				ActionListener listener = new commonActionListener();
			/*
			 *  为容器添加按钮，事件监听器；
			 * */
			addButton(StrConfiguration.BRACKET_LEFT_SYMBOL,listener,Cpanl);													//左括号
			addButton(StrConfiguration.BRACKET_RIGHT_SYMBOL,listener,Cpanl);												//右括号
			addButton(StrConfiguration.CLEAR_SYMBOL,listener,Cpanl);																		//CE
			addButton(StrConfiguration.ELIMINATE_SYMBOL,listener,Cpanl);							 								// 删除（退后）；
			
			addButton(StrConfiguration.SEVEN_NUMBER,listener,Cpanl);																		//7
			addButton(StrConfiguration.EIGHT_NUMBER,listener,Cpanl);																		//8
			addButton(StrConfiguration.NINE_NUMBER,listener,Cpanl);																		//9
			addButton(StrConfiguration.ADD_SYMBOL,listener,Cpanl);																			//+
			
			addButton(StrConfiguration.FOUR_NUMBER,listener,Cpanl);																		//4
			addButton(StrConfiguration.FIVE_NUMBER,listener,Cpanl);																			//5
			addButton(StrConfiguration.SIX_NUMBER,listener,Cpanl);																			//6
			addButton(StrConfiguration.SUB_SYMBOL,listener,Cpanl);																			//-
			
			addButton(StrConfiguration.ONE_NUMBER,listener,Cpanl	);																			//1
			addButton(StrConfiguration.TWO_NUMBER,listener,Cpanl);																 			//2
			addButton(StrConfiguration.THREE_NUMBER,listener,Cpanl);																	//3
			addButton(StrConfiguration.MUL_SYMBOL,listener,Cpanl);																			//*
			
			
		
			addButton(StrConfiguration.ZERO_NUMBER,listener,Cpanl);																		//0
			addButton(StrConfiguration.POINT_SYMBOL,listener,Cpanl);																		//.
			addButton(StrConfiguration.EQUAL_SYMBOL,listener,Cpanl);																		//=
			addButton(StrConfiguration.DIV_SYMBOL,listener,Cpanl); 													 						//除号
			
			super.add(Sdisply, BorderLayout.NORTH);
			super.add(Cpanl, BorderLayout.CENTER);
			setResizable(false); 
		}
																																									//初始化容器
	private void andpanel() {
		Cpanl = new JPanel();
		
	}
																																									//设置布局管理器
	private void setlayout() {
		Cpanl.setLayout(new GridLayout(5,4));
	}
																																									//容器添加按钮和事件监听器
		private void addButton(String txt,ActionListener listener,Container container) {
			JButton button = new JButton(txt);
			button.addActionListener(listener);
			button.setFocusable(false);
			container.add(button);
		}
		/*
		 * 把得到的字符串，处理为后缀表达式；
		 * */
		private ArrayList<Character> changestr(String laststr) {
			ArrayList<Character> list = new ArrayList<>();																		//接受后缀表达式的容器
			String builder = new String(laststr);																							//目标字符串
			Character str;																																	//变量，更新循环条件
			Stack<Character> stack =new Stack<>();																					//栈
			Indexof index= new Indexof(0);																									//java 方法参数采用按置传递，变量会清零，固设置为 类的方式；
			str = builder.charAt(index.getIndex());																						//初始化更新条件
			index.add();																																		//下标更新
			tool.handle(stack, list, str, builder, index);
			tool.clear(stack, list);
			return list;
		} 
		/*
		 * 处理后缀表达式，计算出最后结果；
		 * */
		private BigDecimal computation(ArrayList<Character> list) {
			list.add('\0');																																		//添加结束语句
			Indexof index = new Indexof(0);																									//初始化下标
			 char ch = list.get(index.getIndex());																							//初始化循环更新变量
			 StringBuilder builder = new StringBuilder();																				//用于转化String 和 Double;
			 Stack<BigDecimal> stack = new Stack<BigDecimal>();															//栈
			 BigDecimal allresult = null ;																											//结果
			 BigDecimal tonumber;
			tool.compute(list, index, builder, stack, allresult, ch);
			if(stack.size()==1) {
			allresult =stack.pop();
			tonumber = (BigDecimal)allresult;
			return tonumber;	
			}else {
				return null;
			}
		}
	
	private class numbersActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if((Sdisply.getText().length()==1 && Sdisply.getText().equals(" "))|| (Sdisply.getText().length()==1 && Sdisply.getText().equals("0"))
					|| (Sdisply.getText().length()==1 &&Sdisply.getText().equals("∞"))) {
				Sdisply.setText(" ");
			}
			if(command.equals(StrConfiguration.POINT_SYMBOL)) {
				StringBuilder builder = new StringBuilder(Sdisply.getText());
				char str = builder.charAt(builder.length() -1);
				if(!(str<='9' && str>='0') || (builder.length()==1 && !(builder.charAt(0)>='0'&& builder.charAt(0) <='9'))) {
					builder.append("0");
					Sdisply.setText(builder.toString());
				}
			}
			StringBuilder builder = new StringBuilder(Sdisply.getText());
			builder.append(command);
			Sdisply.setText(builder.toString());
			}

	}
	private class specialoperratorActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if(command.equals(StrConfiguration.ELIMINATE_SYMBOL)) {
				StringBuilder builder = new StringBuilder(Sdisply.getText());
				if(builder.toString().length()<=1) {																						//如果显示器中的字符串长度为1，则说明用户没有输入任何信息，设置默认字符；（初始化为‘0’，删除和归零操作设置为‘（空格） ’ ）
					Sdisply.setText("0");
				}else {
					builder.deleteCharAt(builder.length()-1);
					Sdisply.setText(builder.toString());
				}		
			}else {
				Sdisply.setText("0");
			}
		}
	}
	private class operratorActionListener implements ActionListener{									

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
	if(command.equals(StrConfiguration.EQUAL_SYMBOL)) {												//判断括号数对；
	  if(!Sdisply.getText().equals("∞")) {
		if(tool.textexpression(Sdisply.getText())) {
			ArrayList<Character> list= changestr(Sdisply.getText()+'\0');											//添加结束标志	
			if(tool.expression(list)) {	
				if(computation(list)!=null) {																									//如果栈中元素等于一就是最后结果；
					BigDecimal changenumber = computation(list);
						   String changestr =changenumber.toString();
							if(Pattern.compile(StrConfiguration.REGULAR_THREE_DOUBLE).matcher(changestr).find()){
									StringBuilder builder = new StringBuilder(changestr);                                //用正则表达式测试结果为浮点数还是整数，这里为浮点数；
									if(builder.charAt(builder.length()-1)=='0'&&builder.length()!=1) {
										while((builder.charAt(builder.length()-1)=='0'&& builder.length()>1)||(builder.charAt(builder.length()-1)=='.'&& builder.length()>1 )) {
											builder.deleteCharAt(builder.length()-1);
										}
										Sdisply.setText(builder.toString());
									}else {
									Sdisply.setText(changestr);
									}
							}else {																																//用正则表达式测试结果为浮点数还是整数，这里为整数;
							Sdisply.setText(""+changenumber.intValue());
						}
					}else {																																		//如果栈中元素不等于一就是最后结果；
						Sdisply.setText("∞");	
						System.out.println("1");
					}
				}else {
					Sdisply.setText("∞");		
					System.out.println("2");
				}
			}else {
		 		Sdisply.setText("∞");
		 		System.out.println("3");
				}
		}else {
			Sdisply.setText("∞");
			System.out.println("4");
		   }	
		}else if(command.equals(StrConfiguration.BRACKET_LEFT_SYMBOL)||command.equals(StrConfiguration.BRACKET_RIGHT_SYMBOL)) {
			
			if(Sdisply.getText().length()==1 && (Sdisply.getText().equals("0")||Sdisply.getText().equals("∞"))) {
			Sdisply.setText(" ");
			}
			StringBuilder builder =new StringBuilder(Sdisply.getText());
			builder.append(command);
			Sdisply.setText(builder.toString());
		}
		else {																																			
				if((Sdisply.getText().length()==1&&Sdisply.getText().equals("∞"))) {
					Sdisply.setText("0");
				}
				StringBuilder builder = new StringBuilder(Sdisply.getText());	
				builder.append(command);
				Sdisply.setText(builder.toString());
			}
		}
	}
	private class commonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			ActionListener listener;
			if(command.equals(StrConfiguration.ADD_SYMBOL)||command.equals(StrConfiguration.SUB_SYMBOL)||command.equals(StrConfiguration.MUL_SYMBOL)
					||command.equals(StrConfiguration.DIV_SYMBOL)||command.equals(StrConfiguration.EQUAL_SYMBOL)||command.equals(StrConfiguration.BRACKET_LEFT_SYMBOL)
					||command.equals(StrConfiguration.BRACKET_RIGHT_SYMBOL)) {
				listener = new operratorActionListener();
			}else if(command.equals(StrConfiguration.CLEAR_SYMBOL)||command.equals(StrConfiguration.ELIMINATE_SYMBOL)) {
				listener = new specialoperratorActionListener();
			}else {
				listener = new numbersActionListener();
			}
			listener.actionPerformed(e);
		}
	}
}










