//Leaf //Widgets

package farmItem;

public class Item extends Component {
	
	private String name;
	private String kind;
	private double xPos;
	private double yPos;
	private double height;
	private double width;
	private double length;
	private double price;
	private double marketValue;
	
	private double netPrice = 0.0;
	private double netValue = 0.0;
	

	public void addChild(Component widgetComponent) {
		// Thrown an exception
	}

	@Override
	public void deleteComp(Component widgetComponent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		
	}
	
	@Override
	public String getKind() {
		// TODO Auto-generated method stub
		return this.kind;
	}
	
	@Override
	public void setKind(String kind) {
		this.kind = kind;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}

	@Override
	public void setYPosition(double yPos) {
		this.yPos = yPos;
		
	}

	@Override
	public double getYPosition() {
		// TODO Auto-generated method stub
		return yPos;
	}

	@Override
	public void setXPosition(double xPos) {
		// TODO Auto-generated method stub
		this.xPos = xPos;
		
	}

	@Override
	public double getXPosition() {
		// TODO Auto-generated method stub
		return this.xPos;
	}

	@Override
	public void setHeight(double height) {
		// TODO Auto-generated method stub
		this.height = height;
		
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}

	@Override
	public void setWidth(double width) {
		this.width = width;
		
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	@Override
	public void setPrice(double price) {
		// TODO Auto-generated method stub
		this.price = price;
		this.netPrice = price;
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}
	
	@Override
	public void setLength(double length) {
		this.length = length;
		
	}

	@Override
	public double getLength() {
		// TODO Auto-generated method stub
		return this.length;
	}
	
	@Override
	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}
	
	@Override
	public double getMarketValue() {
		return this.marketValue;
	}
	
	@Override
	public double netPrice() {
		return netPrice;
	}
	
	@Override
	public double netValue() {
		return netValue;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
