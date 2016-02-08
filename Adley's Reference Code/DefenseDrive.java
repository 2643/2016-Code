package org.usfirst.frc.team2643.robot;

/*
 * written by: Adley Wong
 * @Team 2643
 */

public class DefenseDrive extends DriveControl
{	
	public static void defenseButtons()
	{
		if(gamePad.getRawButton(2))
		{
			porticullis();
		}
		else if(gamePad.getRawButton(3))
		{
			sallyPort();
		}
		else if(gamePad.getRawButton(4))
		{
			drawBridge();
		}
	}
	
	public static void porticullis()
	{
		switch(state)
		{
			case 0:
				
				if(slideEncoder.get() >= lowestPortcullis)
				{
					climbMotor1.set(-upperSpeed);
					climbMotor2.set(-upperSpeed);
				}
				else
				{
					climbMotor1.set(0);
					climbMotor2.set(0);
					state = 1;
				}
				break;
				
			case 1:
				
				rFrontMotor.set(moveSpeed);
				lFrontMotor.set(moveSpeed);
				rBackMotor.set(moveSpeed);
				lBackMotor.set(moveSpeed);
				
				if(leftDriveEncoder.get() >= moveDistance && rightDriveEncoder.get() >= moveDistance)
				{
					rFrontMotor.set(0);
					lFrontMotor.set(0);
					rBackMotor.set(0);
					lBackMotor.set(0);
					
					state = 2;
				}
				break;
				
			case 2:
				
				climbMotor1.set(upperSpeed);
				climbMotor2.set(upperSpeed);
				
				if(slideEncoder.get() >= highestPortcullis)
				{
					climbMotor1.set(0);
					climbMotor2.set(0);
					
					state = 3;
				}
				break;
				
			case 3:
				
				rFrontMotor.set(moveSpeed);
				lFrontMotor.set(moveSpeed);
				rBackMotor.set(moveSpeed);
				lBackMotor.set(moveSpeed);
				
				if(leftDriveEncoder.get() >= moveDistance && rightDriveEncoder.get() >= moveDistance)
				{
					break;
				}
				break;
		}
	}
	
	public static void sallyPort()
	{
		switch(state)
		{
			case 0:
				
				if(slideEncoder.get() <= portHeight)
				{
					climbMotor1.set(upperSpeed);
					climbMotor2.set(upperSpeed);
				}
				else
				{
					climbMotor1.set(0);
					climbMotor2.set(0);
					state = 1;
				}
				
			case 1:
				
				climbMotor1.set(-upperSpeed/2);
				climbMotor2.set(-upperSpeed/2);
				
				
		}
	}
	
	public static void drawBridge()
	{
		switch(state)
		{
			case 0:
		}
	}
}
