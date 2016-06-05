package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/*
 * written by: Adley Wong
 * @Team 2643
 */

/*
 * right joystick log - arcade
 * button 9 - tank
 * button 10 - arcade
 * right joystick - left side for tank
 * xbox left joystick - intake
 * xbox right joystick - shooter
 * becareful as there have been no debugging with the shooter yet....
 */

public class Robot extends IterativeRobot 
{
	public static Joystick operatorPad = new Joystick(1);
	
	//Button Magic Numbers
	public static int chevalPistonY = 4;
	
	//booleans
	public static boolean chevalToggle = false;
	public static boolean shooterToggle = false;
	public static boolean intakeToggle = false;
	public static boolean startCounting = false;
	
	//shooting and intake
	public static Victor shooterMotor = new Victor(5);
	public static Victor intakeMotor = new Victor(4);
	
	//timer
	public static Timer intakeTimer = new Timer();
	
	//Joysticks
	public static Joystick gamePad = new Joystick(0);
			
	//Drive Motors
	public static Talon rFrontMotor = new Talon(9);
	public static Talon lFrontMotor = new Talon(7);
	public static Talon rBackMotor = new Talon(8);
	public static Talon lBackMotor = new Talon(6);
		
	//booleans
	public static boolean driveToggle = false;
	public static boolean shiftingToggle = false;
	public static boolean on = true;
	public static boolean off = false;
	
	//Solenoids
	public static Solenoid rightShiftingSolenoid = new Solenoid(0,1);
	public static Solenoid leftShiftingSolenoid = new Solenoid(2,3);
	
	//Magic Number
	public static double intakeSpeed = 0.5;
	public static double upperSpeed = 0.4;
	public static double slow = 0.2;
	public static int topPoint = 100;
	public static double shootSpeed = 0.6;
	public static int state = 0;
	public static double moveSpeed = 0.4;
	public static int moveDistance = 325;
	public static int topSlide = 320;
	public static int lowSlideTreshold = 60;
	public static int temp = 0;
	public static double speed = 0.8;
	public static int timeToDefense = 4;
	public static int intakeTime = 2;
	
	//Button Magic Numbers
	public static int intakeButton = 7;
	public static int releaseButton = 5;
	public static int shootButton = 8;
	public static int reverseButton = 6;
	public static int tankD = 9;
	public static int arcadeD = 10;
	public static int fastShiftingButton = 7;
	public static int slowShiftingButton = 8;
			
	//timer
	public static Timer time = new Timer();
	
	//Drive
	//public static RobotDrive gamePadDrive = new RobotDrive(lFrontMotor, lBackMotor, rFrontMotor, rBackMotor);
	//fix 
	
	
	//compressor
	//static Compressor airCompressor = new Compressor(1);
	
	//NetWork Tables
	//public static NetworkTable table = NetworkTable.getTable("GRIP");
	
	//Camera declarations
    public static String cameraHost = "10.26.43.11";
    public static AxisCamera cam;
    public static Image frame;


    Command autonomousCommand;
    SendableChooser chooser;
    
    public void robotInit() 
    {
      /*  chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new ExampleCommand());
        SmartDashboard.putData("Auto mode", chooser);
        SmartDashboard.putBoolean("DB/Button 0", true);
        SmartDashboard.putBoolean("DB/Button 1", true);
        
        */
        //initization code
        //init();
    }

    public void disabledInit()
    {
    	
    }
	
	public void disabledPeriodic() 
	{
		Scheduler.getInstance().run();
	}
	
	public static void autoCode()
	{
		//timeToDefense
		if(time.get() <= timeToDefense)
		{
			rFrontMotor.set(0.65);
			rBackMotor.set(0.65);
			lFrontMotor.set(-0.65);
			lBackMotor.set(-0.65);	
		}
		else
		{
			lBackMotor.set(0);
			rBackMotor.set(0);
			lFrontMotor.set(0);
			rFrontMotor.set(0);
		}
		
		/*if(SmartDashboard.getBoolean( "DB/Button 0", false )  &&   time.get() >= timeToDefense) // It will not move
		{
			lBackMotor.set(0);
			rBackMotor.set(0);
			lFrontMotor.set(0);
			rFrontMotor.set(0);
		}
		else if(  SmartDashboard.getBoolean( "DB/Button 0", false ) && time.get() <= timeToDefense ) // It will go forwards
		{
			rFrontMotor.set(0.5);
			rBackMotor.set(0.5);
			lFrontMotor.set(-0.5);
			lBackMotor.set(-0.5);			
		}
		else if(SmartDashboard.getBoolean( "DB/Button 1", false )  &&   time.get() >= timeToDefense) // It will not move
		{
			lBackMotor.set(0);
			rBackMotor.set(0);
			lFrontMotor.set(0);
			rFrontMotor.set(0);
		}
		else if(  SmartDashboard.getBoolean("DB/Button 1", false)  && time.get() <= timeToDefense ) // It will go backwards	
		{
			rFrontMotor.set(-0.5);
			rBackMotor.set(-0.5);
			lFrontMotor.set(0.5);
			lBackMotor.set(0.5);	
		}
		else 
		{	
			lBackMotor.set(0);
			rBackMotor.set(0);
			lFrontMotor.set(0);
		    rFrontMotor.set(0);   
		}*/
		
	}
		
    public void autonomousInit() 
    {
        //autonomousCommand = (Command) chooser.getSelected();
        //if (autonomousCommand != null) autonomousCommand.start();
        time.start();
    }

    public void autonomousPeriodic()
    {
        Scheduler.getInstance().run();
        autoCode();
    }
    
    public void teleopInit()
    {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }
    
    public static void init()
	{
		//if inverted, invert it back
		/*gamePadDrive.setInvertedMotor(MotorType.kFrontLeft, true);
		gamePadDrive.setInvertedMotor(MotorType.kFrontRight, true);
		gamePadDrive.setInvertedMotor(MotorType.kRearLeft, true);
		gamePadDrive.setInvertedMotor(MotorType.kRearRight, true);*/
		
		//cam stuff
        /*cam = new AxisCamera(cameraHost);
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);*/
	}
    
    /*public static void compressor()
    {
    	if(airCompressor.getPressureSwitchValue())
		{
			//if the air is full, do nothing 
			airCompressor.setClosedLoopControl(false);
		}
		else
		{	
			//else start compression
			airCompressor.setClosedLoopControl(true);
		}
    }*/
	
	public static void drive()
	{
		/*if(gamePad.getRawButton(tankD)) //if button 9 is pressed, set to true
		{
			driveToggle = true;
		}
		else if(gamePad.getRawButton(arcadeD)) //if button 10 is pressed, set to false
		{
			driveToggle = false;
		}
		
		if(driveToggle) //if true
		{
			//tank drive
			rFrontMotor.set(-gamePad.getRawAxis(3));
			lFrontMotor.set(gamePad.getY());
			rBackMotor.set(-gamePad.getRawAxis(3));
			lBackMotor.set(gamePad.getY());
		}
		else //if false
		{
			//set to arcade, usually set to arcade because of this
			gamePadDrive.arcadeDrive(gamePad, true);  	 
		}*/
		
		rFrontMotor.set(-gamePad.getRawAxis(3));
		lFrontMotor.set(gamePad.getY());
		rBackMotor.set(-gamePad.getRawAxis(3));
		lBackMotor.set(gamePad.getY());
	}
		
	/*public static void shifting()
	{
		if(gamePad.getRawButton(fastShiftingButton))	
		{
			//if press button 7, set to true
			shiftingToggle = true;
		}
		else if(gamePad.getRawButton(slowShiftingButton)) 
		{
			//if button 8, set to false
			shiftingToggle = false;
		}
		
		if(shiftingToggle)
		{
			//if ture, low gear or high gear?
			leftShiftingSolenoid.set(on);
			rightShiftingSolenoid.set(on);
			System.out.println("Second Gear");
		}
		else 
		{
			//if false, low gear or high gear?
			leftShiftingSolenoid.set(off);
			rightShiftingSolenoid.set(off);
		
			System.out.println("First Gear");
		}
	}*/
	
	public static void intake()
	{
		intakeMotor.set((operatorPad.getRawAxis(1)) * speed); //left joystick on x box fps pro run on class mate 
	}
	
	public static void intakeState()
	{
		//set intake to y axis of the operator controller and slow it down a bit
		intakeMotor.set((operatorPad.getRawAxis(1)) * speed); //left joystick on x box fps pro run on class mate 
		
		if(operatorPad.getRawButton(1))
		{
			switch(state)
			{
				case 0:
					intakeTimer.reset();
					
					state = 1;
				
				case 1:
					if(operatorPad.getRawButton(1))
					{
						intakeTimer.start();
						if(intakeTimer.get() >= intakeTime)
						{
							intakeMotor.set(0);
						}
						else
						{
							intakeMotor.set(0.6);
						}
					}
					else if(operatorPad.getRawButton(3))
					{
						intakeMotor.set(-0.6);
					}
					else
					{
						intakeMotor.set(0);
					}
					
					//state = 0;
			}
		}
		else if(operatorPad.getRawButton(3))
		{
			intakeMotor.set(-0.6);
		}
		else
		{
			intakeMotor.set(0);
		}
	}
	
	/*public static void shooter()
	{
		//set shooter to y axis of the other joystick and slow it down a bit 
		shooterMotor.set((operatorPad.getRawAxis(5)) * speed); //right joystick on x box fps pro run on class mate
	}*/
	                                                                                                                                                
	public static void camera()
	{
		//more cmaera code, gets the image and should display onto the smartdash board
		 cam.getImage(frame);
         CameraServer.getInstance().setImage(frame);
	}

    public void teleopPeriodic() 
    {
        //Scheduler.getInstance().run();
       
        drive();
        //compressor();
        //shifting();
        intake();
        //shooter();
        //camera();
	}

    public void testPeriodic()
    {
        LiveWindow.run();
    }
}