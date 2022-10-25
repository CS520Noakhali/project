package drone_animation;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Control the drone flying.
 */
public class DroneController implements Initializable {
    @FXML
    private ComboBox<String> object_list;

    @FXML
    private TextField X_coord;

    @FXML
    private TextField Y_coord;

    @FXML
    private Button drone_fly_button;

    @FXML
    private Button return_origin;

    @FXML
    private Circle droneImage;

    private int x_final_coord = 0;
    private int y_final_coord = 0;

    @FXML
    /**
     * Set drone to designed x, y coordinate on the pane.
     */
    protected void onFlyButtonClick() {
        int x = Integer.parseInt(X_coord.getText());
        int y = Integer.parseInt(Y_coord.getText());

        //translate
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(droneImage);
        translate.setDuration(Duration.millis(500));
        translate.setByX(x);
        translate.setByY(y);
        translate.play();

        x_final_coord += x;
        y_final_coord += y;

        System.out.println("x coord: " + x);
        System.out.println("y coord: " + y);
        System.out.println("Flying!");
    }

    @FXML
    /**
     * Set drone back to origin (0, 0)
     */
    protected void onReturnOriginButtonClick() {
        //translate
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(droneImage);
        translate.setDuration(Duration.millis(1));
        translate.setByX(x_final_coord*(-1));
        translate.setByY(y_final_coord*(-1));

        translate.play();
        x_final_coord = 0;
        y_final_coord = 0;

        System.out.println("return to origin!");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}