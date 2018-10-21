package calulatorprogram;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		private JPanel Cpanl;			//上方容器的下容器
		private JPanel cols1;			//按键第一排
		private JPanel cols2;			//按键第二排
		private JPanel cols3;			//按键第三排
		private JPanel cols4;			//按键第四排
		private JPanel cols5;			//按键第五排
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
			ActionListener listener =new operrator();
			ActionListener special = new specialoperrator();
			/*
			 *  为容器添加按钮，事件监听器；
			 * */
			addButton("(",listener,cols1);
			addButton(")",listener,cols1);
			addButton("CE",special,cols1);
			addButton("<",special,cols1);
			
			addButton("7",listener,cols2);
			addButton("8",listener,cols2);
			addButton("9",listener,cols2);
			addButton("+",listener,cols2);	
			
			addButton("4",listener,cols3);
			addButton("5",listener,cols3);
			addButton("6",listener,cols3);
			addButton("-",listener,cols3);
			
			addButton("1",listener,cols4);
			addButton("2",listener,cols4);
			addButton("3",listener,cols4);
			addButton("*",listener,cols4);
			
			
		
			addButton("0",listener,cols5);
			addButton(".",listener,cols5);
			addButton("=",listener,cols5);
			addButton("/",listener,cols5); 
			
			
			
			Cpanl.add(cols1);
			Cpanl.add(cols2);
			Cpanl.add(cols3);
			Cpanl.add(cols4);
			Cpanl.add(cols5);
			
			super.add(Sdisply, BorderLayout.NORTH);
			super.add(Cpanl, BorderLayout.CENTER);
			setResizable(false); 
		}
		//初始化容器
	private void andpanel() {
		Cpanl = new JPanel();
		cols1= new JPanel();
		cols2 = new JPanel();
		cols3 = new JPanel();
		cols4 = new JPanel();
		cols5 = new JPanel();
	}
	//设置布局管理器
	private void setlayout() {
		Cpanl.setLayout(new GridLayout(5,1));
		cols1.setLayout(new GridLayout(1,4));
		cols2.setLayout(new GridLayout(1,4));
		cols3.setLayout(new GridLayout(1,4));
		cols4.setLayout(new GridLayout(1,4));
		cols5.setLayout(new GridLayout(1,4));
	}
	//容器添加按钮和事件监听器
		private void addButton(String txt,ActionListener listener,Container container) {
			JButton button = new JButton(txt);
			button.addActionListener(listener);
			container.add(button);
		}
	
		//内部类，监听器事件实现；（数字符号，等于符号）
		private class operrator  implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				if(command.equals("=")) {
					ArrayList<Character> list= changestr(Sdisply.getText()+'\0');							//添加结束标志
					if(computation(list)!=null) {																					//如果栈中元素等于一就是最后结果；
					if(Pattern.compile("-?\\d+.0+").matcher(computation(list).toString()).find()) {//用正则表达式测试结果为浮点数还是整数，这里为整数；
					Sdisply.setText(""+computation(list).intValue());
					}else {																															//用正则表达式测试结果为浮点数还是整数，这里为浮点数数；
					Sdisply.setText(computation(list).toString());
					}
					}else {																															//如果栈中元素不等于一就是最后结果；
					Sdisply.setText("输入错误");	
					}
				}else {																																//设置‘CE’的监听器事件；
					if(Sdisply.getText().length()==1) {
					Sdisply.setText(" ");
				}
				StringBuilder builder = new StringBuilder(Sdisply.getText());	
				builder.append(command);
				Sdisply.setText(builder.toString());
				}
			}	
		}
		/*
		 * 按钮事件监听器，（删除，归零）
		 * */
		private class specialoperrator implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				if(command.equals("<")) {
					StringBuilder builder = new StringBuilder(Sdisply.getText());
					if(builder.toString().length()==1) {						//如果显示器中的字符串长度为1，则说明用户没有输入任何信息，设置默认字符；（初始化为‘0’，删除和归零操作设置为‘（空格） ’ ）
						Sdisply.setText(" ");
					}else {
						builder.deleteCharAt(builder.length()-1);
						Sdisply.setText(builder.toString());
					}		
				}else {
					Sdisply.setText(" ");
				}
			}
		} 
		/*
		 * 把得到的字符串，处理为后缀表达式；
		 * */
		private ArrayList<Character> changestr(String laststr) {
			ArrayList<Character> list = new ArrayList<>();		//接受后缀表达式的容器
			String builder = new String(laststr);							//目标字符串
			Character str;																	//变量，更新循环条件
			Stack<Character> stack =new Stack<>();					//栈
			Indexof index= new Indexof(0);									//java 方法参数采用按置传递，变量会清零，固设置为 类的方式；
			str = builder.charAt(index.getIndex());						//初始化更新条件
			index.add();																	//下标更新
			while(str!='\0') {
				Decision.dodispose(Decision.decisions(str), stack, list, str, builder, index);		//调用工厂和策略方法
				str=builder.charAt(index.getIndex());													
				index.add();
			}
			while(!stack.isEmpty()) {																		//最后栈不为空，输出栈中内容。（如果栈中存在未出栈内容，其优先级为最小）
				list.add(stack.pop());
			}
			return list;
		}
		/*
		 * 处理后缀表达式，计算出最后结果；
		 * */
		private Double computation(ArrayList<Character> list) {
			list.add('\0');																						//添加结束语句
			Indexof index = new Indexof(0);													//初始化下标
			 char ch = list.get(index.getIndex());												//初始化循环更新变量
			 StringBuilder builder = new StringBuilder();								//用于转化String 和 Double;
			 Stack<Double> stack = new Stack<Double>();								//栈
			 Double allresult = null ;																	//结果
			 while(ch!='\0') {
				 chiose.choseGetresult(chiose.chioseNO(ch), list, index, builder, stack, allresult, ch);		//调用 工厂 策略 选择方法
				 index.add();
				 ch = list.get(index.getIndex());
			 }
			if(stack.size()==1) {
			allresult =stack.pop();
			return allresult;	
			}else {
				return null;
			}
		}
}










