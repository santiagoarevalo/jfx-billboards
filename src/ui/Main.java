package ui;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InfrastructureDepartment;

public class Main extends Application {
	
	private BillboardGUI billboardGUI;
	private InfrastructureDepartment infrastructureDepartment;
	
	public Main() throws IOException, ClassNotFoundException {
		infrastructureDepartment = new InfrastructureDepartment();
		billboardGUI = new BillboardGUI(infrastructureDepartment);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPanel.fxml"));

		fxmlLoader.setController(billboardGUI);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Infrastructure Department");
		primaryStage.show();
		
	}

}
