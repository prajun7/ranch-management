package farmItem;
interface Visitor {
	public abstract void visit(Item item);
	public abstract void visit(ItemContainer itemContainer);
}
