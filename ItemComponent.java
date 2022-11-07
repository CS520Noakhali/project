import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

// The component abstract class declares common variables and operations for both
// composite (ItemContainer) and leaf (Item) classes.

abstract class ItemComponent {

    private String name;
    private int price;
    private int xCoordinate;
    private int yCoordinate;
    private int length;
    private int width;
    private int height;
    private int marketValue;
    
    @FXML
    private ImageView imageview;
    

    public ItemComponent(String name, int price, int x, int y, int length, int width, int height, int marketValue, ImageView imageview) {
        this.name = name;
        this.price = price;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.length = length;
        this.width = width;
        this.height = height;
        this.marketValue = marketValue;
        this.imageview=imageview;
    }

    // ****************************
    //    non- abstract methods
    // ***************************

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getXcoordinate() {
        return xCoordinate;
    }

    public void setXcoordinate(int newXcoordinate) {
        this.xCoordinate = newXcoordinate;
    }

    public int getYcoordinate() {
        return yCoordinate;
    }

    public void setYcoordinate(int newYcoordinate) {
        this.yCoordinate = newYcoordinate;
    };

    public int getLength() {
        return length;
    }

    public void setLength(int newLength) {
        this.length = newLength;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int newWidth) {
        this.width = newWidth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int newPrice) {
        this.price = newPrice;
    }
    
    public ImageView getImageView() {
    	return this.imageview;
    }

    public void setImageView(ImageView imageview) {
    	this.imageview=imageview;
    }

    public int getMarketValue() {
    	return this.marketValue;
    }

    public void setMarketValue(int marketValue) {
    	this.marketValue = marketValue;
    }
    
    @Override
    public String toString() {
        return this.name;
    }


    // debug purpose
    public void printinfo() {
    	System.out.println("Name : "+ this.getName());
    	System.out.println("X cord : "+ this.getXcoordinate() + " Y cord: " + this.getYcoordinate());
    	System.out.println("Length : "+ this.getLength() + " Width: " + this.getWidth() + "height :" + this.getHeight());
    	System.out.println("price : "+ this.getPrice());
        System.out.println("market value : "+ this.getMarketValue());
    	
    }

    // ****************************
    //       abstract methods
    // ***************************

    
    abstract void add(ItemComponent itemComponent);
        
    abstract void delete(ItemComponent itemComponent);
    
    abstract int accept(AbstractVisitor visitor);
    
}