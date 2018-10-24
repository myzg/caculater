package calulatorprogram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
		private JPanel Cpanl;			//�Ϸ�������������
		private JPanel cols1;			//������һ��
		private JPanel cols2;			//�����ڶ���
		private JPanel cols3;			//����������
		private JPanel cols4;			//����������
		private JPanel cols5;			//����������
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
			ActionListener listener =new operrator();
			ActionListener special = new specialoperrator();
			/*
			 *  Ϊ������Ӱ�ť���¼���������
			 * */
			addButton(StrConfiguration.BRACKET_LEFT_SYMBOL,listener,Cpanl);																	//������
			addButton(StrConfiguration.BRACKET_RIGHT_SYMBOL,listener,Cpanl);																//������
			addButton(StrConfiguration.CLEAR_SYMBOL,special,Cpanl);																					//CE
			addButton(StrConfiguration.ELIMINATE_SYMBOL,special,Cpanl);																			// ɾ�����˺󣩣�
			
			addButton(StrConfiguration.SEVEN_NUMBER,listener,Cpanl);																				//7
			addButton(StrConfiguration.EIGHT_NUMBER,listener,Cpanl);																					//8
			addButton(StrConfiguration.NINE_NUMBER,listener,Cpanl);																					//9
			addButton(StrConfiguration.ADD_SYMBOL,listener,Cpanl);																						//+
			
			addButton(StrConfiguration.FOUR_NUMBER,listener,Cpanl);																					//4
			addButton(StrConfiguration.FIVE_NUMBER,listener,Cpanl);																					//5
			addButton(StrConfiguration.SIX_NUMBER,listener,Cpanl);																						//6
			addButton(StrConfiguration.SUB_SYMBOL,listener,Cpanl);																						//-
			
			addButton(StrConfiguration.ONE_NUMBER,listener,Cpanl);																					//1
			addButton(StrConfiguration.TWO_NUMBER,listener,Cpanl);																		 			//2
			addButton(StrConfiguration.THREE_NUMBER,listener,Cpanl);																				//3
			addButton(StrConfiguration.MUL_SYMBOL,listener,Cpanl);																						//*
			
			
		
			addButton(StrConfiguration.ZERO_NUMBER,listener,Cpanl);																					//0
			addButton(StrConfiguration.POINT_SYMBOL,listener,Cpanl);																					//.
			addButton(StrConfiguration.EQUAL_SYMBOL,listener,Cpanl);																				//=
			addButton(StrConfiguration.DIV_SYMBOL,listener,Cpanl); 																						//����
			
			
			
			
			
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
	
																																										//�ڲ��࣬�������¼�ʵ�֣������ַ��ţ����ڷ��ţ�
		private class operrator  implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				if(command.equals(StrConfiguration.EQUAL_SYMBOL)) {
					ArrayList<Character> list= changestr(Sdisply.getText()+'\0');										//��ӽ�����־
						if(computation(list)!=null) {																								//���ջ��Ԫ�ص���һ�����������
							Number changenumber = computation(list);
							   String changestr =computation(list).toString();
								if(Pattern.compile(StrConfiguration.REGULAR_ONE_DOUBLE).matcher(changestr).find()||
									Pattern.compile(StrConfiguration.REGULAR_TWO_DOUBLE).matcher(changestr).find()&&
									!(Pattern.compile(StrConfiguration.REGULAR_THREE_DOUBLE).matcher(changestr).find())
									) {																																//��������ʽ���Խ��Ϊ��������������������Ϊ��������
									Sdisply.setText(changestr);	
							}else {																																//��������ʽ���Խ��Ϊ��������������������Ϊ����;
								Sdisply.setText(""+changenumber.intValue());
						}
					}else {																																		//���ջ��Ԫ�ز�����һ�����������
						Sdisply.setText("��");	
					}
			}else {																																				//���á�CE���ļ������¼���
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
		 * ��ť�¼�����������ɾ�������㣩
		 * */
		private class specialoperrator implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				if(command.equals(StrConfiguration.ELIMINATE_SYMBOL)) {
					StringBuilder builder = new StringBuilder(Sdisply.getText());
					if(builder.toString().length()==1) {																						//�����ʾ���е��ַ�������Ϊ1����˵���û�û�������κ���Ϣ������Ĭ���ַ�������ʼ��Ϊ��0����ɾ���͹����������Ϊ�����ո� �� ��
						Sdisply.setText(" ");
					}else {
						builder.deleteCharAt(builder.length()-1);
						Sdisply.setText(builder.toString());
					}		
				}else {
					Sdisply.setText("0");
				}
			}
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
		private Number computation(ArrayList<Character> list) {
			list.add('\0');																																		//��ӽ������
			Indexof index = new Indexof(0);																									//��ʼ���±�
			 char ch = list.get(index.getIndex());																							//��ʼ��ѭ�����±���
			 StringBuilder builder = new StringBuilder();																				//����ת��String �� Double;
			 Stack<Double> stack = new Stack<Double>();																			//ջ
			Double allresult = null ;																													//���
			Number tonumber;
			tool.compute(list, index, builder, stack, allresult, ch);
			if(stack.size()==1) {
			allresult =stack.pop();
			tonumber = (Number)allresult;
			return tonumber;	
			}else {
				return null;
			}
		}
	
}










