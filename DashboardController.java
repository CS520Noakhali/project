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

    // private static instance of DashboardController class. Using lazy singleton
    private static DashboardController dashboardController = null;

    // private constructor
    private DashboardController() {

    }

    // public methods that allows other classes to get singleton instance of DashboardController.
    // Use lazy singleton model
    public static DashboardController getInstance() {
    if (dashboardController == null) {
        dashboardController = new DashboardController();
    }
    return dashboardController;
    }

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
    private  TextField heightTextField;

    //GLOBAL VALUES
    int X_FINAL_COORD, Y_FINAL_COORD = 0;
    
    int X_COORD_LIMIT_POSITIVE = 500;
    int X_COORD_LIMIT_NEGATIVE = 0;
    int Y_COORD_LIMIT_POSITIVE = 700;
    int Y_COORD_LIMIT_NEGATIVE = 0;


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
void onSaveItemInfoButtonClick(ActionEvent event) {
    TreeItem<ItemComponent> selectedTreeItem = treeView.getSelectionModel().getSelectedItem();
    selectedTreeItem.getValue().setName(nameTextField.getText());
    selectedTreeItem.getValue().setHeight(Integer.parseInt(heightTextField.getText()));
    selectedTreeItem.getValue().setLength(Integer.parseInt(lengthTextField.getText()));
    selectedTreeItem.getValue().setWidth(Integer.parseInt(widthTextField.getText()));
    selectedTreeItem.getValue().setXcoordinate(Integer.parseInt(xCoordTextField.getText()));
    selectedTreeItem.getValue().setYcoordinate(Integer.parseInt(yCoordTextField.getText()));
    selectedTreeItem.getValue().setPrice(Integer.parseInt(priceTextField.getText()));
    treeView.refresh();
}

@FXML

void onReturnHomeButtonClick() {
    TranslateTransition translate = new TranslateTransition();
    X_FINAL_COORD = 0;
    Y_FINAL_COORD = 0;

    translate.setNode(droneImage);
    translate.setDuration(Duration.millis(500));
    translate.setToX(X_FINAL_COORD);
    translate.setToY(Y_FINAL_COORD);
    
    translate.play();
    System.out.println("return to origin!");
}

@FXML
void onScanFarmButtonClick() {

    X_FINAL_COORD = 0;
    Y_FINAL_COORD = 0;

    int[][] coordinates = {
        {0, 700}, 
        {100, 0},
        {0, -700},
        {100, 0},
        {0, 700},
        {100, 0},
        {0, -700},
        {100, 0},
        {0, 700},
        {100, 0},
        {0, -700}
    };

    SequentialTransition master = new SequentialTransition();

    ArrayList<TranslateTransition> lst1 = new ArrayList<>();

    TranslateTransition initiate = new TranslateTransition();
    initiate.setNode(droneImage);
    initiate.setDuration(Duration.millis(500));
    initiate.setToX(0);
    initiate.setToY(0);
    lst1.add(initiate);

    for (int i = 0;i<coordinates.length;i++){
        TranslateTransition translate = new TranslateTransition();
        
        int[] arr = coordinates[i];
        int x = arr[0];
        int y = arr[1];
        
         

        translate.setNode(droneImage);
        translate.setDuration((Duration.millis(1000)));
        translate.setByX(x);
        translate.setByY(y);
        
        X_FINAL_COORD += x;
        Y_FINAL_COORD += y;
        
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

    //if the users input is more that the limit parametr (in our case for x it will be 600 and for y : 700)
    //the drone will stop at the border

    if (x > X_COORD_LIMIT_POSITIVE) {
        x = X_COORD_LIMIT_POSITIVE;
        xCoordTextField.setText(Integer.toString(x));
    }
    else if (x < X_COORD_LIMIT_NEGATIVE){
        x = X_COORD_LIMIT_NEGATIVE;
        xCoordTextField.setText(Integer.toString(x));
    }
        
    if (y > Y_COORD_LIMIT_POSITIVE) {
        y = Y_COORD_LIMIT_POSITIVE;
        yCoordTextField.setText(Integer.toString(y));
    }
    else if (y < Y_COORD_LIMIT_NEGATIVE){
        y = Y_COORD_LIMIT_NEGATIVE;
        yCoordTextField.setText(Integer.toString(y));
    }
    //translate
    TranslateTransition translate = new TranslateTransition();
    translate.setNode(droneImage);
    translate.setDuration(Duration.millis(500));
    translate.setToX(x);
    translate.setToY(y);
    translate.play();

    X_FINAL_COORD = x;
    Y_FINAL_COORD = y;

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
   
}
    


}