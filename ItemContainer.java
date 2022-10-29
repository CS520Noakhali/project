<<<<<<< HEAD
package application;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

=======
import java.util.ArrayList;
import java.util.List;

>>>>>>> 7af1e57fa7e91971d8b3acaecf989a2e459630c9
// Composite class that represent Item Container
public class ItemContainer extends ItemComponent {

    
<<<<<<< HEAD
    public ItemContainer(String name, int price, int x, int y, int length, int width, int height, ImageView imageview) {
	    super(name, price, x, y, length, width, height, imageview);
=======
    public ItemContainer(String name, int price, int x, int y, int length, int width, int height) {
	    super(name, price, x, y, length, width, height);
>>>>>>> 7af1e57fa7e91971d8b3acaecf989a2e459630c9
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

    









    
}