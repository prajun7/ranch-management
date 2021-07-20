package droneAdapter;

import java.io.IOException;

public abstract class PhysicalDrone {
	
	DroneController controller;
	
	/***
	 * 
	 * @param speed
	 * @throws IOException 
	 */
	public abstract void setSpeed(int speed) throws IOException;
	
	/***
	 * 
	 * @return
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public abstract double getSpeed() throws NumberFormatException, IOException;
	
	/***
	 * 
	 * @return
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public abstract int getBattery() throws NumberFormatException, IOException;
	
	
	
}
