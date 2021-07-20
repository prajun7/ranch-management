package farmItem;

import java.io.IOException;
import droneAdapter.*;
import droneAdapter.Constants;
import javafx.scene.control.TreeItem;

import static droneAdapter.Constants.CENTIMETERS_PER_MODEL_FOOT;
import static droneAdapter.Constants.PIXELS_TO_ONE_MODEL_FOOT;

public class AdapterDroneImp extends View implements Animation {
	
	TelloDrone tellodrone;
	
	public AdapterDroneImp (TelloDrone tellodrone){
	       this.tellodrone = tellodrone;
	    }

	public double pxToFt(double n) {
		double pixelToFeet = n / PIXELS_TO_ONE_MODEL_FOOT;
		double feetToCm = pixelToFeet * CENTIMETERS_PER_MODEL_FOOT;
		return feetToCm;
	}
	
	@Override
	public void FarmScan() throws IOException, InterruptedException {
		
		double ccx1 = View.cc.getXPosition();
		double ccy1 = View.cc.getYPosition();
		
    	double xd1 = View.getxd();
    	double yd1 = View.getyd();
    	
    	double droneStartX1 = View.getDroneStartX();
    	double droneStartY1 = View.getDroneStartY();
    	
    	System.out.println(droneStartX1);
    	System.out.println(droneStartY1);
    	
// ACTUAL DRONE COMMANDS ARE COMMENTED OUT DUE TO LACK OF DRONE - CRASHES APP
		
//		tellodrone.activateSDK();
//		tellodrone.takeoff();
		System.out.println("Drone Takes Off");
//		tellodrone.hoverInPlace(10);
		System.out.println("Drone Hovers in Place for 10 seconds");
//		tellodrone.turnCW(135);
		System.out.println("Turn clockwise 135 degrees");
//		tellodrone.gotoXY((int)pxToFt(xd1), (int)pxToFt(yd1), 150);
		System.out.println("Drone goes to corner of property");
//		tellodrone.turnCCW(180));
		System.out.println("Turn counter-clockwise 180 degrees");
//		tellodrone.gotoXY((int)pxToFt(droneStartX1), (int)pxToFt(droneStartY1), 150);
		System.out.println("Drone goes to start position");		
//		tellodrone.turnCW(45);
		System.out.println("Turn clockwise 45 degrees");
//		tellodrone.gotoXY((int)pxToFt(droneStartX1), (int)pxToFt(yd1), 150);
		System.out.println("Drone goes to bottom of first row");
//		tellodrone.turnCCW(90);
		System.out.println("Turn counter-clockwise 90 degrees");
//		tellodrone.gotoXY((int)pxToFt(droneStartX1+xd1), (int)pxToFt(yd1), 150);
		System.out.println("Drone goes to bottom of second row");
//		tellodrone.turnCCW(90);
		System.out.println("Turn counter-clockwise 90 degrees");
//		tellodrone.gotoXY((int)pxToFt(droneStartX1+xd1), (int)pxToFt(droneStartY1), 150);
		System.out.println("Drone goes to top of second row");
//		tellodrone.turnCW(90);
		System.out.println("Turn clockwise 90 degrees");
//		tellodrone.gotoXY((int)pxToFt(droneStartX1+2*xd1), (int)pxToFt(droneStartY1), 150);
		System.out.println("Drone goes to top of third row");
//		tellodrone.turnCW(90);
		System.out.println("Turn clockwise 90 degrees");
//		tellodrone.gotoXY((int)pxToFt(droneStartX1+2*xd1), (int)pxToFt(yd1), 150);
		System.out.println("Drone goes to bottom of third row");
//		tellodrone.turnCCW(90);
		System.out.println("Turn counter-clockwise 90 degrees");
//		tellodrone.gotoXY((int)pxToFt(droneStartX1+3*xd1), (int)pxToFt(yd1), 150);
		System.out.println("Drone goes to bottom of fourth row");
//		tellodrone.turnCCW(90);
		System.out.println("Turn counter-clockwise 90 degrees");
//		tellodrone.gotoXY((int)pxToFt(droneStartX1+3*xd1), (int)pxToFt(droneStartY1), 150);
		System.out.println("Drone goes to top of fourth row");
//		tellodrone.turnCW(90);
		System.out.println("Turn clockwise 90 degrees");
//		tellodrone.gotoXY((int)pxToFt(droneStartX+4*xd1), (int)pxToFt(droneStartY1), 150);
		System.out.println("Drone goes to top of fifth row");
//		tellodrone.turnCW(90);
		System.out.println("Turn clockwise 90 degrees");
//		tellodrone.gotoXY((int)pxToFt(droneStartX1+4*xd1), (int)pxToFt(yd1), 150);
		System.out.println("Drone goes to bottom of fifth row");
//		tellodrone.turnCCW(90);
		System.out.println("Turn counter-clockwise 90 degrees");
//		tellodrone.gotoXY((int)pxToFt(droneStartX1+5*xd1), (int)pxToFt(yd1), 150);
		System.out.println("Drone goes to bottom of sixth row");
//		tellodrone.turnCCW(90);
		System.out.println("Turn counter-clockwise 90 degrees");
//		tellodrone.gotoXY((int)pxToFt(droneStartX1+5*xd1), (int)pxToFt(droneStartY1), 150);
		System.out.println("Drone goes to top of sixth row");
//		tellodrone.turnCCW(90);
		System.out.println("Turn counter-clockwise 90 degrees");
//		tellodrone.gotoXY((int)pxToFt(droneStartX1), (int)pxToFt(droneStartY1), 150);
		System.out.println("Drone goes to starting position");
//		tellodrone.turnCCW(135);
		System.out.println("Turn clockwise 135 degrees");
//		tellodrone.gotoXY((int)pxToFt(droneStartX1), (int)pxToFt(droneStartY1), 150);
		System.out.println("Drone goes to starting position");
//		tellodrone.turnCCW(135);
		System.out.println("Turn clockwise 135 degrees");
//		tellodrone.gotoXY((int)pxToFt(ccx1), (int)pxToFt(ccy1), 150);
		System.out.println("Drone returns to Command Center at " + ccx1 + ", " + ccy1);
//		tellodrone.land();
		System.out.println("Drone lands");
		tellodrone.end();
	}
	
	@Override
	public void DroneToItem () {
		
		double ccx1 = View.cc.getXPosition();
		double ccy1 = View.cc.getYPosition();
		double itemx = View.getCurrentItemXPosition();
    	double itemy = View.getCurrentItemYPosition();
    	
//		tellodrone.activateSDK();
//		tellodrone.takeoff();
		System.out.println("Drone Takes Off");
//		tellodrone.hoverInPlace(10);
		System.out.println("Drone Hovers in Place for 10 seconds");
//		tellodrone.turnCW(135);
		System.out.println("Turn clockwise 135 degrees");
//		tellodrone.gotoXY((int)pxToFt(itemx), (int)pxToFt(itemy), 150);
		System.out.println("Drone goes to Item at " + itemx + ", " + itemy);
//		tellodrone.turnCW(180);
		System.out.println("Turn clockwise 180 degrees");
//		tellodrone.gotoXY((int)pxToFt(ccx1), (int)pxToFt(ccy1), 150);
		System.out.println("Drone returns to Command Center at " + ccx1 + ", " + ccy1);
//		tellodrone.land();
		System.out.println("Drone lands");	
		tellodrone.end();
	}

}
