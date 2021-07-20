package farmItem;

import java.io.IOException;

import droneAdapter.TelloDrone;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class View {
	private MainApp mainApp;
    public TreeView<Component> farmList;
    public TextField xPositionField;
    public TextField yPositionField;
    public TextField lengthField;
    public TextField heightField;
    public TextField widthField;
    public TextField nameField;
    public TextField priceField;
    public TextField marketValueField;
    public TextField netPriceField;
    public TextField netValueField;
    public Canvas cv;
    public Canvas cv2;
    public Pane dPane;
    public Pane pane;
    public Timeline timeline;
    public GraphicsContext drawShape;
    public GraphicsContext drawImage;
    public Image image;
    public String nameValue;
    public double xposValue;
    public double yposValue;
    public double heightValue;
    public double widthValue;
    public double lengthValue;
    public double priceValue;
    public double marketValue;
    public double netPrice;
    public double netValue;
    public static ImageView drone1;
    public Component drone;
    public static Component cc;
    public static double ccx;
    public static double ccy;
    public static double xd;
    public static double yd;
    public static double droneStartX;
    public static double droneStartY;
    public static TreeItem<Component> currentItem;
    public static double currentItemXPosition;
    public static double currentItemYPosition;
    public static double dx;
    public static double dy;
    public static double dl;
    public static double dw;
    
	public static TreeItem<Component> getCurrentItem() {
		return currentItem;
	}
	
	public void setCurrentItem() {
		TreeItem<Component> item = farmList.getSelectionModel().getSelectedItem();
	   	this.currentItem = item;
	}
    
	public static double getCurrentItemXPosition() {
		return currentItemXPosition;
	}
	
	public void setCurrentItemXPosition() {
//    	TreeItem<Component> item = farmList.getSelectionModel().getSelectedItem();
//		this.currentItemXPosition = item.getValue().getXPosition();
    	TreeItem<Component> item = getCurrentItem();
		this.currentItemXPosition = item.getValue().getXPosition();
	}
	
	public static double getCurrentItemYPosition() {
		return currentItemYPosition;
	}
	
	public void setCurrentItemYPosition() {
//    	TreeItem<Component> item = farmList.getSelectionModel().getSelectedItem();
//		this.currentItemYPosition = item.getValue().getYPosition();
    	TreeItem<Component> item = getCurrentItem();
		this.currentItemYPosition = item.getValue().getYPosition();
	}
	
	public static double getxd() {
		return xd;
	}
	
	public void setxd() {
    	this.xd = (dPane.getWidth())/6;
	}
	
	public static double getyd() {
		return yd;
	}
	
	public void setyd() {
    	this.yd = dPane.getHeight()-65;
	}
	
	public static double getDroneStartX() {
		return droneStartX;
	}
	
	public void setDroneStartX() {
    	this.droneStartX = 50;
	}
	
	public static double getDroneStartY() {
		return droneStartY;
	}
	
	public void setDroneStartY() {
    	this.droneStartY = 35;
	}
	
	public Component getDrone() {
		return drone;
	}
	
	public void setDrone() {
		this.drone = searchTreeByName("Drone");
	}
	
	public static double getDX() {
		return dx;
	}
	
	public void setDX() {
		drone = getDrone();
		this.dx = drone.getXPosition();
	}
    
	public static double getDY() {
		return dy;
	}
	
	public void setDY() {
		drone = getDrone();
		this.dy = drone.getYPosition();
	}
	
	public static double getDL() {
		return dl;
	}
	
	public void setDL() {
		drone = getDrone();
		this.dl = drone.getLength();
	}
	
	public static double getDW() {
		return dw;
	}
	
	public void setDW() {
		drone = getDrone();
		this.dw = drone.getWidth();
	}
    
    
    
   
    //Radio Buttons
    @FXML private RadioButton scanFarmRadioButton;
    @FXML private RadioButton scanItemRadioButton;
    
    private ToggleGroup scanOneToggleGroup;

    public View() {
    }
    
  
    @FXML
    private void initialize() {
	    farmList.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> updateDetails(newValue));
	    
	    scanOneToggleGroup = new ToggleGroup(); 
	    this.scanFarmRadioButton.setToggleGroup(scanOneToggleGroup);
	    this.scanItemRadioButton.setToggleGroup(scanOneToggleGroup);
	    
	    
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    

    /**
     * Creates the root of the tree
     */
    public void setRoot() {
        if(farmList.getRoot()==null) {
            Component root = new ItemContainer();
            root.setKind("Container");
            root.setName("Root");
            farmList.setRoot(new TreeItem<Component>(root));
            farmList.getSelectionModel().selectFirst();
            farmList.getRoot().setExpanded(true);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot Perform That Action");
            alert.setContentText("You cannot add multiple root items");
            alert.showAndWait();
        }
    }

    /**
     * Adds an item to selected container 
     */
    public void addChild() {
        TreeItem<Component> addChild = farmList.getSelectionModel().getSelectedItem();
        if (farmList.getRoot() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot Perform That Action");
            alert.setContentText("You must first add a root");
            alert.showAndWait();
        } else if (farmList.getSelectionModel().isEmpty() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot Perform That Action");
            alert.setContentText("You must first select a container to which to add your item");
            alert.showAndWait();
        } else if (addChild.getValue().getKind() == "Container") {
    		Component leaf = new Item();
	        leaf.setKind("Item");
	        leaf.setName("Item");
            leaf.setXPosition(525);
            leaf.setYPosition(25);
            leaf.setWidth(10);
            leaf.setHeight(10);
            leaf.setLength(10);
            leaf.setPrice(100);
            leaf.setMarketValue(100);
        	addChild.getChildren().add(new TreeItem<>(leaf));
        	addChild.getValue().addChild(leaf);
			Draw (leaf.getXPosition(),leaf.getYPosition(),leaf.getLength(),leaf.getWidth(),leaf.getName());
    	} else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot Perform That Action");
            alert.setContentText("You cannot add an item to an item. An item must be in a container.");
            alert.showAndWait();
        }
        
    }

    /**
     * Adds a container to the selected container
     */
    public void addContainer() {
    	TreeItem<Component> addContainer = farmList.getSelectionModel().getSelectedItem();
        if (farmList.getRoot() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot Perform That Action");
            alert.setContentText("You must first add a root");
            alert.showAndWait();
        } else if (farmList.getSelectionModel().isEmpty() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot Perform That Action");
            alert.setContentText("You must first select a container to which to add your container");
            alert.showAndWait();
        } else if (addContainer.getValue().getKind() == "Container") {
            Component container = new ItemContainer();
            container.setKind("Container");
            container.setName("Container");
            container.setXPosition(25);
            container.setYPosition(25);
            container.setWidth(25);
            container.setHeight(25);
            container.setLength(25);
            container.setPrice(1000);
            container.setMarketValue(0); // container should have market value of 0
        	addContainer.getChildren().add(new TreeItem<>(container));
        	addContainer.getValue().addChild(container);
        	addContainer.setExpanded(true);
			Draw (container.getXPosition(),container.getYPosition(),container.getLength(),container.getWidth(),container.getName());
        	farmList.getSelectionModel().select(addContainer);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot Perform That Action");
            alert.setContentText("You cannot add a container to an item");
            alert.showAndWait();
        }
    }

    /***
     * Deletes the selected item or container. Warns root cannot be deleted.
     */
    public void Delete() {    	
        TreeItem<Component> itemToDelete = farmList.getSelectionModel().getSelectedItem();
        Component delete = farmList.getSelectionModel().getSelectedItem().getValue();
	        if (itemToDelete.getValue().getKind() == "Item") {
	        	itemToDelete.getParent().getValue().deleteComp(itemToDelete.getValue());
	        	itemToDelete.getParent().getChildren().remove(itemToDelete);
	            ClearDraw(delete.getXPosition(),delete.getYPosition(),delete.getLength(),delete.getWidth(),delete.getName());
	            System.out.println(delete.getXPosition());
	        } else if (itemToDelete.getParent() == null) {
		            Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setTitle("An Error has Occurred");
		            alert.setContentText("You cannot delete the root element");
		            alert.showAndWait();
	        } else if (itemToDelete.getValue().getKind() == "Container" && itemToDelete.getValue().getName() == "Command Center") {
	        	itemToDelete.getParent().getValue().deleteComp(itemToDelete.getValue());
	        	ClearDrone ();
	        	itemToDelete.getParent().getChildren().remove(itemToDelete);
		        ClearDraw(delete.getXPosition(),delete.getYPosition(),delete.getLength(),delete.getWidth(),delete.getName());
	        } else if (itemToDelete.getValue().getKind() == "Drone") {
	        	itemToDelete.getParent().getValue().deleteComp(itemToDelete.getValue());
	        	ClearDrone ();
	        	itemToDelete.getParent().getChildren().remove(itemToDelete);
		        ClearDraw(delete.getXPosition(),delete.getYPosition(),delete.getLength(),delete.getWidth(),delete.getName());
	        } else {
	        	itemToDelete.getParent().getValue().deleteComp(itemToDelete.getValue());
	        	itemToDelete.getParent().getChildren().remove(itemToDelete);
	            // TODO: Need to iterate through all children to delete all items in a container
		        ClearDraw(delete.getXPosition(),delete.getYPosition(),delete.getLength(),delete.getWidth(),delete.getName());
	        }
    }
    /***
     * Adds a command center to the treeview.
     */
    public void addCommandCenter() {
    	TreeItem<Component> addContainer = farmList.getRoot();
    	if (farmList.getRoot() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot Perform That Action");
            alert.setContentText("You must first add a root");
            alert.showAndWait();	
        } else {
            Component commandCenter = new ItemContainer();
            commandCenter.setKind("Container");
            commandCenter.setName("Command Center");
            commandCenter.setXPosition(100);
            commandCenter.setYPosition(100);
            commandCenter.setWidth(100);
            commandCenter.setHeight(40);
            commandCenter.setLength(150);
            commandCenter.setPrice(1000);
        	addContainer.getChildren().add(new TreeItem<>(commandCenter));
        	addContainer.getValue().addChild(commandCenter);
        	addContainer.setExpanded(true);
        	cc = commandCenter;
			Draw (commandCenter.getXPosition(),commandCenter.getYPosition(),commandCenter.getLength(),commandCenter.getWidth(),commandCenter.getName());
        	farmList.getSelectionModel().select(addContainer);
        } 
    }
    
    public void addDrone() {
        TreeItem<Component> addChild = farmList.getSelectionModel().getSelectedItem();
        if (farmList.getRoot() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot Perform That Action");
            alert.setContentText("You must first add a root");
            alert.showAndWait();
        } else if (farmList.getSelectionModel().isEmpty() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot Perform That Action");
            alert.setContentText("You must first select a parent to which to add your item");
            alert.showAndWait();
        } else if (addChild.getValue().getKind() == "Container" && addChild.getValue().getName() == "Command Center") {
    		Component drone = new Item();
	        drone.setKind("Drone");
	        drone.setName("Drone");
	        drone.setXPosition(155);
	        drone.setYPosition(125);
	        drone.setWidth(40);
	        drone.setHeight(15);
	        drone.setLength(40);
	        drone.setPrice(800);
        	addChild.getChildren().add(new TreeItem<>(drone));
        	addChild.getValue().addChild(drone);
        	DrawDrone(drone.getXPosition(), drone.getYPosition(), drone.getLength(), drone.getWidth());
        	addChild.setExpanded(true);
        	farmList.getSelectionModel().getSelectedItem();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot Perform That Action");
            alert.setContentText("You can only add a drone to a Command Center.");
            alert.showAndWait();
        }
    }
    
    /**
     * Draws the rectangle at position (X,Y) of l*b
     * @param x the X position of the upper left corner of the rectangle.
     * @param y the Y position of the upper left corner of the rectangle.
     * @param w the width of the rectangle.
     * @param h the height of the rectangle.
     */
    public void Draw (double x, double y, double l, double h, String nameValue){
	    drawShape = cv.getGraphicsContext2D();
	    drawShape.setLineWidth(1.0);
	    drawShape.setStroke(Color.BLACK);
        drawShape.strokeRect(x, y, l, h);
        drawShape.setLineWidth(.75);
        drawShape.strokeText(nameValue, x + 1, y - 3);
    	
    }

    public ImageView DrawDrone (double x, double y, double l, double h) {
    	ImageView drone1 = new ImageView();
    	drone1.setImage(new Image("/images/drone.png"));
    	drone1.setX(x);
    	drone1.setY(y);
    	drone1.setFitHeight(l);
    	drone1.setFitWidth(h);
    	dPane.getChildren().addAll(drone1);
    	return drone1;
    }
    
//    public void DrawComponent (double x, double y, double l, double h, String nameValue) {
//    	Rectangle rect = new Rectangle();
//    	Text text = new Text(nameValue);
//    	rect.setStrokeWidth(.75);
//    	rect.setStroke(Color.BLACK);;
//    	rect.setFill(null);
//    	rect.setLayoutX(x);
//    	rect.setLayoutY(y);
//    	rect.setWidth(l);
//    	rect.setHeight(h);
//    	text.setLayoutX(x+1);
//    	text.setLayoutY(y-3);
//    	text.setFill(Color.BLACK);
//    	text.setFont(Font.font("Arial", 10));
//    	pane.getChildren().addAll(rect, text);
//    }

    @FXML
    public void ScanFarm () throws IOException, InterruptedException {
    	FarmScan();
    }
    
    public Component searchTreeByName(String name) {
    	return searchTreeByNameHelper(farmList.getRoot(), name);
    }
    
    public Component searchTreeByNameHelper(TreeItem<Component> treeitem, String name) {
    	ObservableList<TreeItem<Component>> children = treeitem.getChildren();
    	if (treeitem.getValue().getName()==name) {
			return treeitem.getValue();
		}
    	if (children.isEmpty()==true) {
    		// If no children, return null
    		 return null;
    	} else {
    		for(TreeItem<Component> child: children) {
    			// If children, continue search
    			return searchTreeByNameHelper(child, name);
    		} return null;
    	} 
    }
    
   
    public void LaunchSimulator() throws IOException, InterruptedException {
    	
	    setDrone();
	    setCurrentItem();
	    setCurrentItemXPosition();
	    setCurrentItemYPosition();
	    setDX();
	    setDY();
	    setDL();
	    setDW();
    	
    	if (this.scanOneToggleGroup.getSelectedToggle().equals(this.scanFarmRadioButton)) {
    	      FarmScan ();
    	}
    	      
    	if (this.scanOneToggleGroup.getSelectedToggle().equals(this.scanItemRadioButton)) {
    		DroneToItem();
    	}
    }
    
    public void LaunchDrone() throws IOException, InterruptedException {
    	
    	System.out.println("DRONE LAUNCH EXECUTED.");
    	setCurrentItemXPosition();
    	setCurrentItemYPosition();
    	setxd();
    	setyd();
    	setDroneStartX();
    	setDroneStartY();
    	
    	if (this.scanOneToggleGroup.getSelectedToggle().equals(this.scanFarmRadioButton)) {
    		TelloDrone drone2 = new TelloDrone();
    		AdapterDroneImp adapterDrone = new AdapterDroneImp(drone2);  
    		adapterDrone.FarmScan();
    	}
    	      
    	if (this.scanOneToggleGroup.getSelectedToggle().equals(this.scanItemRadioButton)) {
    		TelloDrone drone2 = new TelloDrone();
    		AdapterDroneImp adapterDrone = new AdapterDroneImp(drone2); 
    	      adapterDrone.DroneToItem();
    	}
    }
    
    
    public void FarmScan () throws IOException, InterruptedException  {
    	ClearDrone();

    	drone = searchTreeByName("Drone");
    	double x = drone.getXPosition();
    	double y = drone.getYPosition();
    	double l = drone.getLength();
    	double w = drone.getWidth();
    	ImageView drone1 = DrawDrone(x,y,l,w);
    	
    	double ccx = farmList.getRoot().getValue().getXPosition();
    	double ccy = farmList.getRoot().getValue().getYPosition();
    	double ccl = farmList.getRoot().getValue().getLength();
    	double ccw = farmList.getRoot().getValue().getWidth();
    	TranslateTransition tc0 = new TranslateTransition(Duration.seconds(3));
    	tc0.setToX(ccx-x+(ccl/2)-(l/2));
    	tc0.setToY(ccy-y+(ccw/2)-(w/2));
    	
    	double droneStartX = 50;
    	double droneStartY = 35;
    	
    	double xd = (dPane.getWidth())/6;
    	double yd = dPane.getHeight()-65;
    	
    	double ccxNow = cc.getXPosition()+(cc.getLength()/2-(droneStartX));
    	System.out.println(ccxNow);
    	double ccyNow = cc.getYPosition()+(cc.getWidth()/2-(droneStartY));
    	System.out.println(ccyNow);
    	
    	TranslateTransition t0 = new TranslateTransition(Duration.seconds(3));
    	t0.setByX(50);
    	t0.setByY(35);
    	TranslateTransition t1 = new TranslateTransition(Duration.seconds(3));
    	t1.setByX(0);
    	t1.setByY(yd);
    	TranslateTransition t2 = new TranslateTransition(Duration.seconds(1));
    	t2.setByX(xd);
    	t2.setByY(0);
    	TranslateTransition t3 = new TranslateTransition(Duration.seconds(3));
    	t3.setByX(0);
    	t3.setByY(-yd);
    	TranslateTransition t4 = new TranslateTransition(Duration.seconds(1));
    	t4.setByX(xd);
    	t4.setByY(0);
    	TranslateTransition t5 = new TranslateTransition(Duration.seconds(3));
    	t5.setByX(0);
    	t5.setByY(yd);
    	TranslateTransition t6 = new TranslateTransition(Duration.seconds(1));
    	t6.setByX(xd);
    	t6.setByY(0);
    	TranslateTransition t7 = new TranslateTransition(Duration.seconds(3));
    	t7.setByX(0);
    	t7.setByY(-yd);
    	TranslateTransition t8 = new TranslateTransition(Duration.seconds(1));
    	t8.setByX(xd);
    	t8.setByY(0);   	
    	TranslateTransition t9 = new TranslateTransition(Duration.seconds(3));
    	t9.setByX(0);
    	t9.setByY(yd);
    	TranslateTransition t10 = new TranslateTransition(Duration.seconds(1));
    	t10.setByX(xd);
    	t10.setByY(0);   	
    	TranslateTransition t11 = new TranslateTransition(Duration.seconds(3));
    	t11.setByX(0);
    	t11.setByY(-yd);
    	TranslateTransition t12 = new TranslateTransition(Duration.seconds(1));
    	t12.setByX(-xd*5);
    	t12.setByY(0);   	
    	TranslateTransition t13 = new TranslateTransition(Duration.seconds(3));
    	t13.setByX(ccxNow);
    	t13.setByY(ccyNow);  	
    	SequentialTransition seqT = new SequentialTransition (drone1,tc0,t0,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13);
    	seqT.play();
    }

    public void DroneToItem() {
    	
	    	ClearDrone();
	    	drone = getDrone();
	    	double x = getDX();
	    	double y = getDY();
	    	double l = getDL();
	    	double w = getDW();
	    	ImageView drone1 = DrawDrone(x,y,l,w); 
	    	TreeItem<Component> item = getCurrentItem();	    	
	    	TranslateTransition t0 = new TranslateTransition(Duration.seconds(3));
	    	double currentItemXPosition = getCurrentItemXPosition();
	    	double currentItemYPosition = getCurrentItemYPosition();
	    	drone.setXPosition(currentItemXPosition+(item.getValue().getLength()/2)-(l/2));
	    	drone.setYPosition(currentItemYPosition+(item.getValue().getWidth()/2)-(w/2));
	    	t0.setByX(currentItemXPosition-x+(item.getValue().getLength()/2)-(l/2));
	    	t0.setByY(currentItemYPosition-y+(item.getValue().getWidth()/2)-(w/2));
	    	SequentialTransition seqT = new SequentialTransition (drone1,t0);
	    	seqT.play();
    	
//    	AnimationImp runDroneToItem = new AnimationImp();
//    	runDroneToItem.DroneToItem();
    }

    /**
     * Removes the rectangle at position (X,Y) of l*b
     * @param x the X position of the upper left corner of the rectangle.
     * @param y the Y position of the upper left corner of the rectangle.
     * @param w the width of the rectangle.
     * @param h the height of the rectangle.
     */
    public void ClearDraw(double x, double y, double l, double h, String nameValue){
	    drawShape = cv.getGraphicsContext2D();
	    drawShape.setLineWidth(1.5);
	    drawShape.setStroke(Color.WHITE);
        drawShape.strokeRect(x, y, l, h);
        drawShape.setLineWidth(1.25);
        drawShape.strokeRect(x, y, l, h);
        drawShape.strokeText(nameValue, x + 1, y - 3);
    }
    
    /*
     * Clears Drone from dPane
     */
    public void ClearDrone () {
    	dPane.getChildren().clear();
    	    }

    /**
     * Refreshed the fields with current data when an item is selected.
     * @param newValue 
     */
    public void updateDetails(TreeItem<Component> newValue) {  	
    	if (newValue != null) {
    		nameField.setText(newValue.getValue().getName());
    		xPositionField.setText(Double.toString(newValue.getValue().getXPosition()));
    		yPositionField.setText(Double.toString(newValue.getValue().getYPosition()));
    		lengthField.setText(Double.toString(newValue.getValue().getLength()));
    		widthField.setText(Double.toString(newValue.getValue().getWidth()));
    		heightField.setText(Double.toString(newValue.getValue().getHeight()));
    		priceField.setText(Double.toString(newValue.getValue().getPrice()));
    		marketValueField.setText(Double.toString(newValue.getValue().getMarketValue()));
            ItemVisitor vis = new ItemVisitor();
            newValue.getValue().accept(vis);
            netPriceField.setText(Double.toString(vis.netPrice()));
            netValueField.setText(Double.toString(vis.netValue()));
    	} else {
    		nameField.clear();
    		xPositionField.clear();
    		yPositionField.clear();
    		lengthField.clear();
    		widthField.clear();
    		heightField.clear();
    		priceField.clear();
    		marketValueField.clear();
    		netValueField.clear();
    	}
    }
    
    /**
     * Saves the given information 
     */
    public void Save() {
    	Component save = farmList.getSelectionModel().getSelectedItem().getValue();
        if (save == farmList.getRoot().getValue()) {  //For changing the name of the Root
            nameValue = nameField.getText();
            save.setName(nameValue);
        } else if (save.getKind() == "Drone") {
        	ClearDrone ();
            nameValue = nameField.getText();
            xposValue = Double.parseDouble(xPositionField.getText());
            yposValue = Double.parseDouble(yPositionField.getText());
            heightValue = Double.parseDouble(heightField.getText());
            lengthValue = Double.parseDouble(lengthField.getText());
            priceValue = Double.parseDouble(priceField.getText());
            marketValue = Double.parseDouble(marketValueField.getText());
            widthValue = Double.parseDouble(widthField.getText());
            save.setName(nameValue);
            save.setXPosition(xposValue);
            save.setYPosition(yposValue);
            save.setHeight(heightValue);
            save.setLength(lengthValue);
            save.setWidth(widthValue);
            save.setPrice(priceValue);
            save.setMarketValue(marketValue);
    		DrawDrone (xposValue, yposValue, lengthValue, widthValue);
        } else {
        	ClearDraw (save.getXPosition(), save.getYPosition(), save.getLength(), save.getWidth(), save.getName());
            nameValue = nameField.getText();
            xposValue = Double.parseDouble(xPositionField.getText());
            yposValue = Double.parseDouble(yPositionField.getText());
            heightValue = Double.parseDouble(heightField.getText());
            lengthValue = Double.parseDouble(lengthField.getText());
            priceValue = Double.parseDouble(priceField.getText());
            marketValue = Double.parseDouble(marketValueField.getText());
            widthValue = Double.parseDouble(widthField.getText());
            save.setName(nameValue);
            save.setXPosition(xposValue);
            save.setYPosition(yposValue);
            save.setHeight(heightValue);
            save.setLength(lengthValue);
            save.setWidth(widthValue);
            save.setPrice(priceValue);
            if (save.getKind() == "Item") {
                save.setMarketValue(marketValue);
            }
			Draw (xposValue, yposValue, lengthValue, widthValue, nameValue);
        }
        farmList.refresh();

    }
}

