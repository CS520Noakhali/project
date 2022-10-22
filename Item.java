
// Leaf class that represent Item 
public class Item extends ItemComponent {

    public Item(String name, int price, int x, int y, int length, int width, int height) {
	    super(name, price, x, y, length, width, height);
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