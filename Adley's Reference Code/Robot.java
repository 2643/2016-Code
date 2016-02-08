package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;



import org.usfirst.frc.team2643.robot.commands.ExampleCommand;
import org.usfirst.frc.team2643.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * written by: Adley Wong
 * @Team 2643
 */

public class Robot extends IterativeRobot 
{

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;

    Command autonomousCommand;
    SendableChooser chooser;
   
    static Timer time = new Timer();
    static int timing = 100;

    public void robotInit() 
    {
		oi = new OI();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new ExampleCommand());
        SmartDashboard.putData("Auto mode", chooser);
        
        DriveControl.init();
        
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
        AutonSetting.autonMode();
    }

    public void teleopInit()
    {
        if (autonomousCommand != null) autonomousCommand.cancel();
        DriveControl.inTelop();
    }

    public void teleopPeriodic() 
    {
    	
        Scheduler.getInstance().run();
        time.start();
        
        DriveControl.drive();
        DriveControl.intake();
        DriveControl.shooter();
        DriveControl.climber();
        DefenseDrive.defenseButtons();
    }

    public void testPeriodic()
    {
        LiveWindow.run();
    }
}
