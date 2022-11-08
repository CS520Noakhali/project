/*
 * Abstract class representing abstract Visitor interface.
 */

abstract class AbstractVisitor {

    abstract int visitItem(Item item);

    abstract int visitItemContainer(ItemContainer itemContainer);


}