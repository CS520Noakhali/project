import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.VLineTo;
import javafx.util.Duration;

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
    
    @FXML
    private ImageView droneImage;


    @FXML 
    private TextField heightTextField;

    @FXML
    private Button saveItemInfoButton;


    int x_final_coord, y_final_coord = 0;


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
    
    // Get currently selected directory TreeItem 
   TreeItem<ItemComponent> selectedTreeItem = treeView.getSelectionModel().getSelectedItem();
   // Get the Item corresponding to the current directory TreeItem
   selectedTreeItem.getValue().setName(nameTextField.getText());
   selectedTreeItem.getValue().setHeight(Integer.parseInt(heightTextField.getText()));
   selectedTreeItem.getValue().setLength(Integer.parseInt(lengthTextField.getText()));
   selectedTreeItem.getValue().setWidth(Integer.parseInt(widthTextField.getText()));
   selectedTreeItem.getValue().setXcoordinate(Integer.parseInt(xCoordTextField.getText()));
   selectedTreeItem.getValue().setYcoordinate(Integer.parseInt(yCoordTextField.getText()));
   selectedTreeItem.getValue().setPrice(Integer.parseInt(priceTextField.getText()));

   
   

   


}

@FXML
void onSaveItemInfoButtonClick(ActionEvent event) {

}

@FXML

void onSaveItemInfoButtonClick(ActionEvent event) {
    
    TreeItem<ItemComponent> selectedTreeItem = treeView.getSelectionModel().getSelectedItem();
    selectedTreeItem.getValue().setName(nameTextField.getText());
    selectedTreeItem.getValue().setHeight(Integer.parseInt(heightTextField.getText()));
    selectedTreeItem.getValue().setLength(Integer.parseInt(lengthTextField.getText()));
    selectedTreeItem.getValue().setWidth(Integer.parseInt(widthTextField.getText()));
    selectedTreeItem.getValue().setXcoordinate(Integer.parseInt(xCoordTextField.getText()));
    selectedTreeItem.getValue().setYcoordinate(Integer.parseInt(yCoordTextField.getText()));
    selectedTreeItem.getValue().setPrice(Integer.parseInt(priceTextField.getText()));
 
    
void onReturnHomeButtonClick() {
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

@FXML
void onScanFarmButtonClick() {
    int[][] coordinates = {{0, 700}, {100, 0}, {0, -700}, {100, 0}, {0, 700}, {100, 0}, {0, -700}, {100, 0}, {0, 700}, {100, 0}, {0, -700}
    };

    SequentialTransition master = new SequentialTransition();

    ArrayList<TranslateTransition> lst1 = new ArrayList<>();
    for (int i = 0;i<coordinates.length;i++){
    TranslateTransition translate = new TranslateTransition();
    translate.setNode(droneImage);
    int[] arr = coordinates[i];
    int x = arr[0];
    int y = arr[1];
    translate.setDuration((Duration.millis(1000)));
    translate.setByX(x);
    translate.setByY(y);
    x_final_coord += x;
    y_final_coord += y;
    lst1.add(translate);
}

for (TranslateTransition t: lst1){
master.getChildren().add(t);
}
master.play();
System.out.println("take-off");
}

@FXML

void onVisitItemButtonClick() {
    int x = Integer.parseInt(xCoordTextField.getText());
    int y = Integer.parseInt(yCoordTextField.getText());

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
void selectItem(MouseEvent event) {

   // Get currently selected directory TreeItem 
   TreeItem<ItemComponent> selectedTreeItem = treeView.getSelectionModel().getSelectedItem();
   // Get the Item corresponding to the current directory TreeItem and set it in the textFields
   nameTextField.setText(selectedTreeItem.getValue().getName());
   lengthTextField.setText(Integer.toString(selectedTreeItem.getValue().getLength()));
   priceTextField.setText(Integer.toString(selectedTreeItem.getValue().getPrice()));
   widthTextField.setText(Integer.toString(selectedTreeItem.getValue().getWidth()));
   xCoordTextField.setText(Integer.toString(selectedTreeItem.getValue().getXcoordinate()));
   yCoordTextField.setText(Integer.toString(selectedTreeItem.getValue().getYcoordinate()));
   heightTextField.setText(Integer.toString(selectedTreeItem.getValue().getHeight()));
   


   /* selectedItem */

}
    

}
