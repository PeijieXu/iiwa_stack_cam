package utilities;

import com.kuka.roboticsAPI.deviceModel.*;

import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.motionModel.IMotionContainer;
import com.kuka.roboticsAPI.motionModel.PositionHold;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;


import utilities.Timedelay;

/**
 * Program to generate the plastic motion of the robot.
 * Plastic Motion : robot will be under compliance. When affected by the forces and changed its position and/or orientation, 
 * robot DOES NOT go to the original position.   
 */

public class SafetyTest implements Runnable {
		private boolean exit; 
	    private String name; 
	    private TcpipComm tcp;
	    Thread t; 
	    
	    //ip_address = "192.168.10.11";	// this is pc ip
	  	private String ip_address = "192.168.10.8";	// this is pc ip
	  	private int port;
	  
	    public SafetyTest(String name) 
	    { 
	    	this.port = 30010; // change this
	    	this.tcp =  new TcpipComm(this.ip_address, port, "client");
	    	tcp.establishConnection();
	        this.name = name; 
	        t = new Thread(this, this.name); 
	        exit = false; 
	        t.start(); // Starting the thread 
	    } 
	
    public void run() {
        while(!exit)
    	{
			while (!exit)
			{
				String error = tcp.getStringRequest();
				tcp.sendStringResponse("Message received");
				//System.out.println(error);
				
				if(error.equalsIgnoreCase("error"))
				{
					// make it stop
					Timedelay.wait_milliseconds(20000);
				}
			}
			t.stop();
		}
    }
    
    public void stop() 
    { 
        exit = true; 
    } 

}
