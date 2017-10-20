package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import static java.lang.Math.PI;
import static java.lang.Math.sin;
import static java.lang.StrictMath.cos;

public class DrawgraphRandom extends JFrame
{
    ArrayList<String> words;
    int [][] edges;
    private JPanel jPanel;
    private Button button;
    private Button button1;
    private Button button2;
    int count;
    int length;
    Vector<String> nodes = new Vector<String>();
    String pathss;
    private String[] ttemp;
    public DrawgraphRandom(ArrayList<String> words,int [][] edges,JFrame f,Graph g)
    {
        super();
        button= new Button("终止并保存");
        button1 = new Button("下一步");
        button2 = new Button("开始");
        
        JPanel jPanel=(JPanel)this.getContentPane();
        jPanel.setLayout(null);
        button.setBounds(1600, 800, 100, 30);
        button1.setBounds(1600, 700, 100, 30);
        button2.setBounds(1600, 600, 100, 30);
        button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sixthWindow.main(null, g, f, pathss);
				dispose();
			}
		});
        button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (ttemp==null) {
					JOptionPane.showMessageDialog(null, "请先点击开始按钮","错误",JOptionPane.ERROR_MESSAGE);
				}
				else if (count<=length) 	{
					nodes.add(ttemp[count]);
					pathss+=" "+ttemp[count++];
					repaint();
				}
				else {
					JOptionPane.showMessageDialog(null, "最终随机路径为:"+pathss);
				}
			}
		});
        button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				  String path = g.randomWalk();
				    ttemp = path.split("\\s+");
			        length=ttemp.length-1;
			        nodes.clear();
			        nodes.add(ttemp[0]);
			        count=1;//记录当前随机走过的路上的节点
			        pathss=ttemp[0];
			        repaint();
			}
		});
        this.edges=edges;
        this.words=words;
        setTitle("绘制图");
        setBounds(0,0,2000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(button);
        add(button1);
        add(button2);
        setVisible(true);
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D graphics = (Graphics2D) g;  //转化graphics 为Graphics2D对象
        Font font=new Font("宋体",Font.PLAIN,12); // 定义字体对象
        graphics.setFont(font);
        BasicStroke bk=new BasicStroke(10,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL); //自定义笔画对象
        graphics.setStroke(bk);  //设置graphics的笔画对象;
        int n=words.size();
        int RR=400;
        double R=50,P;
        double angle=2*PI/n;
        Coordinate core =new Coordinate(1000,500);
        Coordinate[] coordinates=new Coordinate[n];
        P=RR*sin(angle/2);
        R=R>P?P:R;
        graphics.setColor(Color.RED);
        for(int i=0;i<n;++i)
        {
            coordinates[i]=new Coordinate((RR)*cos(angle*(double)i)+core.x,(RR)*sin(angle*(double)i)+core.y);
            g.drawOval((int)(coordinates[i].x-R),(int)(coordinates[i].y-R),(int)(2*R),(int)(2*R));

        }
        graphics.setColor(Color.BLACK);
        for(int i=0;i<n;++i)
        {
            graphics.drawString(words.get(i),(int)((RR+R)*cos(angle*(double)i)+core.x),(int)((RR+R)*sin(angle*(double)i)+core.y));
        }
        graphics.setStroke(new BasicStroke(3));
        AL al=new AL();
        graphics.setColor(Color.BLUE);
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(edges[i][j]>0)
                {
                    al.drawAL((int)coordinates[i].x,(int)coordinates[i].y,(int)(coordinates[i].x+coordinates[j].x*3)/4,(int)(coordinates[i].y+coordinates[j].y*3)/4,graphics);
                    graphics.drawLine((int)(coordinates[i].x+coordinates[j].x*3)/4,(int)(coordinates[i].y+coordinates[j].y*3)/4,(int)coordinates[j].x,(int)coordinates[j].y);
                }
            }
        }
        graphics.setColor(Color.DARK_GRAY);
        graphics.setStroke(new BasicStroke(15));
        for(int i=0;i<n;i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (edges[i][j] > 0)
                {
                    graphics.drawString("" + edges[i][j], (int) (coordinates[i].x*3 + coordinates[j].x) / 4, (int) (coordinates[i].y*3 + coordinates[j].y) / 4);
                }
            }
        }
        
        
        
        graphics.setColor(Color.YELLOW);
        graphics.setStroke(new BasicStroke(3));
        ArrayList<Integer> p=new ArrayList<Integer>();
        for(int i=0;i<nodes.size();++i)
        {
            for(int j=0;j<words.size();++j)
            {
                if(words.get(j).equals(nodes.get(i)))
                {
                    p.add(j);
                    break;
                }
            }
        }
        for(int i=1;i<p.size();++i)
        {
            al.drawAL((int)coordinates[p.get(i-1)].x,(int)coordinates[p.get(i-1)].y,(int)(coordinates[p.get(i-1)].x+coordinates[p.get(i)].x*3)/4,(int)(coordinates[p.get(i-1)].y+coordinates[p.get(i)].y*3)/4,graphics);
            graphics.drawLine((int)(coordinates[p.get(i-1)].x+coordinates[p.get(i)].x*3)/4,(int)(coordinates[p.get(i-1)].y+coordinates[p.get(i)].y*3)/4,(int)coordinates[p.get(i)].x,(int)coordinates[p.get(i)].y);
        }
        
        
        
        graphics.setColor(Color.DARK_GRAY);
        graphics.setStroke(new BasicStroke(15));
        for(int i=1;i<p.size();++i)
        {
            graphics.drawString("" + edges[p.get(i-1)][p.get(i)], (int) (coordinates[p.get(i-1)].x*3 + coordinates[p.get(i)].x) / 4, (int) (coordinates[p.get(i-1)].y*3 + coordinates[p.get(i)].y) / 4);
        }
    }
}