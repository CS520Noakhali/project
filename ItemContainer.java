import java.util.ArrayList;
import java.util.List;

// Composite class that represent Item Container
public class ItemContainer extends ItemComponent {

    
    public ItemContainer(String name, int price, int x, int y, int length, int width, int height) {
	    super(name, price, x, y, length, width, height);
    }

    // list to store children of Item Container
    private List<ItemComponent> itemComponentList = new ArrayList<ItemComponent>();

    // add new item to the selected ItemContainer
    @Override	
    public void addItem(Item newItem) {
        itemComponentList.add(newItem);
    }

    // add new Item Container to the selected ItemContainer
    @Override
    public void addItemContainer(ItemContainer newItemContainer) {
        itemComponentList.add(newItemContainer);
    }


    // Delete all content of the item Container
    @Override
    public void delete(ItemComponent itemComponent) {
        itemComponentList.remove(itemComponent);
}

    









    
}