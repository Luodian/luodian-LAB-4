package com.company;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.JLabel;
public class mainWindow {

	private JFrame frame;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton btnNewButton_1;
	private JFileChooser fileChooser;
	private Graph graph;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
        private int t;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
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
	public mainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5B9E\u9A8C1");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		graph=null;
		JButton btnNewButton = new JButton("\u5EFA\u56FE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				String text = textField.getText();
				 BufferedReader in;
				try {
					in = new BufferedReader(new InputStreamReader(new FileInputStream(text)));
					 String line;
				        String all=new String();
				        while((line=in.readLine())!=null)
				        {
				            all+=" ";
				            all+=line;
				        }
				        all = all.replaceAll("[^a-z^A-Z]", " ");
				        all = all.toLowerCase();
				        String[] words=all.split("\\s+");
				        //当开头是空格时，一号字符会变成“”空字符，而这里处理换行时，第一行会自动添加一个空格
				        //所以words[0]必定为“”
				        String[] input=new String[words.length-1];
				        for(int i=0;i<words.length-1;i++)
				            input[i]=words[i+1];
				        graph=new Graph(input);
				        JOptionPane.showMessageDialog(null, "建图成功");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			       
			}
		});
		btnNewButton.setBounds(319, 56, 76, 27);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(165, 57, 97, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u6587\u4EF6\u8DEF\u5F84");
		lblNewLabel.setBounds(34, 60, 117, 18);
		frame.getContentPane().add(lblNewLabel);
		
		btnNewButton_1 = new JButton("\u5C55\u793A\u56FE\u7ED3\u6784");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (graph==null) {
					JOptionPane.showMessageDialog(null, "当前为空图，请先建图！","错误",JOptionPane.ERROR_MESSAGE);
				}
				sevenWindow.main(null, graph, frame);
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(52, 115, 137, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("...");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser=new JFileChooser();
				fileChooser.showOpenDialog(frame);
				String path=fileChooser.getSelectedFile().getPath();
				textField.setText(path);
			}
		});
		btnNewButton_2.setBounds(276, 56, 29, 27);
		frame.getContentPane().add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("\u67E5\u8BE2\u6865\u63A5\u8BCD");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				secondWindow.main(null,graph,frame);
			}
		});
		btnNewButton_3.setBounds(233, 115, 137, 27);
		frame.getContentPane().add(btnNewButton_3);
		
		button = new JButton("\u751F\u6210\u65B0\u6587\u672C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				thirdWindow.main(null, graph, frame);
			}
		});
		button.setBounds(52, 155, 137, 27);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("\u5355\u70B9\u6700\u77ED\u8DEF");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				forthWindow.main(null, graph, frame);
			}
		});
		button_1.setBounds(233, 155, 137, 27);
		frame.getContentPane().add(button_1);
		
		button_2 = new JButton("\u591A\u70B9\u6700\u77ED\u8DEF");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				fifthWindow.main(null, graph, frame);
			}
		});
		button_2.setBounds(52, 195, 137, 27);
		frame.getContentPane().add(button_2);
		
		button_3 = new JButton("\u968F\u673A\u6E38\u8D70");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				String string = graph.randomWalk();
				new DrawgraphRandom(graph.words, graph.edges, frame,graph);
			}
		});
		button_3.setBounds(233, 195, 137, 27);
		frame.getContentPane().add(button_3);
		
		
	}
}
