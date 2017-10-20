package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.PI;
import static java.lang.Math.sin;
import static java.lang.StrictMath.cos;

public class DrawpathAll extends JFrame
{
    ArrayList<String> words;
    int [][] edges;
    String[][] nodes;
    private Button button;
    private int pathNum;
    private String[] paths;
    public DrawpathAll(ArrayList<String> words,int [][] edges,String[] paths,JFrame f,int[] pathlength)
    {
        super();
        this.edges=edges;
        this.pathNum=paths.length;
        int y=90;
        if (pathlength.length==1) {//对于1对1最短路径的情况
        	 for(int i = 0 ;i<pathNum;i++){
		        	if(paths[i]==null) continue;
		        	JLabel jLabel = new JLabel(paths[i]+":::length = "+pathlength[0]);
		            jLabel.setBounds(150,y+15*i, 400, 30);
		            add(jLabel);
		        }
		}else{//对于1对n最短路径的情况
			 for(int i = 0 ;i<pathNum;i++){
		        	if(paths[i]==null) continue;
		        	JLabel jLabel = new JLabel(paths[i]+":::length = "+pathlength[i]);
		            jLabel.setBounds(150,y+15*i, 400, 30);
		            add(jLabel);
		        }
		}
        nodes=new String[pathNum][];
        this.paths=paths;
        this.words=words;
        button= new Button("返回");
        JPanel jPanel=(JPanel)this.getContentPane();
        jPanel.setLayout(null);
        button.setBounds(1600, 800, 100, 30);
        button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.setVisible(true);
				dispose();
			}
		});
        add(button);
        for(int i=0;i<paths.length;i++)
        {
        	if (paths[i]==null) continue;
        	paths[i].toLowerCase();
        	nodes[i]=paths[i].split("\\s+");
        }
        setTitle("绘制路径");
        setBounds(0,0,2000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
   public void drawdirline(Coordinate i, Coordinate j,Graphics graphics)
    {
        double rate= ((double)2/(double)3) * (new Random().nextDouble());
        rate=0.25;
        graphics.setColor(Color.BLUE);
        graphics.drawLine((int)i.x,(int)i.y,(int)((1-rate)*i.x+rate*j.x),(int)((1-rate)*i.y+rate*j.y));
        graphics.setColor(Color.YELLOW);
        graphics.drawLine((int)((1-rate)*i.x+rate*j.x),(int)((1-rate)*i.y+rate*j.y),(int)j.x,(int)j.y);
    }
    public void drawdirline2(Coordinate i, Coordinate j,Graphics graphics)
    {
        double rate= ((double)2/(double)3) * (new Random().nextDouble());
        rate=0.25;
        graphics.setColor(Color.BLACK);
        graphics.drawLine((int)i.x,(int)i.y,(int)((1-rate)*i.x+rate*j.x),(int)((1-rate)*i.y+rate*j.y));
        graphics.setColor(Color.BLACK);
        graphics.drawLine((int)((1-rate)*i.x+rate*j.x),(int)((1-rate)*i.y+rate*j.y),(int)j.x,(int)j.y);
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
                    //al.drawAL((int)coordinates[i].x,(int)coordinates[i].y,(int)(coordinates[i].x+coordinates[j].x*3)/4,(int)(coordinates[i].y+coordinates[j].y*3)/4,graphics);
                    //graphics.drawLine((int)(coordinates[i].x+coordinates[j].x*3)/4,(int)(coordinates[i].y+coordinates[j].y*3)/4,(int)coordinates[j].x,(int)coordinates[j].y);
                    drawdirline(coordinates[i],coordinates[j],graphics);
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
        
        
        for(int k=0;k<pathNum;k++){
        	if (paths[k]==null) continue;
            graphics.setColor(Color.YELLOW);
            graphics.setStroke(new BasicStroke(3));
        	ArrayList<Integer> p=new ArrayList<Integer>();
            for(int i=0;i<nodes[k].length;++i)
            {
                for(int j=0;j<words.size();++j)
                {
                    if(words.get(j).equals(nodes[k][i]))
                    {
                        p.add(j);
                        break;
                    }
                }
            }
            for(int i=1;i<p.size();++i)
            {
                //al.drawAL((int)coordinates[p.get(i-1)].x,(int)coordinates[p.get(i-1)].y,(int)(coordinates[p.get(i-1)].x+coordinates[p.get(i)].x*3)/4,(int)(coordinates[p.get(i-1)].y+coordinates[p.get(i)].y*3)/4,graphics);
                //graphics.drawLine((int)(coordinates[p.get(i-1)].x+coordinates[p.get(i)].x*3)/4,(int)(coordinates[p.get(i-1)].y+coordinates[p.get(i)].y*3)/4,(int)coordinates[p.get(i)].x,(int)coordinates[p.get(i)].y);
                drawdirline2(coordinates[p.get(i-1)],coordinates[p.get(i)],graphics);
            }
            graphics.setColor(Color.DARK_GRAY);
            graphics.setStroke(new BasicStroke(15));
            for(int i=1;i<p.size();++i)
            {
                graphics.drawString("" + edges[p.get(i-1)][p.get(i)], (int) (coordinates[p.get(i-1)].x*3 + coordinates[p.get(i)].x) / 4, (int) (coordinates[p.get(i-1)].y*3 + coordinates[p.get(i)].y) / 4);
            }
        }
    }

}
