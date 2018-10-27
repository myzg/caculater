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
		private JButton Sdisply;			//����ʾ��	
		private JPanel Cpanl;				//�Ϸ�������������
		
																																																//��ʼ��������
	public Window() {
			
			super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			super.setBounds(100, 100, 300, 200);
			super.setVisible(true);
			super.setTitle("������");
			/*
			 * ������ʾ�����ԣ������ã����ֶ��뷽ʽ���ң�����ʼ���֣�
			 * */
			Sdisply = new JButton("0");
			Sdisply.setHorizontalAlignment(SwingConstants.RIGHT);
			Sdisply.setEnabled(false);
			
		/*
		 * ���÷������ð�ť����
		 * */
			andpanel();
			setlayout();
			/*
			 * ���ü������¼�
			 * */
		//	ActionListener listener =new operrator1();
		//	ActionListener special = new specialoperrator1();
				ActionListener listener = new commonActionListener();
			/*
			 *  Ϊ������Ӱ�ť���¼���������
			 * */
			addButton(StrConfiguration.BRACKET_LEFT_SYMBOL,listener,Cpanl);													//������
			addButton(StrConfiguration.BRACKET_RIGHT_SYMBOL,listener,Cpanl);												//������
			addButton(StrConfiguration.CLEAR_SYMBOL,listener,Cpanl);																		//CE
			addButton(StrConfiguration.ELIMINATE_SYMBOL,listener,Cpanl);							 								// ɾ�����˺󣩣�
			
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
			addButton(StrConfiguration.DIV_SYMBOL,listener,Cpanl); 													 						//����
			
			super.add(Sdisply, BorderLayout.NORTH);
			super.add(Cpanl, BorderLayout.CENTER);
			setResizable(false); 
		}
																																									//��ʼ������
	private void andpanel() {
		Cpanl = new JPanel();
		
	}
																																									//���ò��ֹ�����
	private void setlayout() {
		Cpanl.setLayout(new GridLayout(5,4));
	}
																																									//������Ӱ�ť���¼�������
		private void addButton(String txt,ActionListener listener,Container container) {
			JButton button = new JButton(txt);
			button.addActionListener(listener);
			button.setFocusable(false);
			container.add(button);
		}
		/*
		 * �ѵõ����ַ���������Ϊ��׺���ʽ��
		 * */
		private ArrayList<Character> changestr(String laststr) {
			ArrayList<Character> list = new ArrayList<>();																		//���ܺ�׺���ʽ������
			String builder = new String(laststr);																							//Ŀ���ַ���
			Character str;																																	//����������ѭ������
			Stack<Character> stack =new Stack<>();																					//ջ
			Indexof index= new Indexof(0);																									//java �����������ð��ô��ݣ����������㣬������Ϊ ��ķ�ʽ��
			str = builder.charAt(index.getIndex());																						//��ʼ����������
			index.add();																																		//�±����
			tool.handle(stack, list, str, builder, index);
			tool.clear(stack, list);
			return list;
		} 
		/*
		 * �����׺���ʽ��������������
		 * */
		private BigDecimal computation(ArrayList<Character> list) {
			list.add('\0');																																		//��ӽ������
			Indexof index = new Indexof(0);																									//��ʼ���±�
			 char ch = list.get(index.getIndex());																							//��ʼ��ѭ�����±���
			 StringBuilder builder = new StringBuilder();																				//����ת��String �� Double;
			 Stack<BigDecimal> stack = new Stack<BigDecimal>();															//ջ
			 BigDecimal allresult = null ;																											//���
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
					|| (Sdisply.getText().length()==1 &&Sdisply.getText().equals("��"))) {
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
				if(builder.toString().length()<=1) {																						//�����ʾ���е��ַ�������Ϊ1����˵���û�û�������κ���Ϣ������Ĭ���ַ�������ʼ��Ϊ��0����ɾ���͹����������Ϊ�����ո� �� ��
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
	if(command.equals(StrConfiguration.EQUAL_SYMBOL)) {												//�ж��������ԣ�
	  if(!Sdisply.getText().equals("��")) {
		if(tool.textexpression(Sdisply.getText())) {
			ArrayList<Character> list= changestr(Sdisply.getText()+'\0');											//��ӽ�����־	
			if(tool.expression(list)) {	
				if(computation(list)!=null) {																									//���ջ��Ԫ�ص���һ�����������
					BigDecimal changenumber = computation(list);
						   String changestr =changenumber.toString();
							if(Pattern.compile(StrConfiguration.REGULAR_THREE_DOUBLE).matcher(changestr).find()){
									StringBuilder builder = new StringBuilder(changestr);                                //��������ʽ���Խ��Ϊ��������������������Ϊ��������
									if(builder.charAt(builder.length()-1)=='0'&&builder.length()!=1) {
										while((builder.charAt(builder.length()-1)=='0'&& builder.length()>1)||(builder.charAt(builder.length()-1)=='.'&& builder.length()>1 )) {
											builder.deleteCharAt(builder.length()-1);
										}
										Sdisply.setText(builder.toString());
									}else {
									Sdisply.setText(changestr);
									}
							}else {																																//��������ʽ���Խ��Ϊ��������������������Ϊ����;
							Sdisply.setText(""+changenumber.intValue());
						}
					}else {																																		//���ջ��Ԫ�ز�����һ�����������
						Sdisply.setText("��");	
						System.out.println("1");
					}
				}else {
					Sdisply.setText("��");		
					System.out.println("2");
				}
			}else {
		 		Sdisply.setText("��");
		 		System.out.println("3");
				}
		}else {
			Sdisply.setText("��");
			System.out.println("4");
		   }	
		}else if(command.equals(StrConfiguration.BRACKET_LEFT_SYMBOL)||command.equals(StrConfiguration.BRACKET_RIGHT_SYMBOL)) {
			
			if(Sdisply.getText().length()==1 && (Sdisply.getText().equals("0")||Sdisply.getText().equals("��"))) {
			Sdisply.setText(" ");
			}
			StringBuilder builder =new StringBuilder(Sdisply.getText());
			builder.append(command);
			Sdisply.setText(builder.toString());
		}
		else {																																			
				if((Sdisply.getText().length()==1&&Sdisply.getText().equals("��"))) {
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










