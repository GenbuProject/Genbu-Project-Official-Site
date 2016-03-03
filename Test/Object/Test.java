import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class Test extends Applet {
	public void paint(Graphics g){
		g.setColor(Color.red);
		
		Dimension size = getSize();
		g.fillRect(0, 0, size.width - 1, size.height - 1);
	}
}