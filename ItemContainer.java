import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;

// Composite class that represent Item Container
public class ItemContainer extends ItemComponent {
    
    public ItemContainer(String name, int price, int x, int y, int length, int width, int height, ImageView imageview) {
	    super(name, price, x, y, length, width, height, imageview);
    }

    // list to store children of Item Container
    private List<ItemComponent> itemComponentList = new ArrayList<ItemComponent>();

    // add new item to the selected ItemContainer
    @Override	
    public void add(ItemComponent itemComponent) {
        itemComponentList.add(itemComponent);
    }

    // Delete all content of the item Container
    @Override
    public void delete(ItemComponent itemComponent) {
        itemComponentList.remove(itemComponent);
    }

    // Calculates Purchase Price of the Item Container as its price plus prices of its children
    public int getPurchasePrice() {
        int purchasePrice = this.getPrice();
        for (ItemComponent ic : itemComponentList) {
            purchasePrice += ic.getPrice();
        }
        return purchasePrice;
    }

    public List<ItemComponent> getChildren() {
        return itemComponentList;
    }

    // Accept Visitor method
    @Override
    int accept(AbstractVisitor visitor) {
        return visitor.visitItemContainer(this);
    }

    public int getMarketValue() {
        return 0;
    }



}