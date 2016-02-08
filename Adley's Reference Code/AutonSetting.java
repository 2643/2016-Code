package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * written by: Adley Wong
 * @Team 2643
 */

public class AutonSetting extends DriveControl
{	
	public static void autonMode() 
	{
		position();
		barrier();
		shoot();
	}
	
	public static void position()
	{
		if(SmartDashboard.getNumber("DB/Slider 0", 0.0) == 0.0)
		{
			//left position
		}
		else if(SmartDashboard.getNumber("DB/Slider 0", 0.0) == 1.0)
		{
			//middle position
		}
		else if(SmartDashboard.getNumber("DB/Slider 0", 0.0) == 2.0)
		{
			//right position
		}
		else if(SmartDashboard.getNumber("DB/Slider 0", 0.0) == 3.0)
		{
			//spy bot
		}
		else
		{
			return;
		}
	}
	
	public static void barrier()
	{
		if(SmartDashboard.getNumber("DB/Slider 1", 0.0) == 0.0)
		{
			//Portcullis
		}
		else if(SmartDashboard.getNumber("DB/Slider 1", 0.0) == 1.0)
		{
			//Cheval de Frise
		}
		else if(SmartDashboard.getNumber("DB/Slider 1", 0.0) == 2.0)
		{
			//Moat
		}
		else if(SmartDashboard.getNumber("DB/Slider 1", 0.0) == 3.0)
		{
			//ramparts
		}
		else if(SmartDashboard.getNumber("DB/Slider 1", 0.0) == 4.0)
		{
			//rock wall
		}
		else if(SmartDashboard.getNumber("DB/Slider 1", 0.0) == 5.0)
		{
			//rough terrain
		}
		else if(SmartDashboard.getNumber("DB/Slider 2", 0.0) == 0.0)
		{
			//sally port
		}
		else if(SmartDashboard.getNumber("DB/Slider 2", 0.0) == 1.0)
		{
			//draw bridge
		}
		else
		{
			return;
		}
	}
	
	public static void shoot()
	{
		if(SmartDashboard.getNumber("DB/Slider 3", 0.0) == 0.0)
		{
			//left shot
		}
		else if(SmartDashboard.getNumber("DB/Slider 3", 0.0) == 1.0)
		{
			//middle shot
		}
		else if(SmartDashboard.getNumber("DB/Slider 3", 0.0) == 2.0)
		{
			//right shot
		}
		else
		{
			return;
		}
	}
}
