import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;

/*
 * DashboardController class implemented using Singleton design pattern
 */
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
    private TextField marketPriceTextField;

    @FXML
    private TextField widthTextField;

    @FXML
    private TextField xCoordTextField;

    @FXML
    private TextField yCoordTextField;

    @FXML
    private  TextField heightTextField;
    
    @FXML
    private Pane farm_pane;

    // shows market value calculated with Visitor
    @FXML
    private TextField calculatedMarketValueField;

    // shows purchase price calculated with Visitor
    @FXML
    private TextField calculatedPurchasePriceField;


    //GLOBAL VALUES
    int X_FINAL_COORD, Y_FINAL_COORD = 0;
    int X_COORD_LIMIT_POSITIVE = 500;
    int X_COORD_LIMIT_NEGATIVE = 0;
    int Y_COORD_LIMIT_POSITIVE = 700;
    int Y_COORD_LIMIT_NEGATIVE = 0;

    // variables for main components that should be visible for all methods (root, drone, command center)
    ImageView dummy = null;
    Image droneimage=  new Image(getClass().getResourceAsStream("drone.png")); 
    ImageView drone_view= new ImageView(droneimage);
    Image com_centre_image = new Image(getClass().getResourceAsStream("comm_center.png"));
    ImageView com_centre_view= new ImageView(com_centre_image);
    ItemComponent rootIC = new ItemContainer("root", 0, 0, 0, 0, 0, 0, dummy);
    ItemComponent commCenterIC = new ItemContainer("command center", 100, 400, 400, 0, 60,60, com_centre_view);
    ItemComponent droneIC = new Drone("drone", 10, 400, 400, 0, 60, 60, 10, drone_view);


    /* 
    Initialize method: TreeView is created
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Add command center and drone to the TreeView
        rootIC.add(commCenterIC);
        commCenterIC.add(droneIC);
        
        // Create corresponding TreeItems 
        TreeItem<ItemComponent> root = new TreeItem<>(rootIC);
        TreeItem<ItemComponent> commandCenter = new TreeItem<>(commCenterIC);
        TreeItem<ItemComponent> drone = new TreeItem<>(droneIC);

        // Add root, command center and drone to the TreeView
        root.setExpanded(true);
        root.getChildren().add(commandCenter);
        commandCenter.getChildren().add(drone);
        commandCenter.setExpanded(true);
        treeView.setRoot(root);
        
        // set drone image on the farm pane
        droneIC.getImageView().setX(droneIC.getXcoordinate());
        droneIC.getImageView().setY(droneIC.getYcoordinate());
        droneIC.getImageView().setFitHeight(droneIC.getHeight());
        droneIC.getImageView().setFitWidth(droneIC.getWidth());
        farm_pane.getChildren().add(droneIC.getImageView());
        
        // set command center on the farm pane
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
        // Get currently selected directory TreeItem 
        TreeItem<ItemComponent> selectedTreeItem = treeView.getSelectionModel().getSelectedItem();
        // Get the Item corresponding to the current directory TreeItem
        ItemComponent selectedItem = selectedTreeItem.getValue();

        if (selectedItem instanceof ItemContainer) {
            int price, x,y, length, marketValue, width, height;
            length = 0;
            price = marketValue = 10;
            x=y=100;
            width=height=20;
            
            Image image=  new Image(getClass().getResourceAsStream("testitem.png")); 
            ImageView testview1= new ImageView(image);
            
            // a. First, create new Item and corresponding TreeItem
            ItemComponent newItem =  new Item("New item", price, x, y, length, width, height, marketValue, testview1);
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
    }

    /* 
    ADD ITEM CONTAINER functionality
    */
    @FXML
    void onAddItemContainerButtonClick(ActionEvent event) {
        // Get currently selected directory TreeItem 
        TreeItem<ItemComponent> selectedTreeItem = treeView.getSelectionModel().getSelectedItem();
        // Get the Item corresponding to the current directory TreeItem
        ItemComponent selectedItem = selectedTreeItem.getValue();

        if (selectedItem instanceof ItemContainer) {
            int price, x,y, length, width, height;
            price = 100;
            length = 0;
            x = y = 100;
            width = height = 100;
            
            // Image for the item container
            Image image = new Image(getClass().getResourceAsStream("item_container.png")); 
            ImageView containerview= new ImageView(image);
            containerview.toBack();
    
            // a. Create new ItemContainer and corresponding TreeItem
            ItemComponent newItemContainer =  new ItemContainer("New Item Container", price, x, y, length, width, height, containerview);
            TreeItem<ItemComponent> newTreeItemContainer = new TreeItem<>(newItemContainer);
            
            // b. set the image on the farm pane  
            newItemContainer.getImageView().setX(x);
            newItemContainer.getImageView().setY(y);
            newItemContainer.getImageView().setFitHeight(height);
            newItemContainer.getImageView().setFitWidth(width);
            farm_pane.getChildren().add(newItemContainer.getImageView());
    
    
            // c. update TreeView 
            // Add new item to the selected TreeItem
            selectedTreeItem.getChildren().add(newTreeItemContainer);
            newTreeItemContainer.setExpanded(true);
            treeView.getSelectionModel().select(newTreeItemContainer);
    
            // d. Update our Composite pattern structure
            // Get the Item Container corresponding to the current directory TreeItem
            ItemContainer selectedItemContainer = (ItemContainer)selectedTreeItem.getValue();
            // Add item to the item container
            selectedItemContainer.add(newItemContainer);

        }


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

        // if the selectedItem is neither the root, command center, nor drone...
        if (!selectedItem.getName().equals("root") && !selectedItem.getName().equals("command center") && !selectedItem.getName().equals("drone")) {

            // remove the image of the item from the farm pane
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
    }

    /*
     * Function that saves the information about the item when the "Save" button is clicked
     */
    @FXML
    void onSaveItemInfoButtonClick(ActionEvent event) {
        
        String name = null; 
        int price, marketValue, x,y, length, width, height;
        price=marketValue=x=y=length=width=height=0;
        
        try {
            name= nameTextField.getText();
        } catch (Exception e) {
            System.out.println("name field error");
        }
        
        try {
            
            price=Integer.parseInt(priceTextField.getText());
            
        }catch (Exception e) {
            System.out.println("price field error");
        }

        try {
            
            marketValue=Integer.parseInt(marketPriceTextField.getText());
            
        }catch (Exception e) {
            System.out.println("market value field error");
        }
        
        try {
            x=Integer.parseInt(xCoordTextField.getText());
        }catch (Exception e) {
            System.out.println("x coordinate field error");
        }
        
        try {
            y=Integer.parseInt(yCoordTextField.getText());
        }catch (Exception e) {
            System.out.println("y coordinate field error");
        }
        
        try {
            length=Integer.parseInt(lengthTextField.getText());
        } catch (Exception e) {
            System.out.println("length field error");
        }
        
        try {
            width=Integer.parseInt(widthTextField.getText());
        }catch (Exception e) {
            System.out.println("width field error");
        }
        
        try {
            height=Integer.parseInt(heightTextField.getText());
        }
        catch (Exception e) {
            System.out.println("height field error");
        }
        
        // set variables to the Item Component using setters methods
        TreeItem<ItemComponent> selectedTreeItem = treeView.getSelectionModel().getSelectedItem();
        selectedTreeItem.getValue().setName(name);
        selectedTreeItem.getValue().setHeight(height);
        selectedTreeItem.getValue().setLength(length);
        selectedTreeItem.getValue().setWidth(width);
        selectedTreeItem.getValue().setXcoordinate(x);
        selectedTreeItem.getValue().setYcoordinate(y);
        selectedTreeItem.getValue().setPrice(price);
        
        // set market value if that was an Item
        if (selectedTreeItem.getValue() instanceof Item) {
            Item obj = (Item) selectedTreeItem.getValue();
            obj.setMarketValue(marketValue);
        } 
        
        
        // adjust position on pane
        selectedTreeItem.getValue().getImageView().setX(selectedTreeItem.getValue().getXcoordinate());
        selectedTreeItem.getValue().getImageView().setY(selectedTreeItem.getValue().getYcoordinate());
        selectedTreeItem.getValue().getImageView().setFitHeight(selectedTreeItem.getValue().getHeight());
        selectedTreeItem.getValue().getImageView().setFitWidth(selectedTreeItem.getValue().getWidth());
        
        treeView.refresh();
    }

    /*
    * Implements "Return home" functionality of the drone
    */
    @FXML
    void onReturnHomeButtonClick() {

        int x = commCenterIC.getXcoordinate();
        int y = commCenterIC.getYcoordinate();
        int w = commCenterIC.getWidth();
        int h = commCenterIC.getHeight();

        //translate
        droneIC.getImageView().toFront();
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(droneIC.getImageView());
        translate.setDuration(Duration.millis(500));

        x = (int) x + w/2 - 30; //centering drone over the item
        y = (int) y + h/2 - 30; //centering drone over item

        int deltaX = x - droneIC.getXcoordinate();
        int deltaY = y - droneIC.getYcoordinate();

        translate.setByX(deltaX);
        translate.setByY(deltaY);
        translate.play();

        // update drone's coordinates
        droneIC.setXcoordinate(x);
        droneIC.setYcoordinate(y);

        System.out.println("return to command center");

    }


    /*
     * Function that implements "Scan farm" functionality for the drone
     */
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

        droneIC.getImageView().toFront();
        TranslateTransition initiate = new TranslateTransition();
        initiate.setNode(droneIC.getImageView());
        initiate.setDuration(Duration.millis(900));
        initiate.setByX(20-droneIC.getXcoordinate()); //centering drone to top left
        initiate.setByY(30-droneIC.getYcoordinate()); 
        lst1.add(initiate);

        for (int i = 0;i<coordinates.length;i++){
            TranslateTransition translate = new TranslateTransition();
            
            int[] arr = coordinates[i];
            int x = arr[0];
            int y = arr[1];
            
            translate.setNode(droneIC.getImageView());
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
        System.out.println("scan farm");

        // update drone's coordinates
        droneIC.setXcoordinate(520);
        droneIC.setYcoordinate(30);
    }

    /*
     * Function that implements "Visit item" functionality for the drone
     */
    @FXML
    void onVisitItemButtonClick() {
        int x = Integer.parseInt(xCoordTextField.getText());
        int y = Integer.parseInt(yCoordTextField.getText());
        int w = Integer.parseInt(widthTextField.getText());
        int h = Integer.parseInt(heightTextField.getText());

        //translate
        droneIC.getImageView().toFront();
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(droneIC.getImageView());
        translate.setDuration(Duration.millis(500));

        x = (int) x + w/2 - 30; //centering drone over the item
        y = (int) y + h/2 - 30; //centering drone over item

        int deltaX = x - droneIC.getXcoordinate();
        int deltaY = y - droneIC.getYcoordinate();

        translate.setByX(deltaX);
        translate.setByY(deltaY);
        translate.play();

        // update drone's coordinates
        droneIC.setXcoordinate(x);
        droneIC.setYcoordinate(y);

        System.out.println("visited item");

    }
    
    /*
     * Function that shows information about the item, when the item is selected in the directory view
     */
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
    marketPriceTextField.setText(Integer.toString(selectedTreeItem.getValue().getMarketValue()));
    
    }

    /*
     * Function that calculates the Market Value of the selected item/item container
     */
    @FXML
    void onCalculateMarketValueClick(ActionEvent event) {

        // create  MarketValueVisitor object
        AbstractVisitor visitor = new MarketValueVisitor();

        // get selected item component
        ItemComponent selectedIC = treeView.getSelectionModel().getSelectedItem().getValue();

        // Accept visitor
        int marketValue = selectedIC.accept(visitor);

        try {
            calculatedMarketValueField.setText(Integer.toString(marketValue));         
        } catch (Exception e) {
            System.out.println("Calculated Market Value Text Field error");
        }
        
    }

    /*
     * Function that calculates the Purchase price of the selected item/item container
     */
    @FXML
    void onCalculatePurchasePriceClick(ActionEvent event) {

        // create PurchasePriceVisitor object
        AbstractVisitor visitor = new PurchasePriceVisitor();

        // get selected item component
        ItemComponent selectedIC = treeView.getSelectionModel().getSelectedItem().getValue();

        int purchasePrice = selectedIC.accept(visitor);

        try {
            calculatedPurchasePriceField.setText(Integer.toString(purchasePrice));          
        } catch (Exception e) {
            System.out.println("Calculated Purchase Price Text Field error");
        }
        
    }

}