package farmItem;

class ItemVisitor implements Visitor {
	private float netPrice;
	private float netValue;
	
	public ItemVisitor() {
		netPrice = 0;
		netValue = 0;
	}
	
	public float netPrice() {
		return netPrice;
	}
	
	public float netValue() {
		return netValue;
	}
	
	public void visit(Item item) {
		netPrice += item.netPrice();
		netValue += item.netValue();
	}
	
	public void visit(ItemContainer itemContainer) {
		netPrice += itemContainer.netPrice();
		netValue += itemContainer.netValue();
	}
}
