import javafx.scene.image.ImageView;

// Leaf class that represent Item 
public class Item extends ItemComponent {

    public int marketValue;

    public Item(String name, int price, int x, int y, int length, int width, int height, int marketValue, ImageView imageview) {
	    super(name, price, x, y, length, width, height, imageview);
        this.marketValue = marketValue;
    }

    public int getMarketValue() {
    	return this.marketValue;
    }

    public void setMarketValue(int marketValue) {
    	this.marketValue = marketValue;
    }

    @Override
    public void delete(ItemComponent itemComponent) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void add(ItemComponent itemComponent) {
       throw new UnsupportedOperationException();
        
    }

    // Accept Visitor method
    @Override
    int accept(AbstractVisitor visitor) {
        return visitor.visitItem(this);
    }

    
}