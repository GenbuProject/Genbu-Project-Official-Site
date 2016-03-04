import java.applet.Applet;
import java.awt.Event;
import java.awt.Graphics;

public class Test extends Applet {
	public void paint(Graphics Canvas) {
		Canvas.drawLine(0, 50, 100, 50);
		Canvas.drawLine(0, 150, 100, 150);
		Canvas.drawLine(0, 50, 0, 150);
		Canvas.drawLine(100, 50, 100, 150);

		Canvas.drawLine(100, 0, 200, 0);
		Canvas.drawLine(0, 50, 100, 0);
		Canvas.drawLine(100, 50, 200, 0);

		Canvas.drawLine(100, 150, 200, 100);
		Canvas.drawLine(200, 0, 200, 100);
	}

	@Override
	public boolean mouseDown(Event Mouse, int X, int Y) {
		Graphics Canvas = getGraphics();
			Canvas.drawLine(0 + X, 50 + Y, 100 + X, 50 + Y);
			Canvas.drawLine(0 + X, 150 + Y, 100 + X, 150 + Y);
			Canvas.drawLine(0 + X, 50 + Y, 0 + X, 150 + Y);
			Canvas.drawLine(100 + X, 50 + Y, 100 + X, 150 + Y);

			Canvas.drawLine(100 + X, 0 + Y, 200 + X, 0 + Y);
			Canvas.drawLine(0 + X, 50 + Y, 100 + X, 0 + Y);
			Canvas.drawLine(100 + X, 50 + Y, 200 + X, 0 + Y);

			Canvas.drawLine(100 + X, 150 + Y, 200 + X, 100 + Y);
			Canvas.drawLine(200 + X, 0 + Y, 200 + X, 100 + Y);

		return true;
	}

	@Override
	public boolean mouseDrag(Event Mouse, int X, int Y) {
		Graphics Canvas = getGraphics();
			Canvas.drawLine(0 + X, 50 + Y, 100 + X, 50 + Y);
			Canvas.drawLine(0 + X, 150 + Y, 100 + X, 150 + Y);
			Canvas.drawLine(0 + X, 50 + Y, 0 + X, 150 + Y);
			Canvas.drawLine(100 + X, 50 + Y, 100 + X, 150 + Y);

			Canvas.drawLine(100 + X, 0 + Y, 200 + X, 0 + Y);
			Canvas.drawLine(0 + X, 50 + Y, 100 + X, 0 + Y);
			Canvas.drawLine(100 + X, 50 + Y, 200 + X, 0 + Y);

			Canvas.drawLine(100 + X, 150 + Y, 200 + X, 100 + Y);
			Canvas.drawLine(200 + X, 0 + Y, 200 + X, 100 + Y);

		return true;
	}
}