public class PurchasePriceVisitor extends AbstractVisitor {

    /*
    Implementation of the visit method for the Item. Just returns the price of the selected item
    */
    @Override
    int visit(Item item) {
        return item.getPrice();
    }

    /*
    Implementation of the visit method for the Item container. 
    Should return price of itemContainer + the price of all children. 
    (Note that item-containers can include other item-containers with their children)
    */ 
    @Override
    int visit(ItemContainer itemContainer) {
        // TODO Auto-generated method stub
        return 0;
    }

    

}