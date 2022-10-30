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
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;


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
    
    @FXML
    private Pane farm_pane;

    //GLOBAL VALUES
    int X_FINAL_COORD, Y_FINAL_COORD = 0;
    
    int X_COORD_LIMIT_POSITIVE = 500;
    int X_COORD_LIMIT_NEGATIVE = 0;
    int Y_COORD_LIMIT_POSITIVE = 700;
    int Y_COORD_LIMIT_NEGATIVE = 0;

    /* 
    Initialize method: TreeView is created
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    // *********  Set up tree view *******************
    	ImageView dummy = null;
    	
		
		 Image droneimage=  new Image(getClass().getResourceAsStream("drone.png")); 
		 ImageView drone_view= new ImageView(droneimage);
		 
		 Image com_centre_image = new Image(getClass().getResourceAsStream("comm_center.png"));
		 ImageView com_centre_view= new ImageView(com_centre_image);

        // a. Create root, command center and drone ItemComponents. 
        ItemComponent rootIC = new ItemContainer("root", 0, 0, 0, 0, 0, 0, dummy);
        ItemComponent commCenterIC = new ItemContainer("command center", 0, 400, 400, 0, 60,60, com_centre_view);
        ItemComponent droneIC = new Drone("drone", 0, 400,	400, 0,  60, 60,drone_view);
        rootIC.add(commCenterIC);
        commCenterIC.add(droneIC);
        
        // b. Create corresponding TreeItems 
        TreeItem<ItemComponent> root = new TreeItem<>(rootIC);
        TreeItem<ItemComponent> commandCenter = new TreeItem<>(commCenterIC);
        TreeItem<ItemComponent> drone = new TreeItem<>(droneIC);

        // c. add root, command center and drone to the TreeView
        root.setExpanded(true);
        root.getChildren().add(commandCenter);
        commandCenter.getChildren().add(drone);
        commandCenter.setExpanded(true);
        treeView.setRoot(root);
        
        
        // d. set drone on pane
          
        droneIC.getImageView().setX(droneIC.getXcoordinate());
        droneIC.getImageView().setY(droneIC.getYcoordinate());
        droneIC.getImageView().setFitHeight(droneIC.getHeight());
        droneIC.getImageView().setFitWidth(droneIC.getWidth());
        farm_pane.getChildren().add(droneIC.getImageView());
        
        
        
        
        
     // e. set command center on pane
        
        commCenterIC.getImageView().setX(commCenterIC.getXcoordinate());
        commCenterIC.getImageView().setY(commCenterIC.getYcoordinate());
        commCenterIC.getImageView().setFitHeight(commCenterIC.getHeight());
        commCenterIC.getImageView().setFitWidth(commCenterIC.getWidth());
        commCenterIC.getImageView().setOpacity(0.5);
        farm_pane.getChildren().add(commCenterIC.getImageView());
        

    }

/* 
ADD ITEM functionality 
 */
@FXML
void onAddItemButtonClick(ActionEvent event) {

	
	int price, x,y, length, width, height;
	price=length =0;
	x=y=100;
	width=height=20;
	
	
	
	
    Image image=  new Image(getClass().getResourceAsStream("testitem.png")); 
    ImageView testview1= new ImageView(image);
	
	
    // a. First, create new Item and corresponding TreeItem
    ItemComponent newItem =  new Item("New item", price, x, y, length, width, height,testview1);
    TreeItem<ItemComponent> newTreeItem = new TreeItem<>(newItem);
    
    
    // set node on pane   
    newItem.getImageView().setX(x);
    newItem.getImageView().setY(y);
    newItem.getImageView().setFitHeight(height);
    newItem.getImageView().setFitWidth(width);
    farm_pane.getChildren().add(newItem.getImageView());
    
    
    //Sanity check (pass)
    newItem.printinfo();

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
	
	
	int price, x,y, length, width, height;
	price=length =0;
	x=y=100;
	width=height=20;
	
	Image image=  new Image(getClass().getResourceAsStream("item_container.png")); 
    ImageView containerview= new ImageView(image);
	

        // a. First, create new ItemContainer and corresponding TreeItem
        ItemComponent newItemContainer =  new ItemContainer("New Item Container", price, x, y, length, width, height, containerview);
        TreeItem<ItemComponent> newTreeItemContainer = new TreeItem<>(newItemContainer);
        
        
        // set node on pane   
        newItemContainer.getImageView().setX(x);
        newItemContainer.getImageView().setY(y);
        newItemContainer.getImageView().setFitHeight(height);
        newItemContainer.getImageView().setFitWidth(width);
        farm_pane.getChildren().add(newItemContainer.getImageView());
    
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

    // remove from pane
    farm_pane.getChildren().remove(selectedItem.getImageView());


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
	
	String name = null; 
	int price, x,y, length, width, height;
	price =x=y=length=width=height=0;
	
	try {
		name= nameTextField.getText();
	} catch (Exception e) {
		System.out.println("name filed error");
	}
	
	try {
		
		price=Integer.parseInt(priceTextField.getText());
		
	}catch (Exception e) {
		System.out.println("price filed error");
	}
	
	try {
		x=Integer.parseInt(xCoordTextField.getText());
	}catch (Exception e) {
		System.out.println("x coordinate filed error");
	}
	
	try {
		y=Integer.parseInt(yCoordTextField.getText());
	}catch (Exception e) {
		System.out.println("y coordinate filed error");
	}
	
	try {
		length=Integer.parseInt(lengthTextField.getText());
	} catch (Exception e) {
		System.out.println("length filed error");
	}
	 
	try {
		width=Integer.parseInt(widthTextField.getText());
	}catch (Exception e) {
		System.out.println("width filed error");
	}
	
	try {
		height=Integer.parseInt(heightTextField.getText());
	}
	catch (Exception e) {
		System.out.println("height filed error");
	}
	
	
    TreeItem<ItemComponent> selectedTreeItem = treeView.getSelectionModel().getSelectedItem();
    selectedTreeItem.getValue().setName(name);
    selectedTreeItem.getValue().setHeight(height);
    selectedTreeItem.getValue().setLength(length);
    selectedTreeItem.getValue().setWidth(width);
    selectedTreeItem.getValue().setXcoordinate(x);
    selectedTreeItem.getValue().setYcoordinate(y);
    selectedTreeItem.getValue().setPrice(price);
    
    // adjust position on pane
    selectedTreeItem.getValue().getImageView().setX(selectedTreeItem.getValue().getXcoordinate());
    selectedTreeItem.getValue().getImageView().setY(selectedTreeItem.getValue().getYcoordinate());
    selectedTreeItem.getValue().getImageView().setFitHeight(selectedTreeItem.getValue().getHeight());
    selectedTreeItem.getValue().getImageView().setFitWidth(selectedTreeItem.getValue().getWidth());
    
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