package farmItem;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class AnimationImp extends View implements Animation {
	
	public AnimationImp() {

	}
	
	@Override
	public void FarmScan ()  {
		
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


	@Override
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
    }
}
