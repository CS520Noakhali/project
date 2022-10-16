
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
        this.length = newWidth;
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



    // ****************************
    //       abstract methods
    // ***************************

    // add new item to the selected ItemContainer
    abstract void addItem(Item newItem);
    
    // add new Item Container to the selected ItemContainer
    abstract void addItemContainer(ItemContainer newItemContainer);
        
    // For Item class - just delete item. Fot ItemContainer - delete container and all items that were inside it.
    abstract void delete();







    





    











}