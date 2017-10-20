package com.company;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class forthWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,Graph g,JFrame f) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					forthWindow window = new forthWindow(g,f);
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
	public forthWindow(Graph g,JFrame f) {
		initialize(g,f);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Graph g,JFrame f) {
		frame = new JFrame();
		frame.setTitle("\u4E00\u5BF9\u4E00\u6700\u77ED\u8DEF");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u5355\u8BCD1");
		lblNewLabel.setBounds(14, 84, 90, 18);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(111, 81, 92, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u8F93\u5165\u5355\u8BCD2");
		lblNewLabel_1.setBounds(217, 84, 90, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(332, 81, 86, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("\u5C55\u793A\u6700\u77ED\u8DEF");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String word1 = textField.getText();
				String word2 = textField_1.getText();
				if (!g.isTheWordExisted(word1)||!g.isTheWordExisted(word2)) {
					JOptionPane.showMessageDialog(null, "输入的单词不在图中，请重新确认您的输入","错误",JOptionPane.ERROR_MESSAGE);
				}
				else{
					String[] result=g.calcShortestPath(word1, word2);
					g.show_path_all(result, frame);
					frame.setVisible(false);
				}
				
			}
		});
		btnNewButton.setBounds(76, 164, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(268, 164, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
	}
}
