import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/*
 * Driver class. Runs the application
 */
public class App extends Application  {

    @Override
    public void start(Stage stage) throws Exception {

        // get the singleton instance of DashboardController class
        DashboardController dashboardController = DashboardController.getInstance();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        loader.setController(dashboardController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
 
 public static void main(String[] args) throws IOException, InterruptedException {
        launch(args);
       
		System.exit(0);
    }
}
