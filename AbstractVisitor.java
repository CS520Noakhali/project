/*
 * Abstract class representing abstract Visitor interface.
 */

abstract class AbstractVisitor {

    abstract int visit(Item item);

    abstract int visit(ItemContainer itemContainer);


}