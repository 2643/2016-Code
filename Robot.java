
package org.usfirst.frc.team2643.robot;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static int frontRightMotorPWM = 3;
	public static int backLeftMotorPWM = 0;
	public static int backRightMotorPWM = 1;
	public static int frontLeftMotorPWM = 2;
	public static int hookMotorPWM = 4;
	public static int linearSlidePWM = 4;
	public static int linearSlide2PWM = 5;
	public static int gamePadPort= 0;
	public static int gamePad2Port = 1;
	public static int slideBottomLimitSwitchDI = 3;
	public static int slideEncoderEN = 6 ;
	public static int slideEncoderEN2 = 7;
	public static int hookEncoderEN = 4;
	public static int hookEncoderEN2 = 5;
	public static int leftDriveEncoderEN = 0;
	public static int leftDriveEncoderEN2 = 1;
	public static int rightDriveEncoderEN = 2;
	public static int rightDriveEncoderEN2 = 3;
	 
	
	
	
	boolean isTankDrive = false
	
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    int barrierInfront = (int) (SmartDashboard.getNumber("DB/Slider 0", 0)*2);
    int shiftStartingPosition = (int) ((SmartDashboard.getNumber("DB/Slider 1",0)-2.5)*2);
    int state = 0;
    int portcullis = 0;
    int sallyPort = 0;
    int drawBridge = 0;
    int arcadeDriveDrawBridge = 3;
    int arcadeDrivePortcullis = 4;
    int arcadeDriveSallyPort = 4;
    
    static double turn90Amount = 0;
    static double distanceBetweenDefenses = 0;
    static double distanceToDefense = 0;
    static double distanceToFinishDefense = 0;
    static double distanceToPullDown = 0;
    static double topOfLinearSlide = 0;
    static double highHeightBoundary = 80;
    static double lowHeightBoundary = 20;
    double leftPosition = 0;
    double rightPosition = 0;
    double distanceTillUp = 0;
    double distanceOverDefense = 0;
    
    
    static Talon backLeftMotor = new Talon(backLeftMotorPWM);
    static Talon backRightMotor = new Talon(backRightMotorPWM);
    static Talon frontLeftMotor = new Talon(frontLeftMotorPWM);
    static Talon frontRightMotor = new Talon( frontRightMotorPWM);
    static Talon hookMotor = new Talon(hookMotorPWM);
    static Talon linearSlide = new Talon(linearSlidePWM);
    static Talon linearSlide2 = new Talon(linearSlide2PWM);
    static Encoder leftDriveEncoder  = new Encoder(leftDriveEncoderEN,leftDriveEncoderEN2);
    static Encoder rightDriveEncoder = new Encoder(2,3);
    static Encoder hookEncoder = new Encoder(hookEncoderEN,hookEncoderEN2);
    static Encoder slideEncoder = new Encoder(slideEncoderEN,slideEncoderEN2);
    static Joystick gamePad = new Joystick(gamePadPort);
    static Joystick gamePad2 = new Joystick(gamePad2Port);
    static Timer clock = new Timer();
    
    DigitalInput slideBottomLimitSwitch = new DigitalInput(slideBottomLimitSwitchDI);
    
   
    
    
   
    //Declaration of variables^
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        SmartDashboard.putNumber("DB/Slider 0" , 0.0);
        SmartDashboard.putNumber("DB/Slider 1" , 0.0);
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
    }
    
        /**
         * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
         * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
         * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
         * below the Gyro
         *
         * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
         * If using the SendableChooser make sure to add them to the chooser code above as well.
         */
    public void autonomousInit() {
            autoSelected = (String) chooser.getSelected();
//                autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
                System.out.println("Auto selected: " + autoSelected);
                hookEncoder.reset();
                AutoMethods.turnMove(shiftStartingPosition);
                AutoMethods.moveForward(distanceTillUp,1);
                switch (barrierInfront){
                case 1:
                    AutoMethods.crossDrawbridge();
                    break;
                case 2:
                    AutoMethods.crossPortcullis();
                    break;
                case 3:
                    AutoMethods.crossChevalDeFrise();
                    break;
                case 4:
                    AutoMethods.crossSallyPort();
                    break;
                case 5:
                    AutoMethods.crossMoat(distanceOverDefense);
                    break;
                case 6:
                    AutoMethods.crossRoughTerrain(distanceOverDefense);
                    break;
                case 7:
                    AutoMethods.crossRamparts(distanceOverDefense);
                    break;
                case 8:
                    AutoMethods.crossRockWall(distanceOverDefense);
                    break;
            }
    }


    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    
    }


    public void teleopInit() {
    	clock.start();
      }
    
    
    public void teleopPeriodic() {
       
    	               
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

//add limit switch and encoder on the climber 
