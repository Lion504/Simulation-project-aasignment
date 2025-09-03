package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.PanelsController;

import java.io.IOException;


public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Simulator");

        initRootLayout();
        showPanels();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show the simulation control (and parameters) panel and the animation panel.
     */
    public void showPanels() {
        try {
            // Loads Panels
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Paneelit.fxml"));
            AnchorPane panels = (AnchorPane) loader.load();
            	                   
            // Lay them center of the root view
            rootLayout.setCenter(panels);
            
            // Give the controller access to the main app
            PanelsController controller = loader.getController();
            controller.setMainApp(this);
            controller.initController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Returns the main stage.
	 * @return Stage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
}