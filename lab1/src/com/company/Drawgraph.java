package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.*;
import static java.lang.Math.PI;
import static java.lang.Math.sin;
import static java.lang.StrictMath.cos;

public class Drawgraph extends JFrame
{
    ArrayList<String> words;
    int [][] edges;
    String path;
    private JPanel jPanel;
    private Button button;
    private JTextField jTextField;
    public Drawgraph(ArrayList<String> words,int [][] edges,String path,JFrame f)
    {
        super();
        button= new Button("保存并返回");
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
        this.edges=edges;
        this.words=words;
        this.path=path;
        setTitle("绘制图");
        setBounds(0,0,2000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(button);
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
        R=R*3/4;
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
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(edges[i][j]>0)
                {
                   /* al.drawAL((int)coordinates[i].x,(int)coordinates[i].y,(int)(coordinates[i].x+coordinates[j].x*3)/4,(int)(coordinates[i].y+coordinates[j].y*3)/4,graphics);
                    graphics.drawLine((int)(coordinates[i].x+coordinates[j].x*3)/4,(int)(coordinates[i].y+coordinates[j].y*3)/4,(int)coordinates[j].x,(int)coordinates[j].y);*/
                    //graphics.drawLine((int)(coordinates[i].x),(int)(coordinates[i].y),(int)coordinates[j].x,(int)coordinates[j].y);
                    drawdirline(coordinates[i],coordinates[j],graphics);
                }
            }
        }
        graphics.setStroke(new BasicStroke(15));
        graphics.setColor(Color.DARK_GRAY);
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
        if(path!=null&&path!="")
        {
            try {
                BufferedImage image = new BufferedImage(2000, 1000, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = image.createGraphics();
                g2d.setColor(Color.white);
                g2d.fillRect(0, 0, 2000, 1000);
                g2d.setFont(font);
                g2d.setStroke(bk);
                g2d.setColor(Color.RED);
                for (int i = 0; i < n; ++i) {
                    coordinates[i] = new Coordinate((RR) * cos(angle * (double) i) + core.x, (RR) * sin(angle * (double) i) + core.y);
                    g2d.drawOval((int) (coordinates[i].x - R), (int) (coordinates[i].y - R), (int) (2 * R), (int) (2 * R));

                }
                g2d.setColor(Color.BLACK);
                for (int i = 0; i < n; ++i) {
                    g2d.drawString(words.get(i), (int) ((RR + R) * cos(angle * (double) i) + core.x), (int) ((RR + R) * sin(angle * (double) i) + core.y));
                }
                g2d.setStroke(new BasicStroke(3));
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (edges[i][j] > 0) {
                            /*al.drawAL((int) coordinates[i].x, (int) coordinates[i].y, (int) (coordinates[i].x + coordinates[j].x * 3) / 4, (int) (coordinates[i].y + coordinates[j].y * 3) / 4, g2d);
                            g2d.drawLine((int) (coordinates[i].x + coordinates[j].x * 3) / 4, (int) (coordinates[i].y + coordinates[j].y * 3) / 4, (int) coordinates[j].x, (int) coordinates[j].y);*/
                            drawdirline(coordinates[i],coordinates[j],g2d);
                        }
                    }
                }
                graphics.setStroke(new BasicStroke(15));
                g2d.setColor(Color.DARK_GRAY);
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (edges[i][j] > 0) {
                            g2d.drawString("" + edges[i][j], (int) (coordinates[i].x * 3 + coordinates[j].x) / 4, (int) (coordinates[i].y * 3 + coordinates[j].y) / 4);
                        }
                    }
                }

                g2d.dispose();
                ImageIO.write(image, "png", new File(path + ".png"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


}