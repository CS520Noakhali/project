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


    // Text Fields contining information about items
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


    // Method that shows item information pane. Use item getter methods.
    public void showItemComponent(ItemComponent itemComponent) {
	

    }	


    // Method that updates item information pane. Use item setter methods.
    public void updateItemComponent(ItemComponent itemComponent) {
	

    }	



    // Initialize method: TreeView is created, Context menus are created
    @Override
    public void initialize(URL location, ResourceBundle resources) {

// ********* 1. Set up tree view *******************

        // a. Create root, command center and drone ItemComponents
        TreeItem<ItemComponent> root = new TreeItem<>(new ItemContainer("root", 0, 0, 0, 0, 0, 0));
        TreeItem<ItemComponent> commandCenter = new TreeItem<>(new ItemContainer("command center", 0, 0, 0, 0, 0, 0));
        TreeItem<ItemComponent> drone = new TreeItem<>(new Drone("drone", 0, 0, 0, 0, 0, 0));

        // b. add root, command center and drone to the TreeView
        root.setExpanded(true);
        root.getChildren().addAll(commandCenter);
        commandCenter.getChildren().add(drone);
        commandCenter.setExpanded(true);
        treeView.setRoot(root);

    }

// ******** 2. Set up context menus **********************

	// a. Context menu for items: has only "delete" option
	ContextMenu itemContextMenu = new ContextMenu();
	MenuItem itemDelete = new MenuItem("Delete");

	// set itemDelete event. Need to implement
        itemDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Delete item is selected");
		// need to implement
            }
        });

	itemContextMenu.getItems.add(itemDelete);
	
	// b. Context menu for item containers: has "add item", "add item container" and "delete" options
	ContextMenu itemContainerContextMenu = new ContextMenu();
	MenuItem itemContainerDelete = new MenuItem("Delete");

	itemContainerDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Delete item container is selected");
		// need to implement
            }
        });

	MenuItem addItem = new MenuItem("Add Item");

	addItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Add item is selected");
		// need to implement
            }
        });

	MenuItem addItemContainer = new MenuItem("Add Item Container");

	addItemContainer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Add item container is selected");
		// need to implement
            }
        });

	itemContainerContextMenu.getItems.addAll(itemContainerDelete, addItem, addItemContainer);

	// c. Set contextMenus to the treeView. If clicked at the Item object, then itemContextMenu
	// should appear. It clicked at the ItemContainer object, ItemContainerContextMenu should appear.

	// Need to implement


// ******** 3. 
    

    

}
