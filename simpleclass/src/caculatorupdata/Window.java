package caculatorupdata;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Window extends JFrame {
	private String [] symbols= {"(",")","CE","<","7","8","9","+","4","5","6","-",
													   "1","2","3","*","0",".","=","/"};
	private SmbolListener listener;
	public Window() {
		
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setBounds(200, 200, 300, 200);
		super.setVisible(true);
		super.setTitle("¼ÆËãÆ÷");
		super.setResizable(false); 
		
		Container container = super.getContentPane();
		BorderLayout borederlayout = new BorderLayout();
		container.setLayout(borederlayout);
		
		JButton disply = new JButton();
		disply.setText("0");
		disply.setEnabled(false);
		disply.setHorizontalAlignment(SwingConstants.RIGHT);
		super.add(disply,BorderLayout.NORTH);
		
		JPanel panel =new JPanel();
		panel.setLayout(new GridLayout(5,4));
		super.add(panel, BorderLayout.CENTER);
		listener = new SmbolListener();
		
		
		for(int i = 0;i<symbols.length;i++) {
			JButton button = new JButton(symbols[i]);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					listener.setText(disply.getText());
					listener.actionPerformed(e);
					disply.setText(listener.getResult());
				}
			});
			button.setFocusable(false);
			panel.add(button);
		}
	
	}
	public static void main(String [] args) {
		new Window();
	}
	
}
