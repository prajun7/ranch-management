//Component 

package farmItem;

public abstract class Component {
	

	public abstract void addChild(Component widgetComponent);
	
	public abstract void deleteComp(Component widgetComponent);
	
	public abstract String getName();  //To get the name
	
	public abstract void setName(String name);
	
	public abstract void setKind(String kind);
	
	public abstract String getKind();
	
    public abstract String toString();
    
    public abstract void setYPosition(double yPos);
    
    public abstract double getYPosition();
    
    public abstract void setXPosition(double xPos);
     
    public abstract double getXPosition();
    
    public abstract void setHeight(double height);
    
    public abstract double getHeight();
    
    public abstract void setLength(double length);
    
    public abstract double getLength();
    
    public abstract void setWidth(double width);
    
    public abstract double getWidth();
    
    public abstract void setPrice(double price);
    
    public abstract double getPrice();
    
    public abstract void setMarketValue(double marketValue);
    
    public abstract double getMarketValue();

    public abstract double netPrice();
    
    public abstract double netValue();
    
    public abstract void accept(Visitor vis);
}
