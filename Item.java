

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

// Leaf class that represent Item 
public class Item extends ItemComponent {

    public Item(String name, int price, int x, int y, int length, int width, int height, ImageView imageview) {
	    super(name, price, x, y, length, width, height, imageview);
    }

    @Override
    public void delete(ItemComponent itemComponent) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void add(ItemComponent itemComponent) {
       throw new UnsupportedOperationException();
        
    }

    
    

}