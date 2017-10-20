package com.company;

import java.awt.Graphics2D;

/*
 * @author:
 * @param:
 * @return:
 */

public class AL {
    public static void drawAL(final int sx, final int sy, final int ex, final int ey, final Graphics2D g2) {
	final double H = 6; //
	final double L = 6; //
	int x3 = 0;
	int y3 = 0;
	int x4 = 0;
	int y4 = 0;
	final double awrad = Math.atan(L / H); //
	final double arraow_len = Math.sqrt(L * L + H * H); //
	final double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
	final double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
	final double x_3 = ex - 2 * arrXY_1[0]; // (x3,y3)
	final double y_3 = ey - 2 * arrXY_1[1];
	final double x_4 = ex - 2 * arrXY_2[0]; // (x4,y4)
	final double y_4 = ey - 2 * arrXY_2[1];
	final Double X3 = new Double(x_3);
	x3 = X3.intValue();
	final Double Y3 = new Double(y_3);
	y3 = Y3.intValue();
	final Double X4 = new Double(x_4);
	x4 = X4.intValue();
	final Double Y4 = new Double(y_4);
	y4 = Y4.intValue();
	// g.setColor(SWT.COLOR_WHITE);
	//
	g2.drawLine(sx, sy, ex, ey);
	//
	g2.drawLine((ex), (ey), x3, y3);
	//
	g2.drawLine((ex), (ey), x4, y4);
    }

    /*
     * @author:
     * @param:
     * @return:
     */

    public static double[] rotateVec(final int px, final int py, final double ang, final boolean isChLen,
            final double newLen) {
	final double mathstr[] = new double[2];
	double vx = px * Math.cos(ang) - py * Math.sin(ang);
	double vy = px * Math.sin(ang) + py * Math.cos(ang);
	if (isChLen) {
	    final double d = Math.sqrt(vx * vx + vy * vy);
	    vx = vx / d * newLen;
	    vy = vy / d * newLen;
	    mathstr[0] = vx;
	    mathstr[1] = vy;
	}
	return mathstr;
    }
}
