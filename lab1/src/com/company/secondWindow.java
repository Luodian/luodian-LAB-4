package com.company;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class secondWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
        private int t;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,Graph g,JFrame f) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					secondWindow window = new secondWindow(g,f);
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
	public secondWindow(Graph g,JFrame f) {
		initialize(g,f);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Graph g,JFrame f) {
		frame = new JFrame();
		frame.setTitle("\u67E5\u8BE2\u6865\u63A5\u8BCD");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u4E00\u53F7\u6865\u63A5\u8BCD");
		lblNewLabel.setBounds(79, 67, 96, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u4E8C\u53F7\u6865\u63A5\u8BCD");
		lblNewLabel_1.setBounds(79, 106, 96, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(201, 64, 136, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(201, 103, 136, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word1 = textField.getText();
				String word2 = textField_1.getText();
				if (word1==null||word2==null) {
					JOptionPane.showMessageDialog(null, "连接词不可为空","错误",JOptionPane.ERROR_MESSAGE);
				}
				else{
					String[] bridges=g.queryBridgeWords(word1, word2);
					if (bridges.length==0) {
						JOptionPane.showMessageDialog(null, word1+"和"+word2+"没有桥接词");
					}
					else{
						String result=new String();
						for(int i=0;i<bridges.length;i++){
							if (i!=bridges.length-1) result+=bridges[i]+",";
							else result+=bridges[i];
						}
						JOptionPane.showMessageDialog(null, word1+"和"+word2+"桥接词为："+result);
					}
				}
			}
		});
		btnNewButton.setBounds(84, 163, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(224, 163, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
	}

}
