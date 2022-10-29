<<<<<<< HEAD
package application;
=======
>>>>>>> 7af1e57fa7e91971d8b3acaecf989a2e459630c9

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

 
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
 
 public static void main(String[] args) {
        launch(args);
    }
}
