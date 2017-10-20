package com.company;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class sevenWindow {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,Graph g,JFrame f) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sevenWindow window = new sevenWindow(g,f);
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
	public sevenWindow(Graph g,JFrame f) {
		initialize(g,f);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Graph g,JFrame f) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u6307\u5B9A\u4FDD\u5B58\u5730\u5740");
		lblNewLabel.setBounds(61, 96, 124, 18);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(199, 93, 140, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u7EE7\u7EED");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				g.show(textField.getText(), f);
			}
		});
		btnNewButton.setBounds(145, 158, 113, 27);
		frame.getContentPane().add(btnNewButton);
	}
}
