package com.company;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class fifthWindow {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,Graph g,JFrame f) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fifthWindow window = new fifthWindow(g,f);
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
	public fifthWindow(Graph g,JFrame f) {
		initialize(g,f);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Graph g,JFrame f) {
		frame = new JFrame();
		frame.setTitle("\u4E00\u5BF9\u591A\u6700\u77ED\u8DEF");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u5355\u8BCD1");
		lblNewLabel.setBounds(78, 84, 91, 18);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(183, 81, 152, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u751F\u6210\u6700\u77ED\u8DEF");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string = textField.getText();
				String[] result = g.calcShortestPathOfAll(string);
				for(int i=0;i<result.length;i++)
				{
					System.out.println(result[i]);
				}
				g.show_path_all(result, frame);
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(84, 155, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(211, 155, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
	}

}
