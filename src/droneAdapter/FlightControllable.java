package droneAdapter;

import java.io.IOException;

/***
 * Interface containing a selection of aircraft flight commands independent of type/manufacturer
 * 11/08/2019 v1.0
 * @author MasterControlProgram
 *
 */
interface FlightControllable {
	
	/***
	 * Sends the "takeoff" signal to an active aircraft
	 * Trying to fly in low light or high wind may cause errors
	 * Trying to take off with a low battery may fail
	 * @throws IOException 
	 */
	public void takeoff() throws IOException;
	
	/***
	 * Sends the "land" signal to an active aircraft
	 * Aircraft may land despite any obstacles below it or ahead
	 * Make sure your landing area is clear
	 * @throws IOException 
	 */
	public void land() throws IOException;
	
	/***
	 * 
	 * @param up
	 * @throws IOException 
	 */
	public void increaseAltitude(int up) throws IOException;
	
	/***
	 * 
	 * @param down
	 * @throws IOException 
	 */
	public void decreaseAltitude(int down) throws IOException;
	
	/***
	 * 
	 * @param front
	 * @throws IOException 
	 */
	public void flyForward(int front) throws IOException;
	
	/***
	 * 
	 * @param left
	 * @throws IOException 
	 */
	public void flyLeft(int left) throws IOException;
	
	/***
	 * 
	 * @param right
	 * @throws IOException 
	 */
	public void flyRight(int right) throws IOException;
	
	/***
	 * 
	 * @param degrees
	 * @throws IOException 
	 */
	public void turnCW(int degrees) throws IOException;
	
	/***
	 * 
	 * @param degrees
	 * @throws IOException 
	 */
	public void turnCCW(int degrees) throws IOException;
	
	/***
	 * 
	 * @return
	 * @throws IOException 
	 */
	public double getFlightTime() throws IOException;
	
	/***
	 * 
	 * @return
	 * @throws IOException 
	 */
	public double getHeight() throws IOException;
	
	/***
	 * 
	 * @return
	 * @throws IOException 
	 */
	public double getAttitudePitch() throws IOException;
	
	/***
	 * 
	 * @return
	 * @throws IOException 
	 */
	public double getAttitudeRoll() throws IOException;
	
	/***
	 * 
	 * @return
	 * @throws IOException 
	 */
	public double getAttitudeYaw() throws IOException;
	
	/***
	 * Gets aircraft's IMU angular acceleration data and returns the X component
	 * @return double precision acceleration value based on SDK units
	 * @throws IOException 
	 */
	public double getAccelerationX() throws IOException;
	
	/***
	 * Gets aircraft's IMU angular acceleration data and returns the Y component
	 * @return double precision acceleration value based on SDK units
	 * @throws IOException 
	 */
	public double getAccelerationY() throws IOException;
	
	/***
	 * Gets aircraft's IMU angular acceleration data and returns the Z component
	 * @return double precision acceleration value based on SDK units
	 * @throws IOException 
	 */
	public double getAccelerationZ() throws IOException;
	
	/***
	 * 
	 * @return
	 * @throws IOException
	 */
	public int getTOF() throws IOException;
		
}
