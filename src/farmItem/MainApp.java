package farmItem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Ranch Dashboard");

		initRootLayout();

		showOverview();
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {

		rootLayout = Dashboard.get_instance()._rootLayout;// creating the instance
		Scene scene = Dashboard.get_instance().dashboard;
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**
	 * Shows the overview inside the root layout.
	 */
	public void showOverview() {
		try {
			// Load overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("Overview.fxml"));
			AnchorPane Overview = (AnchorPane) loader.load();

			// Set overview into the center of root layout.
			rootLayout.setTop(Overview);

//			 Give the controller access to the main app.
//            OverviewController controller = loader.getController();
//            controller.setMainApp(this);            

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

}