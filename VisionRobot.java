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
 
public class VisionRobot extends Robot
{
 
    Command autonomousCommand;
    SendableChooser chooser;
   
    //NetworkTable table;
    static int session;
    static Image frame;
    static AxisCamera camera;
   
 
    double[] defaultValue = new double [0];
    double[] areas;
    static double[] centerXs;
    static double centerX;
    static double width;
    double distance;
    boolean position = false;
    final double MAGIC_DISTANCE_NUMBER = 1.08;
    final double MAGIC_AREA_NUMBER = 1.429;
    public static int state = 0;
    public static int temp = 0;
 
    //NetworkTable grip = NetworkTable.getTable("GRIP/myContoursReport");
	static NetworkTable table = NetworkTable.getTable("GRIP");
    
    public static double getDistance()
    {
        		
                        double[] widths = table.getNumberArray("myContoursReport/width", new double[0]);
                        int temp = 0;
                        for(int i = 0;i < widths.length; i++)
                        {
                            if(widths[i] > widths[temp])
                            {
                                temp = i;
                            }
                        }
                        //temp is the position of the largest number in widths

                        System.out.println("width: " + widths[temp] + "\ndistance: " + ((140.0/118.0)*(1.08*(20*640)/widths[temp])));
                        //Timer.delay(2);
                        return (140.0/118.0)*(1.08*(20*640)/widths[temp]);
                }
    public static void alignRobot(){
    	temp = 0;
        for(int i = 0; i < centerXs.length; i++)
        {
            if(centerXs[i] > centerXs[temp])
            {
                temp = i;
            }
        }
        //temp is the position of the largest number in center x's

        System.out.println("Center X: " + centerXs[temp]);

        if(centerXs[temp] <= 310)
            {
                frontRightMotor.set(0.22);
                frontLeftMotor.set(0.22);
                backRightMotor.set(0.22);
                backLeftMotor.set(0.22);
                System.out.println("Turning left");
            }
            else if(centerXs[temp] >= 330)
            {
                frontRightMotor.set(-0.22);
                frontLeftMotor.set(-0.22);
                backRightMotor.set(-0.22);
                backLeftMotor.set(-0.22);
                System.out.println("Turing Right");
            }
            else
            {
                frontRightMotor.set(0);
                frontLeftMotor.set(0);
            backRightMotor.set(0);
            backLeftMotor.set(0);
            System.out.println("Stop and state is 1");
        }

        temp = 0;
    }

}
