package org.usfirst.frc.team2643.robot;
 
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
 
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.HSLImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;
 
public class Robot extends IterativeRobot 
{
	
	//Camera declarations
    String cameraHost = "10.26.43.11";
    AxisCamera cam;
    Image frame;
    
    //motors
    static Talon frMotor = new Talon(1);
    static Talon brMotor = new Talon(0);
    static Talon flMotor = new Talon(2);
    static Talon blMotor = new Talon(3);
    static Jaguar intakeMotor = new Jaguar(4);
    static Jaguar shooterMotor = new Jaguar(5);
    
    //joystick
    Joystick stick = new Joystick(0);
    Joystick shooterStick = new Joystick(1);
    static Joystick gamePad = new Joystick (2);
    
    //arcade
    static RobotDrive arcade = new RobotDrive(flMotor, blMotor, frMotor, brMotor);
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    
    //shooter speeds
    double intakeSpeedZ = (shooterStick.getZ()-1)/2;
	private JoystickButton button = new JoystickButton(gamePad, 9);
	
	public boolean isPressed = false;
    private boolean isToggled = false;
    
    public void robotInit()
    {
    	
        //cam stuff
        cam = new AxisCamera(cameraHost);
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        arcade.setInvertedMotor(MotorType.kFrontLeft, true);
        arcade.setInvertedMotor(MotorType.kFrontRight, true);
        arcade.setInvertedMotor(MotorType.kRearLeft, true);
        arcade.setInvertedMotor(MotorType.kRearRight, true);
        
    }

    public void autonomousInit() 
    {
        autoSelected = (String) chooser.getSelected();
        //autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
        System.out.println("Auto selected: " + autoSelected);
    }
 
    public void autonomousPeriodic()
    {
        switch(autoSelected) 
        {
        	case customAuto:
        		//Put custom auto code here  
            break;
        	case defaultAuto:
        	default:
        		//Put default auto code here
            break;
        }
    }
    
    public void shooterCode()
    {
    	 if(gamePad.getRawButton(7))
         {
         	intakeMotor.set(intakeSpeedZ);
         }
         else if(gamePad.getRawButton(5))
         {
         	intakeMotor.set(-intakeSpeedZ);
         }
         else if(gamePad.getRawButton(8))
         {
         	shooterMotor.set(-0.82);
         }
         else if(gamePad.getRawButton(6))
         {
         	shooterMotor.set(0.82);
         }
         else 
         {
         	intakeMotor.set(0);
         	shooterMotor.set(0);
         }
    }

    public void teleopPeriodic()
    {
        //vision code
        //cam.getImage(frame);
        //CameraServer.getInstance().setImage(frame);

    	Scheduler.getInstance().run();
    	while(true)
    	{
    		shooterCode();
    		//drive();
    		//arcade.arcadeDrive(gamePad, true);
    		
    		if(gamePad.getRawButton(9))
    		{
    			isPressed = true;
    		}
    		else if(gamePad.getRawButton(10))
    		{
    			isPressed = false;
    		}
    		
    		if(isPressed)
    		{
    			frMotor.set(gamePad.getRawAxis(3)/-2);
            	flMotor.set(gamePad.getY()/2);
            	frMotor.set(gamePad.getRawAxis(3)/-2);
            	blMotor.set(gamePad.getY()/2);
    		}
    		else
    		{
    			arcade.arcadeDrive(gamePad, true);
    		}
    		
    	}
    }

    public void testPeriodic() 
    {
   
    }
   
    
}