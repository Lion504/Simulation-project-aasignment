package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Visualisation2 implements IVisualisation {
	private GraphicsContext gc;
	private Canvas cnv;
	
	int customerCount = 0;

	public Visualisation2(Canvas cnv) {
		this.cnv = cnv;
		this.gc = cnv.getGraphicsContext2D();
		clearDisplay();
	}
	

	public void clearDisplay() {
		gc.setFill(Color.YELLOW);
		gc.fillRect(0, 0, cnv.getWidth(), cnv.getHeight());
	}

		
	public void newCustomer() {
		customerCount++;
		
		gc.setFill(Color.YELLOW);
		gc.fillRect(100,80, 140, 20);
		gc.setFill(Color.RED);
		gc.setFont(new Font(20));
		gc.fillText("Customer " + customerCount, 100, 100);
	}
}
