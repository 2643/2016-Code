package org.usfirst.frc.team2643.robot;

/*
*	Written by Adley and Niko
*
*/

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import java.io.IOException;
 
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
 
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;
 
public class Robot extends IterativeRobot
{
 
    Command autonomousCommand;
    SendableChooser chooser;
   
    //NetworkTable table;
    int session;
    Image frame;
    AxisCamera camera;
   
    Joystick stick = new Joystick(1);
   
    int state = 0;
 
    double[] defaultValue = new double [0];
    double[] areas;
    int temp = 0;
    double[] centerXs;
    double centerX;
    double width;
    double distance;
    boolean position = false;
    double MAGIC_DISTANCE_NUMBER = 1.08;
    double MAGIC_AREA_NUMBER = 1.429;
 
    //NetworkTable grip = NetworkTable.getTable("GRIP/myContoursReport");
	NetworkTable table = NetworkTable.getTable("GRIP");

    
    
   public void robotInit()
   {
        chooser = new SendableChooser();
        SmartDashboard.putData("Auto mode", chooser);
        
        try 
        {
            new ProcessBuilder("/home/lvuser/grip").inheritIO().start();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void disabledInit()
    {
 
    }
   
    public void disabledPeriodic()
    {
        Scheduler.getInstance().run();
    }

    public void autonomousInit()
    {
        autonomousCommand = (Command) chooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }
 
    public void autonomousPeriodic()
    {
        Scheduler.getInstance().run();
    }
 
    public void teleopInit()
    {
        if (autonomousCommand != null) autonomousCommand.cancel();
        Scheduler.getInstance().run();
    }
    
    public void teleopPeriodic()
    {
    	
        Scheduler.getInstance().run();
 
        while(isOperatorControl() && isEnabled())
        {      	 
        	for (double width : table.getNumberArray("myContoursReport/width", new double[0])) 
        	{
        		switch(state)
                {
                    case 0:
                        double[] widths = table.getNumberArray("myContoursReport/width", new double[0]);
                        temp = 0;
                        for(int i = 0;i < widths.length; i++)
                        {
                            if(widths[i] > widths[temp])
                            {
                                temp = i;
                            }
                        }

                        System.out.println("width: " + widths[temp] + "\ndistance: " + ((136.0/118.0)*(1.08*(20*320)/widths[temp])));
                        //Timer.delay(2);
                }
        	}
        }
    }
   
    public void testPeriodic() {
        LiveWindow.run();
    }
}