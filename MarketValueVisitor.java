public class MarketValueVisitor extends AbstractVisitor {


     /*
    Implementation of the visit method for the Item. Just returns the marketValue of the selected item
    */   
    @Override
    int visit(Item item) {
        return item.getMarketValue();
    }

    /*
    Implementation of the visit method for the Item container. 
    Should return the sum of market values of all children if this item container. 
    That means that item containers themselves have market value of 0. Count only items.
    */ 
    @Override
    int visit(ItemContainer itemContainer) {
        // TODO Auto-generated method stub
        return 0;
    }




}