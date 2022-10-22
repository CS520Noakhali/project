import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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


    /* 
    Initialize method: TreeView is created
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    // *********  Set up tree view *******************

        // a. Create root, command center and drone ItemComponents. 
        ItemComponent rootIC = new ItemContainer("root", 0, 0, 0, 0, 0, 0);
        ItemComponent commCenterIC = new ItemContainer("command center", 0, 0, 0, 0, 0, 0);
        ItemComponent droneIC = new Drone("drone", 0, 0, 0, 0, 0, 0);
        rootIC.add(commCenterIC);
        commCenterIC.add(droneIC);
        
        // b. Create corresponding TreeItems 
        TreeItem<ItemComponent> root = new TreeItem<>(rootIC);
        TreeItem<ItemComponent> commandCenter = new TreeItem<>(commCenterIC);
        TreeItem<ItemComponent> drone = new TreeItem<>(droneIC);

        // b. add root, command center and drone to the TreeView
        root.setExpanded(true);
        root.getChildren().add(commandCenter);
        commandCenter.getChildren().add(drone);
        commandCenter.setExpanded(true);
        treeView.setRoot(root);

    }

/* 
ADD ITEM functionality 
 */
@FXML
void onAddItemButtonClick(ActionEvent event) {

    // a. First, create new Item and corresponding TreeItem
    ItemComponent newItem =  new Item("New Item", 0, 0, 0, 0, 0, 0);
    TreeItem<ItemComponent> newTreeItem = new TreeItem<>(newItem);

    // b. Next, we will update TreeView 

    // Get currently selected directory TreeItem
    TreeItem<ItemComponent> selectedTreeItem = treeView.getSelectionModel().getSelectedItem();
    // Add new item to the selected TreeItem
    selectedTreeItem.getChildren().add(newTreeItem);
    newTreeItem.setExpanded(true);
    treeView.getSelectionModel().select(newTreeItem);

    // c. Then, we will update our Composite pattern structure

    // Get the Item Container corresponding to the current directory TreeItem
    ItemContainer selectedItemContainer = (ItemContainer)selectedTreeItem.getValue();
    // Add item to the item container
    selectedItemContainer.add(newItem);
}

/* 
ADD ITEM CONTAINER functionality
 */
@FXML
void onAddItemContainerButtonClick(ActionEvent event) {

        // a. First, create new ItemContainer and corresponding TreeItem
        ItemComponent newItemContainer =  new ItemContainer("New Item Container", 0, 0, 0, 0, 0, 0);
        TreeItem<ItemComponent> newTreeItemContainer = new TreeItem<>(newItemContainer);
    
        // b. Next, we will update TreeView 
    
        // Get currently selected directory TreeItem
        TreeItem<ItemComponent> selectedTreeItem = treeView.getSelectionModel().getSelectedItem();
        // Add new item to the selected TreeItem
        selectedTreeItem.getChildren().add(newTreeItemContainer);
        newTreeItemContainer.setExpanded(true);
        treeView.getSelectionModel().select(newTreeItemContainer);
    
        // c. Then, we will update our Composite pattern structure
    
        // Get the Item Container corresponding to the current directory TreeItem
        ItemContainer selectedItemContainer = (ItemContainer)selectedTreeItem.getValue();
        // Add item to the item container
        selectedItemContainer.add(newItemContainer);

}

/*
 DELETE ITEM functionality
 */
@FXML
void onDeleteItemButtonClick(ActionEvent event) {

    // Get currently selected directory TreeItem 
    TreeItem<ItemComponent> selectedTreeItem = treeView.getSelectionModel().getSelectedItem();
    // Get the Item corresponding to the current directory TreeItem
    ItemComponent selectedItem = selectedTreeItem.getValue();

    // Get the parent of the selected TreeItem
    TreeItem<ItemComponent> parentTreeItem = selectedTreeItem.getParent();
    // Get the Item corresponding to the parent
    ItemComponent parentItem = parentTreeItem.getValue();

    // Delete from TreeView structure
    parentTreeItem.getChildren().remove(selectedTreeItem);
    
    // Delete item from our Composite pattern structure
    parentItem.delete(selectedItem);

}

@FXML
void onEditItemInfoButtonClick(ActionEvent event) {

}

@FXML
void onReturnHomeButtonClick(ActionEvent event) {

}

@FXML
void onSaveItemInfoButtonClick(ActionEvent event) {

}

@FXML
void onScanFarmButtonClick(ActionEvent event) {

}

@FXML
void onVisitItemButtonClick(ActionEvent event) {

}


@FXML
void selectItem(MouseEvent event) {

}
    

}
