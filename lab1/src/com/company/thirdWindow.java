package com.company;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class thirdWindow {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,Graph g,JFrame f) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					thirdWindow window = new thirdWindow(g,f);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public thirdWindow(Graph g,JFrame f) {
		initialize(g,f);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Graph g,JFrame f) {
		frame = new JFrame();
		frame.setTitle("\u751F\u6210\u65B0\u6587\u672C");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u65B0\u6587\u672C\u5185\u5BB9");
		lblNewLabel.setBounds(84, 82, 85, 18);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(183, 79, 162, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u751F\u6210");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text=textField.getText();
				String newText=g.generateNewText(text);
				JOptionPane.showMessageDialog(null, "生成的新文本为："+newText);
			}
		});
		btnNewButton.setBounds(84, 149, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(232, 149, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
	}

}
