package com.company;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.awt.event.ActionEvent;

public class sixthWindow {

	private JFrame frame;
	private JTextField textField;
	private JFileChooser fileChooser;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,Graph g,JFrame f,String path) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sixthWindow window = new sixthWindow(g,f,path);
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
	public sixthWindow(Graph g,JFrame f,String path) {
		initialize(g,f,path);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Graph g,JFrame f,String path) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u4FDD\u5B58\u5730\u5740");
		lblNewLabel.setBounds(74, 78, 105, 18);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(199, 75, 132, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u4FDD\u5B58");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File file =new File(textField.getText());
				try {
					FileOutputStream fileOutputStream =new FileOutputStream(file);
					OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
					BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
					bufferedWriter.write(path);
					bufferedWriter.close();
					JOptionPane.showMessageDialog(null, "±£´æ³É¹¦");
					f.setVisible(true);
					frame.dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(143, 152, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("...");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChooser=new JFileChooser();
				fileChooser.showOpenDialog(frame);
				String path=fileChooser.getSelectedFile().getPath();
				textField.setText(path);
			}
		});
		btnNewButton_1.setBounds(345, 74, 29, 27);
		frame.getContentPane().add(btnNewButton_1);
	}
}
