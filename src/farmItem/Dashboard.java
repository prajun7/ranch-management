package farmItem;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;

public class Dashboard {

	private BorderPane rootLayout;
	// static(it need to be available on the global basis) variable single-scene
	
	private static Dashboard single_scene = null;
	public Scene dashboard;
	public BorderPane _rootLayout;

	// Private Constructor to have only one instance of it.
	private Dashboard() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
		try {
			rootLayout = (BorderPane) loader.load();
			_rootLayout = rootLayout;
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(rootLayout);
		dashboard = scene;

	}

	public static Dashboard get_instance() {
		// Only way to create the object
		if (single_scene == null)
			single_scene = new Dashboard();

		return single_scene;
	}

}
