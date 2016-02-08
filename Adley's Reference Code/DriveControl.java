package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Talon;

/*
 * Written by Adley Wong
 * @Team 2643
 */

public class DriveControl extends Robot
{
	//Joysticks
	static Joystick gamePad = new Joystick(1);
	
	//Drive Motors
	static Talon lBackMotor = new Talon(1);
	static Talon rBackMotor = new Talon(2);
	static Talon lFrontMotor = new Talon(3);
	static Talon rFrontMotor = new Talon(4);
	
	//shooting and intake
	static Talon intakeMotor = new Talon(5);
	
	//Slide
	static Talon climbMotor1 = new Talon(6);
	static Talon climbMotor2 = new Talon(7);
	
	//booleans
	static boolean isPressed = false;
	
	//Encoder
	static Encoder slideEncoder = new Encoder(1,1);//need correct ports
	static Encoder leftDriveEncoder = new Encoder(2,2);
	static Encoder rightDriveEncoder = new Encoder(3,3);
	static Encoder shootEncoder = new Encoder(4,4);

	//Magic Number
	static double intakeSpeed = 0.5;
	static double upperSpeed = 0.4;
	static double slow = 0.2;
	static int topPoint = 100;
	static double shootSpeed = 0.6;
	static int state = 0;
	static int lowestPortcullis = 100;
	static int highestPortcullis = 400;
	static double moveSpeed = 0.4;
	static int moveDistance = 325;
	static int portHeight = 200;
	
	//Button Magic Numbers
	static int intakeButton = 7;
	static int releaseButton = 5;
	static int shootButton = 8;
	static int reverseButton = 6;
	static int tankD = 9;
	static int arcadeD = 8;
	
	//Drive
	static RobotDrive gamePadDrive = new RobotDrive(lFrontMotor, lBackMotor, rFrontMotor, rBackMotor);
	
	public static void init()
	{
		//if inverted, invert it back
		gamePadDrive.setInvertedMotor(MotorType.kFrontLeft, true);
		gamePadDrive.setInvertedMotor(MotorType.kFrontRight, true);
		gamePadDrive.setInvertedMotor(MotorType.kRearLeft, true);
		gamePadDrive.setInvertedMotor(MotorType.kRearRight, true);
	}
	
	public static void inTelop()
	{
		slideEncoder.reset();
		climbMotor1.set(upperSpeed/3);
		climbMotor2.set(upperSpeed/3);
		
		if(slideEncoder.get() >= 20)
		{
			climbMotor1.set(0);
			climbMotor2.set(0);	
		}
	}
	
	public static void drive()
	{
		if(gamePad.getRawButton(tankD)) //if button 9 is pressed, set to true
		{
			isPressed = true;
		}
		else if(gamePad.getRawButton(arcadeD)) //if button 10 is pressed, set to false
		{
			isPressed = false;
		}
		
		if(isPressed) //if true
		{
			//tank drive
			rFrontMotor.set(gamePad.getRawAxis(3)/-2);
			lFrontMotor.set(gamePad.getY()/2);
			rBackMotor.set(gamePad.getRawAxis(3)/-2);
			lBackMotor.set(gamePad.getY()/2);
		}
		else //if false
		{
			//set to arcade, usually set to arcade because of this
			gamePadDrive.arcadeDrive(gamePad, true);
		}
	}
	
	public static void shooter()
	{
		//fix
		if(gamePad.getRawButton(reverseButton))
		{
			intakeMotor.set(-shootSpeed);
		}
		else if(gamePad.getRawButton(shootButton))
		{
			intakeMotor.set(shootSpeed);
		}
	}
	
	public static void intake()
	{
		if(gamePad.getRawButton(intakeButton)) //if pressing button 7, set to intake
		{
			intakeMotor.set(intakeSpeed);
		}
		else if(gamePad.getRawButton(releaseButton)) //if pressing button 5, set to release
		{
			intakeMotor.set(-intakeSpeed);
		}
	}
	
	public static void climber() //need to check the code
	{
		if(time.get() <= timing ) // if less than the time use this code
		{
			if(gamePad.getPOV() == 0) //if forward POV is pressed
			{
				if(slideEncoder.get() >= 280 && slideEncoder.get() <= 320) //if it is between 280 ticks and 320 ticks
				{
					climbMotor1.set(slow); //slow down
					climbMotor2.set(slow);
				}
				else if(slideEncoder.get() >= 320) //if further than 320 ticks, stop the Motors
				{
					climbMotor1.set(0);
					climbMotor2.set(0);
				}
				else //else allow it to go at half speed
				{
					climbMotor1.set(upperSpeed);
					climbMotor2.set(upperSpeed);
				}
			}
			else if(gamePad.getPOV() == 180) //if POV is going down (180)
			{
				if(slideEncoder.get() <= 60) //if close to 60 ticks, it'll slow down 
				{
					climbMotor1.set(-slow);
					climbMotor2.set(-slow);
				}
				else if(slideEncoder.get() <= 20 && slideEncoder.get() == 0) //if between 20 ticks and 0 ticks, stop
				{
					climbMotor1.set(0);
					climbMotor2.set(0);
				}
				else //else, move down at half speed
				{
					climbMotor1.set(-upperSpeed);
					climbMotor2.set(-upperSpeed);
				}
			}
		}
		else //if in the 20 seconds
		{
			if(gamePad.getPOV() == 0) //if POV is up (0)
			{
				if(slideEncoder.get() >= 400) //if greater than 400
				{
					climbMotor1.set(slow); //slow down to 1/5 speed
					climbMotor2.set(slow);
				}
				else if(slideEncoder.get() >= topPoint) //if at the higest point
				{
					climbMotor1.set(0); //stop
					climbMotor2.set(0);
				}
				else //else go at half speed
				{
					climbMotor1.set(upperSpeed);
					climbMotor2.set(upperSpeed);
				}
			}
			else if(gamePad.getPOV() == 180) //if POV is down (180) 
			{
				if(slideEncoder.get() <= 60 && slideEncoder.get() >= 20) //if less than 60 ticks
				{
					climbMotor1.set(-slow); //go at slow speed
					climbMotor2.set(-slow);
				}
				else if(slideEncoder.get() <= 20 && slideEncoder.get() == 0) //if between 20 ticks and 0 ticks, stop
				{
					climbMotor1.set(0);
					climbMotor2.set(0);
				}
				else //else move down at half speed
				{
					climbMotor1.set(-upperSpeed);
					climbMotor2.set(-upperSpeed);
				}
			}
		}
	}
}