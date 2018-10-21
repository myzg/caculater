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
		//��ʼ������
	private void andpanel() {
		Cpanl = new JPanel();
		cols1= new JPanel();
		cols2 = new JPanel();
		cols3 = new JPanel();
		cols4 = new JPanel();
		cols5 = new JPanel();
	}
	//���ò��ֹ�����
	private void setlayout() {
		Cpanl.setLayout(new GridLayout(5,1));
		cols1.setLayout(new GridLayout(1,4));
		cols2.setLayout(new GridLayout(1,4));
		cols3.setLayout(new GridLayout(1,4));
		cols4.setLayout(new GridLayout(1,4));
		cols5.setLayout(new GridLayout(1,4));
	}
	//������Ӱ�ť���¼�������
		private void addButton(String txt,ActionListener listener,Container container) {
			JButton button = new JButton(txt);
			button.addActionListener(listener);
			container.add(button);
		}
	
		//�ڲ��࣬�������¼�ʵ�֣������ַ��ţ����ڷ��ţ�
		private class operrator  implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				if(command.equals("=")) {
					ArrayList<Character> list= changestr(Sdisply.getText()+'\0');							//��ӽ�����־
					if(computation(list)!=null) {																					//���ջ��Ԫ�ص���һ�����������
					if(Pattern.compile("-?\\d+.0+").matcher(computation(list).toString()).find()) {//��������ʽ���Խ��Ϊ��������������������Ϊ������
					Sdisply.setText(""+computation(list).intValue());
					}else {																															//��������ʽ���Խ��Ϊ��������������������Ϊ����������
					Sdisply.setText(computation(list).toString());
					}
					}else {																															//���ջ��Ԫ�ز�����һ�����������
					Sdisply.setText("�������");	
					}
				}else {																																//���á�CE���ļ������¼���
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
				if(command.equals("<")) {
					StringBuilder builder = new StringBuilder(Sdisply.getText());
					if(builder.toString().length()==1) {						//�����ʾ���е��ַ�������Ϊ1����˵���û�û�������κ���Ϣ������Ĭ���ַ�������ʼ��Ϊ��0����ɾ���͹����������Ϊ�����ո� �� ��
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
		 * �ѵõ����ַ���������Ϊ��׺���ʽ��
		 * */
		private ArrayList<Character> changestr(String laststr) {
			ArrayList<Character> list = new ArrayList<>();		//���ܺ�׺���ʽ������
			String builder = new String(laststr);							//Ŀ���ַ���
			Character str;																	//����������ѭ������
			Stack<Character> stack =new Stack<>();					//ջ
			Indexof index= new Indexof(0);									//java �����������ð��ô��ݣ����������㣬������Ϊ ��ķ�ʽ��
			str = builder.charAt(index.getIndex());						//��ʼ����������
			index.add();																	//�±����
			while(str!='\0') {
				Decision.dodispose(Decision.decisions(str), stack, list, str, builder, index);		//���ù����Ͳ��Է���
				str=builder.charAt(index.getIndex());													
				index.add();
			}
			while(!stack.isEmpty()) {																		//���ջ��Ϊ�գ����ջ�����ݡ������ջ�д���δ��ջ���ݣ������ȼ�Ϊ��С��
				list.add(stack.pop());
			}
			return list;
		}
		/*
		 * �����׺���ʽ��������������
		 * */
		private Double computation(ArrayList<Character> list) {
			list.add('\0');																						//��ӽ������
			Indexof index = new Indexof(0);													//��ʼ���±�
			 char ch = list.get(index.getIndex());												//��ʼ��ѭ�����±���
			 StringBuilder builder = new StringBuilder();								//����ת��String �� Double;
			 Stack<Double> stack = new Stack<Double>();								//ջ
			 Double allresult = null ;																	//���
			 while(ch!='\0') {
				 chiose.choseGetresult(chiose.chioseNO(ch), list, index, builder, stack, allresult, ch);		//���� ���� ���� ѡ�񷽷�
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










