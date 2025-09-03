package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Visualisation implements IVisualisation {
	private double i = 0;
	private double j = 10;
	
	private GraphicsContext gc;
	private Canvas cnv;
	
	public Visualisation(Canvas cnv) {
		this.cnv = cnv;
		this.gc = cnv.getGraphicsContext2D();
		clearDisplay();
	}
	

	public void clearDisplay() {
		gc.setFill(Color.YELLOW);
		gc.fillRect(0, 0, cnv.getWidth(), cnv.getHeight());
	}
	
	public void newCustomer() {
		gc.setFill(Color.RED);
		gc.fillOval(i,j,10,10);
		
		i = i + 10;
		if (i >= cnv.getWidth()) { i = 0; j+=10; }
	}
}
