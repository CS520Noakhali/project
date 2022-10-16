
import java.util.ArrayList;
import java.util.List;

// Composite class that represent Item Container
public class ItemContainer extends ItemComponent {

    // list to store children of Item Container
    private List<ItemComponent> itemComponentList = new ArrayList<ItemComponent>();

    // add new item to the selected ItemContainer
    public void addItem(Item newItem) {
        itemComponentList.add(newItem);
    }

    // add new Item Container to the selected ItemContainer
    public void addItemContainer(ItemContainer newItemContainer) {
        itemComponentList.add(newItemContainer);
    }


    // Delete all content of the item Container
    public void delete() {
        for (ItemComponent itemComponent : itemComponentList) {
            itemComponent.delete();
        }
    }

    









    
}