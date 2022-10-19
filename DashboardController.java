import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

public class DashboardController implements Initializable{

    //TreeView to implement directory of items stored at the farm
    @FXML
    private TreeView<ItemComponent> treeView;

    @FXML
    private TextField lengthTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField widthTextField;

    @FXML
    private TextField xCoordTextField;

    @FXML
    private TextField yCoordTextField;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

// ********* 1. Set up tree view *******************

        //Create root, command center and drone ItemComponents
        TreeItem<ItemComponent> root = new TreeItem<>(new ItemContainer("root", 0, 0, 0, 0, 0, 0));
        TreeItem<ItemComponent> commandCenter = new TreeItem<>(new ItemContainer("command center", 0, 0, 0, 0, 0, 0));
        TreeItem<ItemComponent> drone = new TreeItem<>(new Item("drone", 0, 0, 0, 0, 0, 0));

        // add root, command center and drone to the TreeView
        root.setExpanded(true);
        root.getChildren().addAll(commandCenter);
        commandCenter.getChildren().add(drone);
        commandCenter.setExpanded(true);
        treeView.setRoot(root);

    }

// ******** 2. Set up context menus **********************

// ******** 3. Set up 
    

    @FXML
    void selectItem(MouseEvent event) {
        

    }

    @FXML
    void showContextMenu(ContextMenuEvent event) {
        

    }

    

}
