package droneAdapter;



/***
 * Convenience class for storing constants needed for piping, conversion, and flight
 * 09/30/2019 v1.0
 * @author MasterControlProgram: Seth Lewis
 *
 */

public class Constants {
		
	public static final int FARMWIDTH = 800; // farm width on screen in pixels 4:3 ratio
	public static final int FARMHEIGHT = 600; // farm height on screen in pixels 4:3 ratio
	public static final int FARMDEPTH = 0;
	
	public static final int MODELWIDTH = 32; // farm width physical model in feet 4:3 ratio
	public static final int MODELHEIGHT = 24; // farm height physical model in feet 4:3 ratio
	
	public static final int PIXELS_TO_ONE_MODEL_FOOT = 25; // on screen pixels to one real world foot
	public static final int CENTIMETERS_PER_MODEL_FOOT = 30; // centimeters to feet conversion for flight commands
	
}
