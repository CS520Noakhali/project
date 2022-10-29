<<<<<<< HEAD
package application;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
=======
>>>>>>> 7af1e57fa7e91971d8b3acaecf989a2e459630c9

// Leaf class that represent Item 
public class Item extends ItemComponent {

<<<<<<< HEAD
    public Item(String name, int price, int x, int y, int length, int width, int height, ImageView imageview) {
	    super(name, price, x, y, length, width, height, imageview);
=======
    public Item(String name, int price, int x, int y, int length, int width, int height) {
	    super(name, price, x, y, length, width, height);
>>>>>>> 7af1e57fa7e91971d8b3acaecf989a2e459630c9
    }

    @Override
    public void delete(ItemComponent itemComponent) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void add(ItemComponent itemComponent) {
       throw new UnsupportedOperationException();
        
    }

<<<<<<< HEAD
    
    
=======
>>>>>>> 7af1e57fa7e91971d8b3acaecf989a2e459630c9

}