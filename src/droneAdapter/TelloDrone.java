package droneAdapter;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TelloDrone extends MultiRotorDrone {
	
	private final int maxGoto = 500, minGoto = -500, minDist = 20, maxSpeed = 100, minSpeed = 10, maxDegrees = 360, minDegrees = 1;
	private final int maxDist = maxGoto;
	
	/***
	 * 
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public TelloDrone() throws SocketException, UnknownHostException {
		this.controller = new DroneController(9000, 11111, 8889, "192.168.10.1");
	}
	
	/***
	 * 
	 * @throws IOException
	 */
	public void activateSDK() throws IOException {
		this.controller.sendCommand("command");
	}
	
	public void end() {
		this.controller.closeSockets();
		System.out.println("Exit Program...");
	}
	
	@Override
	public void takeoff() throws IOException {
		this.controller.sendCommand("takeoff");
	}

	@Override
	public void land() throws IOException {
		this.controller.sendCommand("land");
	}
	
	/***
	 * 
	 * @throws IOException
	 */
	public void streamOn() throws IOException {
		this.controller.sendCommand("streamon");
	}
	
	/***
	 * 
	 * @throws IOException
	 */
	public void streamOff() throws IOException {
		this.controller.sendCommand("streamoff");
	}
	
	/***
	 * 
	 * @throws IOException
	 */
	public void missionPadOn() throws IOException {
		this.controller.sendCommand("mon");
	}

	/***
	 * 
	 * @throws IOException
	 */
	public void missionPadOff() throws IOException {
		this.controller.sendCommand("moff");
	}
	
	/***
	 * 
	 * @param param
	 * @throws IOException
	 */
	public void missionPadDirection(int param) throws IOException {
		this.controller.sendCommand("mdirection " + param);
	}

	@Override
	public void increaseAltitude(int up) throws IOException {
		if (up <= minDist) this.controller.sendCommand("up " + minDist);
		else if (up > maxDist) {
			this.controller.sendCommand("up " + maxDist);
			increaseAltitude(Math.abs(maxDist - up));
		}
		else this.controller.sendCommand("up " + up);
	}

	@Override
	public void decreaseAltitude(int down) throws IOException {
		double height = getHeight();
		if (height - down <= 0) down = (int) (height - 10); 
		if (down <= minDist) this.controller.sendCommand("down " + minDist);
		else if (down > maxDist) {
			this.controller.sendCommand("down " + maxDist);
			decreaseAltitude(Math.abs(maxDist - down));
		}
		else this.controller.sendCommand("down " + down);
	}

	@Override
	public void flyForward(int front) throws IOException {
		if (front <= minDist) this.controller.sendCommand("forward " + minDist);
		else if (front > maxDist) {
			this.controller.sendCommand("forward " + maxDist);
			flyForward(Math.abs(maxDist - front));
		}
		else this.controller.sendCommand("forward " + front);
	}
	
	@Override
	public void flyBackward(int back) throws IOException {
		if (back <= minDist) this.controller.sendCommand("back " + minDist);
		else if (back > maxDist) {
			this.controller.sendCommand("back " + maxDist);
			flyBackward(Math.abs(maxDist - back));
		}
		else this.controller.sendCommand("back " + back);
	}

	@Override
	public void flyLeft(int left) throws IOException {
		if (left <= minDist) this.controller.sendCommand("left " + minDist);
		else if (left > maxDist) {
			this.controller.sendCommand("left " + maxDist);
			flyLeft(Math.abs(maxDist - left));
		}
		else this.controller.sendCommand("left " + left);
	}

	@Override
	public void flyRight(int right) throws IOException {
		if (right <= minDist) this.controller.sendCommand("right " + minDist);
		else if (right > maxDist) {
			this.controller.sendCommand("right " + maxDist);
			flyRight(Math.abs(maxDist - right));
		}
		else this.controller.sendCommand("right " + right);
	}

	@Override
	public void turnCW(int degrees) throws IOException {
		if (degrees <= minDegrees) this.controller.sendCommand("cw " + minDegrees);
		else if (degrees > maxDegrees) {
			this.controller.sendCommand("cw " + maxDegrees);
			turnCW(Math.abs(maxDegrees - degrees));
		}
		else this.controller.sendCommand("cw " + degrees);
	}

	@Override
	public void turnCCW(int degrees) throws IOException {
		if (degrees <= minDegrees) this.controller.sendCommand("ccw " + minDegrees);
		else if (degrees > maxDegrees) {
			this.controller.sendCommand("ccw " + maxDegrees);
			turnCCW(Math.abs(maxDegrees - degrees));
		}
		else this.controller.sendCommand("ccw " + degrees);
	}
	
	/***
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param speed
	 * @throws IOException 
	 */
	public void gotoXYZ(int x, int y, int z, int speed) throws IOException {
		int zNext = 0;
//		if (getHeight() + z <= 0) z = 10 - getHeight();
		if (z < minGoto) {
			zNext = z + maxGoto;
			z = minGoto;
		}	
		else if (z > maxGoto) {
			zNext = z + minGoto;
			z = maxGoto;
		}
		double slope = (double)y/x;
		//System.out.println(slope);
		if (speed > maxSpeed) speed = maxSpeed;
		else if (speed < minSpeed) speed = minSpeed;
		if (x <= maxGoto && x >= minGoto && y <= maxGoto && y >= minGoto) {
			System.out.println(String.format("go %1$d %2$d %3$d %4$d", x, -y, z, speed)); //this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", x, -y, z, speed));
			if (zNext > 0) gotoXYZ(0, 0, zNext, speed);
		}
		else if ((x > maxGoto && y <= maxGoto && y >= minGoto) || ((x > maxGoto || x < minGoto) && (y > maxGoto || y < minGoto) && (Math.abs(x) > Math.abs(y)) && (x > maxGoto))) {
			int partialY = (int) Math.round(slope * maxGoto);
			System.out.println(String.format("go %1$d %2$d %3$d %4$d", maxGoto, -partialY, z, speed));
			//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", maxGoto, -partialY, z, speed));
			gotoXYZ(x + minGoto, y - partialY, zNext, speed);
		}
		else if ((x < minGoto && y <= maxGoto && y >= minGoto) || (((x > maxGoto || x < minGoto) && (y > maxGoto || y < minGoto)) && (Math.abs(x) > Math.abs(y)) && (x < minGoto))) {
			int partialY = (int) Math.round(slope * minGoto);
			System.out.println(String.format("go %1$d %2$d %3$d %4$d", minGoto, -partialY, z, speed));
			//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", minGoto, partialY, z, speed));
			gotoXYZ(x + maxGoto, y - partialY, zNext, speed);
		}
		else if ((y > maxGoto && x <= maxGoto && x >= minGoto) || (((x > maxGoto || x < minGoto) && (y > maxGoto || y < minGoto)) && (Math.abs(y) > Math.abs(x)) && (y > maxGoto))) {
			int partialX = (int) Math.round(maxGoto/slope);
			System.out.println(String.format("go %1$d %2$d %3$d %4$d", partialX, minGoto, z, speed));
			//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", partialX, minGoto, z, speed));
			gotoXYZ(x - partialX, y + minGoto, zNext, speed);
		}
		else if ((y < minGoto && x <= maxGoto && x >= minGoto) || (((x > maxGoto || x < minGoto) && (y > maxGoto || y < minGoto)) && (Math.abs(y) > Math.abs(x)) && (y < maxGoto))) {
			int partialX = (int) Math.round(minGoto/slope);
			System.out.println(String.format("go %1$d %2$d %3$d %4$d", partialX, maxGoto, z, speed));
			//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", partialX, maxGoto, z, speed));
			gotoXYZ(x - partialX, y + maxGoto, zNext, speed);
		}
		else {
			if (x > maxGoto && y < minGoto) {
				System.out.println(String.format("go %1$d %2$d %3$d %4$d", maxGoto, maxGoto, z, speed));
				//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", maxGoto, maxGoto, z, speed));
				gotoXYZ(x + minGoto, y + maxGoto, zNext, speed);
			}
			else if (x < minGoto && y > maxGoto) {
				System.out.println(String.format("go %1$d %2$d %3$d %4$d", minGoto, minGoto, z, speed));
				//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", minGoto, minGoto, z, speed));
				gotoXYZ(x + maxGoto, y + minGoto, zNext, speed);
			}
			else if (x > maxGoto && x == y) {
				System.out.println(String.format("go %1$d %2$d %3$d %4$d", maxGoto, minGoto, z, speed));
				//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", maxGoto, minGoto, z, speed));
				gotoXYZ(x + minGoto, y + minGoto, zNext, speed);
			}
			else {
				System.out.println(String.format("go %1$d %2$d %3$d %4$d", minGoto, maxGoto, z, speed));
				//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", minGoto, maxGoto, z, speed));
				gotoXYZ(x + maxGoto, y + maxGoto, zNext, speed);
			}
		}
	}
	
	/***
	 * 
	 * @param x
	 * @param y
	 * @param speed
	 * @throws IOException 
	 */
	public void gotoXY(int x, int y, int speed) throws IOException {
		int z = 0;
		double slope = (double)y/x;
		//System.out.println(slope);
		if (speed > maxSpeed) speed = maxSpeed;
		else if (speed < minSpeed) speed = minSpeed;
		if (x <= maxGoto && x >= minGoto && y <= maxGoto && y >= minGoto) System.out.println(String.format("go %1$d %2$d %3$d %4$d", x, -y, z, speed)); //this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", x, -y, z, speed));
		else if ((x > maxGoto && y <= maxGoto && y >= minGoto) || ((x > maxGoto || x < minGoto) && (y > maxGoto || y < minGoto) && (Math.abs(x) > Math.abs(y)) && (x > maxGoto))) {
			int partialY = (int) Math.round(slope * maxGoto);
			System.out.println(String.format("go %1$d %2$d %3$d %4$d", maxGoto, -partialY, z, speed));
			//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", maxGoto, -partialY, z, speed));
			gotoXY(x + minGoto, y - partialY, speed);
		}
		else if ((x < minGoto && y <= maxGoto && y >= minGoto) || (((x > maxGoto || x < minGoto) && (y > maxGoto || y < minGoto)) && (Math.abs(x) > Math.abs(y)) && (x < minGoto))) {
			int partialY = (int) Math.round(slope * minGoto);
			System.out.println(String.format("go %1$d %2$d %3$d %4$d", minGoto, -partialY, z, speed));
			//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", minGoto, partialY, z, speed));
			gotoXY(x + maxGoto, y - partialY, speed);
		}
		else if ((y > maxGoto && x <= maxGoto && x >= minGoto) || (((x > maxGoto || x < minGoto) && (y > maxGoto || y < minGoto)) && (Math.abs(y) > Math.abs(x)) && (y > maxGoto))) {
			int partialX = (int) Math.round(maxGoto/slope);
			System.out.println(String.format("go %1$d %2$d %3$d %4$d", partialX, minGoto, z, speed));
			//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", partialX, minGoto, z, speed));
			gotoXY(x - partialX, y + minGoto, speed);
		}
		else if ((y < minGoto && x <= maxGoto && x >= minGoto) || (((x > maxGoto || x < minGoto) && (y > maxGoto || y < minGoto)) && (Math.abs(y) > Math.abs(x)) && (y < maxGoto))) {
			int partialX = (int) Math.round(minGoto/slope);
			System.out.println(String.format("go %1$d %2$d %3$d %4$d", partialX, maxGoto, z, speed));
			//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", partialX, maxGoto, z, speed));
			gotoXY(x - partialX, y + maxGoto, speed);
		}
		else {
			if (x > maxGoto && y < minGoto) {
				System.out.println(String.format("go %1$d %2$d %3$d %4$d", maxGoto, maxGoto, z, speed));
				//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", maxGoto, maxGoto, z, speed));
				gotoXY(x + minGoto, y + maxGoto, speed);
			}
			else if (x < minGoto && y > maxGoto) {
				System.out.println(String.format("go %1$d %2$d %3$d %4$d", minGoto, minGoto, z, speed));
				//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", minGoto, minGoto, z, speed));
				gotoXY(x + maxGoto, y + minGoto, speed);
			}
			else if (x > maxGoto && x == y) {
				System.out.println(String.format("go %1$d %2$d %3$d %4$d", maxGoto, minGoto, z, speed));
				//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", maxGoto, minGoto, z, speed));
				gotoXY(x + minGoto, y + minGoto, speed);
			}
			else {
				System.out.println(String.format("go %1$d %2$d %3$d %4$d", minGoto, maxGoto, z, speed));
				//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", minGoto, maxGoto, z, speed));
				gotoXY(x + maxGoto, y + maxGoto, speed);
			}
		}
	}
	
	/***
	 * SDK 2.0 only
	 * @param x
	 * @param y
	 * @param z
	 * @param speed
	 * @param ID
	 * @throws IOException
	 */
	public void gotoMissionPadXYZ(int x, int y, int z, int speed, String ID) throws IOException {
		// TODO Implement in future release
	}
	
	/***
	 * SDK 2.0 only
	 * @param x
	 * @param y
	 * @param speed
	 * @param ID
	 * @throws IOException
	 */
	public void gotoMissionPadXY(int x, int y, int speed, String ID) throws IOException {
		int z = 0;
		double slope = (double)y/x;
		//System.out.println(slope);
		if (speed > maxSpeed) speed = maxSpeed;
		else if (speed < minSpeed) speed = minSpeed;
		if (x <= maxGoto && x >= minGoto && y <= maxGoto && y >= minGoto) System.out.println(String.format("go %1$d %2$d %3$d %4$d", x, -y, z, speed)); //this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", x, -y, z, speed));
		else if ((x > maxGoto && y <= maxGoto && y >= minGoto) || ((x > maxGoto || x < minGoto) && (y > maxGoto || y < minGoto) && (Math.abs(x) > Math.abs(y)) && (x > maxGoto))) {
			int partialY = (int) Math.round(slope * maxGoto);
			System.out.println(String.format("go %1$d %2$d %3$d %4$d %5$s", maxGoto, -partialY, z, speed, ID));
			//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d %5$s", maxGoto, -partialY, z, speed, ID));
			gotoMissionPadXY(x + minGoto, y - partialY, speed, ID);
		}
		else if ((x < minGoto && y <= maxGoto && y >= minGoto) || (((x > maxGoto || x < minGoto) && (y > maxGoto || y < minGoto)) && (Math.abs(x) > Math.abs(y)) && (x < minGoto))) {
			int partialY = (int) Math.round(slope * minGoto);
			System.out.println(String.format("go %1$d %2$d %3$d %4$d %5$s", minGoto, -partialY, z, speed, ID));
			//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d %5$s", minGoto, partialY, z, speed, ID));
			gotoMissionPadXY(x + maxGoto, y - partialY, speed, ID);
		}
		else if ((y > maxGoto && x <= maxGoto && x >= minGoto) || (((x > maxGoto || x < minGoto) && (y > maxGoto || y < minGoto)) && (Math.abs(y) > Math.abs(x)) && (y > maxGoto))) {
			int partialX = (int) Math.round(maxGoto/slope);
			System.out.println(String.format("go %1$d %2$d %3$d %4$d %5$s", partialX, minGoto, z, speed, ID));
			//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d", partialX, minGoto, z, speed));
			gotoMissionPadXY(x - partialX, y + minGoto, speed, ID);
		}
		else if ((y < minGoto && x <= maxGoto && x >= minGoto) || (((x > maxGoto || x < minGoto) && (y > maxGoto || y < minGoto)) && (Math.abs(y) > Math.abs(x)) && (y < maxGoto))) {
			int partialX = (int) Math.round(minGoto/slope);
			System.out.println(String.format("go %1$d %2$d %3$d %4$d %5$s", partialX, maxGoto, z, speed, ID));
			//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d %5$s", partialX, maxGoto, z, speed, ID));
			gotoMissionPadXY(x - partialX, y + maxGoto, speed, ID);
		}
		else {
			if (x > maxGoto && y < minGoto) {
				System.out.println(String.format("go %1$d %2$d %3$d %4$d %5$s", maxGoto, maxGoto, z, speed, ID));
				//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d %5$s", maxGoto, maxGoto, z, speed, ID));
				gotoMissionPadXY(x + minGoto, y + maxGoto, speed, ID);
			}
			else if (x < minGoto && y > maxGoto) {
				System.out.println(String.format("go %1$d %2$d %3$d %4$d %5$s", minGoto, minGoto, z, speed, ID));
				//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d %5$s", minGoto, minGoto, z, speed, ID));
				gotoMissionPadXY(x + maxGoto, y + minGoto, speed, ID);
			}
			else if (x > maxGoto && x == y) {
				System.out.println(String.format("go %1$d %2$d %3$d %4$d %5$s", maxGoto, minGoto, z, speed, ID));
				//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d %5$s", maxGoto, minGoto, z, speed, ID));
				gotoMissionPadXY(x + minGoto, y + minGoto, speed,ID);
			}
			else {
				System.out.println(String.format("go %1$d %2$d %3$d %4$d %5$s", minGoto, maxGoto, z, speed, ID));
				//this.controller.sendCommand(String.format("go %1$d %2$d %3$d %4$d %5$s", minGoto, maxGoto, z, speed, IDcomm));
				gotoMissionPadXY(x + maxGoto, y + maxGoto, speed, ID);
			}
		}
	}
	
	/***
	 * 
	 * @param x1
	 * @param y1
	 * @param z1
	 * @param x2
	 * @param y2
	 * @param z2
	 * @param speed
	 */
	public void flyCurve(int x1, int y1, int z1, int x2, int y2, int z2, int speed) {
		// TODO Implement in future release
	}
	
	@Override
	public void flip(String direction) throws IOException {
		this.controller.sendCommand("flip " + direction);
	}
	
	/***
	 * SDK 2.0 only
	 * @param x1
	 * @param y1
	 * @param z1
	 * @param x2
	 * @param y2
	 * @param z2
	 * @param speed
	 * @param ID
	 * @throws IOException
	 */
	public void flyCurveMissionPad(int x1, int y1, int z1, int x2, int y2, int z2, int speed, String ID) throws IOException {
		// TODO Implement in future release
	}

	/***
	 * SDK 2.0 only
	 * @param x
	 * @param y
	 * @param z
	 * @param speed
	 * @param yaw
	 * @param ID1
	 * @param ID2
	 */
	public void jumpMissionPad(int x, int y, int z, int speed, int yaw, String ID1, String ID2) {
		// TODO Implement in future release
	}
	
	/***
	 * using getBattery() to reset watchdog failsafe
	 */
	@Override
	public void hoverInPlace(int seconds) throws InterruptedException, IOException {
		if (seconds > 15) {
			getBattery();
			TimeUnit.MILLISECONDS.sleep(14970); // less than exactly 15 sec to prevent failsafe landing
			hoverInPlace(Math.abs(seconds - 15));
		}
		else if (seconds == 15) {
			getBattery();
			TimeUnit.MILLISECONDS.sleep(14970); // less than exactly 15 sec to prevent failsafe landing
			return;
		}
		else {
			getBattery();
			TimeUnit.SECONDS.sleep(seconds);
			return;
		}
	}
	
	/***
	 * Actual interrupt, will determine usefulness in future release
	 * @throws IOException
	 */
	public void stopInPlace() throws IOException {
		this.controller.sendCommand("stop");
	}
	
	/***
	 * EMERGENCY ONLY!!! Shuts down all motors even mid flight
	 * @throws IOException
	 */
	public void emergencyStop() throws IOException {
		this.controller.sendCommand("emergency");
	}
	
	@Override
	public void setSpeed(int speed) throws IOException {
		if (speed <= minSpeed) this.controller.sendCommand("speed " + minSpeed);
		else if (speed > maxSpeed) {
			this.controller.sendCommand("speed " + maxSpeed);
		}
		else this.controller.sendCommand("speed " + speed);
	}
	
	/***
	 * 
	 * @param SSID
	 * @param password
	 * @throws IOException
	 */
	public void setWifi(String SSID, String password) throws IOException {
		this.controller.sendCommand("wifi " + SSID + " " + password);
	}
	
	/***
	 * 
	 * @param SSID
	 * @param password
	 * @throws IOException
	 */
	public void setAccessPoint(String SSID, String password) throws IOException {
		this.controller.sendCommand("ap " + SSID + " " + password);
	}
	
	public double getSpeed() throws IOException {
		try {
			return Double.parseDouble(this.controller.sendCommand("speed?"));
		} catch (NumberFormatException e) {
			System.out.println("Number Format Exception due to timeout! " + e);
			return 0;
		}
	}

	@Override
	public int getBattery() throws IOException {
		try {
			return Integer.parseInt(this.controller.sendCommand("battery?"));
		} catch (NumberFormatException e) {
			System.out.println("Number Format Exception due to timeout! " + e);
			return 0;
		}
	}
	
	/***
	 * 
	 * @return
	 * @throws IOException
	 */
	public int getTemp() throws IOException {
		try {
			String temperature = this.controller.sendCommand("temp?");
			String[] arrayOfStr = temperature.split("~|C", 2);
			int temp1 = Integer.parseInt(arrayOfStr[0]);
			int temp2 = Integer.parseInt(arrayOfStr[1].substring(0, arrayOfStr[1].length() - 1));
			return (temp1 + temp2)/2;
		} catch (NumberFormatException e) {
			System.out.println("Number Format Exception due to timeout! " + e);
			return 0;
		}
	}
	
	/***
	 * 
	 * @return
	 * @throws IOException
	 */
	public double getBarometer() throws IOException {
		try {
			return Double.parseDouble(this.controller.sendCommand("baro?"));
		} catch (NumberFormatException e) {
			System.out.println("Number Format Exception due to timeout! " + e);
			return 0;
		}
	}
	
	@Override
	public double getFlightTime() throws IOException {
		try {
			String time = this.controller.sendCommand("time?");
			return Integer.parseInt(time.substring(0, time.length() - 1));
		} catch (NumberFormatException e) {
			System.out.println("Number Format Exception due to timeout! " + e);
			return 0;
		}
	}

	@Override
	public double getHeight() throws IOException {
		try {
			String height = this.controller.sendCommand("height?");
			return 10 * Integer.parseInt(height.substring(0, height.length() - 2));
		} catch (NumberFormatException e) {
			System.out.println("Number Format Exception due to timeout! " + e);
			return 0;
		}
	}

	@Override
	public double getAttitudePitch() throws IOException {
		try {
			String attitude = this.controller.sendCommand("attitude?");
			String[] arrayOfStr = attitude.split(":|;", 7);
			return Integer.parseInt(arrayOfStr[1]);
		} catch (Exception e) {
			System.out.println("Exception due to timeout! " + e);
			return 0;
		}
	}

	@Override
	public double getAttitudeRoll() throws IOException {
		try {
			String attitude = this.controller.sendCommand("attitude?");
			String[] arrayOfStr = attitude.split(":|;", 7);
			return Integer.parseInt(arrayOfStr[3]);
		} catch (Exception e) {
			System.out.println("Exception due to timeout! " + e);
			return 0;
		}
	}

	@Override
	public double getAttitudeYaw() throws IOException {
		try {
			String attitude = this.controller.sendCommand("attitude?");
			String[] arrayOfStr = attitude.split(":|;", 7);
			return Integer.parseInt(arrayOfStr[5]);
		} catch (Exception e) {
			System.out.println("Exception due to timeout! " + e);
			return 0;
		}
	}

	@Override
	public double getAccelerationX() throws IOException {
		try {
			String acceleration = this.controller.sendCommand("acceleration?");
			String[] arrayOfStr = acceleration.split(":|;", 7);
			return Double.parseDouble(arrayOfStr[1]);
		} catch (Exception e) {
			System.out.println("Exception due to timeout! " + e);
			return 0;
		}
	}
	
	@Override
	public double getAccelerationY() throws IOException {
		try {
			String acceleration = this.controller.sendCommand("acceleration?");
			String[] arrayOfStr = acceleration.split(":|;", 7);
			return Double.parseDouble(arrayOfStr[3]);
		} catch (Exception e) {
			System.out.println("Exception due to timeout! " + e);
			return 0;
		}
	}

	@Override
	public double getAccelerationZ() throws IOException {
		try {
			String acceleration = this.controller.sendCommand("acceleration?");
			String[] arrayOfStr = acceleration.split(":|;", 7);
			return Double.parseDouble(arrayOfStr[5]);
		} catch (Exception e) {
			System.out.println("Exception due to timeout! " + e);
			return 0;
		}
	}

	@Override
	public int getTOF() throws IOException {
		try {
			String timeOfFlight = this.controller.sendCommand("tof?");
			return (Integer.parseInt(timeOfFlight.substring(0, timeOfFlight.length() - 2)))/10;
		} catch (NumberFormatException e) {
			System.out.println("Number Format Exception due to timeout! " + e);
			return 0;
		}
	}
	
	/***
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getWIFI() throws IOException {
		return this.controller.sendCommand("wifi?");
	}

	/***
	 * SDK 2.0 only
	 * @return
	 * @throws IOException
	 */
	public String getVersionSDK() throws IOException {
		return this.controller.sendCommand("sdk?");
	}

	/***
	 * SDK 2.0 only
	 * @return
	 * @throws IOException
	 */
	public String getSerialNumber() throws IOException {
		return this.controller.sendCommand("sn?");
	}
	
	/***
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		TelloDrone tello = new TelloDrone();
		
		System.out.println("Tello Drone Demo" + "\n");
		System.out.println("Tello: command takeoff land flip forward back left right" + "\n" + "      " + " up down cw ccw speed speed?" + "\n");
		System.out.println("end -- quit demo" + "\n");
		
		Scanner scan = new Scanner(System.in);

		String command = scan.nextLine();

		while(!command.equals("end") && command != null && !command.trim().isEmpty()) {
			tello.controller.sendCommand(command);
			command = scan.nextLine();
		}

		scan.close();
		tello.controller.closeSockets();
		System.out.println("Exit Program...");
	}
	
}
