package view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import main.MainApp;

/**
 * This class is set using Scene Builder's Controller selection (left part of the main window)
 * Controller class edit box to view.PanelsController
 *
 * Being a controller, this class receives Events from the JavaFX
 */
public class PanelsController {
	@FXML
	private Canvas visu;							// UI Component
	private IVisualisation visualisation = null; 	// work done on this object

	@FXML
	private Button startButton;
	@FXML
	private Button simuloiButton;

	@FXML
	private ChoiceBox<String> simustepsBox;
	@FXML
	private ChoiceBox<String> animationSelBox;
	
	MainApp mainApp;

	public void initController() {
		String st[] = { "50", "100", "200", "500" };
		simustepsBox.setItems(FXCollections.observableArrayList(st));
		simustepsBox.setValue(st[1]);

		String st2[] = { "Balls", "Numbers" };
		animationSelBox.setItems(FXCollections.observableArrayList(st2));
		animationSelBox.setValue(st2[0]);
	}
	
	public void handleStart() {
		System.out.println("Start");
		if (visualisation == null) {
			if (animationSelBox.getValue().equals("Numbers")) {
				visualisation = new Visualisation2(visu);	// Customer info as text
			} else
				visualisation = new Visualisation(visu);	// Customer as a graphical symbol

			visualisation.clearDisplay();
		}
		startButton.setDisable(true);
	}

	public void handleSimulation() {
		new Thread() {
			public void run() {
				for (int i = 1; i <= Integer.valueOf(simustepsBox.getValue()); i++) {
					Platform.runLater(()-> visualisation.newCustomer());
					try {
						sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	 public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;
	 }
}