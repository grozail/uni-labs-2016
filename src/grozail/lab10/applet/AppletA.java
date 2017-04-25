package grozail.lab10.applet;


import java.applet.Applet;
import java.awt.*;
/*
<applet code="AppletA" width=200 height=60>
fuck this deprecated shit
</applet>
 */
public class AppletA extends Applet {
	public void paint(Graphics g) {
		g.drawString("На*** аплеты, это говно не запускается", 10, 10);
	}
}