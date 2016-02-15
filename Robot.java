
package org.usfirst.frc.team2643.robot;

import java.lang.Math;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	

    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;    
    int shiftStartingPosition = (int) ((SmartDashboard.getNumber("DB/Slider 1",0)-2.5)*2);
    
    static boolean isTankDrive = false;
    static double turn90Amount = 0;
    static double distanceBetweenDefenses = 0;
    static double distanceToDefense = 0;
    static double distanceToFinishDefense = 0;
    static double leftPosition = 0;
    static double rightPosition = 0;
    double distanceUntillInfront = 0;
    static double distanceOverDefense = 0;
    String autoState = "moveForawrd";
    final double DISTANCE_POWER_CONSTANT = 0;
    int turnMoveState = 0;
    boolean finished = false;
    double currentRPS = 0;
    double currentPower = 0;
    double distance = 1;
    
    static Solenoid piston = new Solenoid(0);
    static Talon backLeftMotor = new Talon(0);
    static Talon backRightMotor = new Talon(1);
    static Talon frontLeftMotor = new Talon(2);
    static Talon frontRightMotor = new Talon(3);
    static Victor shooterMotor = new Victor(4);
    static Victor intakeMotor = new Victor(5);
    static Victor climbMotor1 = new Victor(0);
    static Victor climbMotor2 = new Victor(0);
    static Victor climbMotor3 = new Victor(0);
    static Victor climbArmMotor = new Victor(0);
    static Encoder leftDriveEncoder  = new Encoder(0,1);
    static Encoder rightDriveEncoder = new Encoder(2,3);
    static Encoder shooterEncoder = new Encoder(4,5);
    static Joystick stick = new Joystick(0);
    static Joystick gamePad = new Joystick(1);
    static Joystick stick2 = new Joystick(2);
    
    static Timer clock = new Timer();
    static  int speed = 0;
    
    int solenoid1PCM = 1;
    int solenoid2PCM = 2;
    Solenoid  solenoid1 = new Solenoid(solenoid1PCM);
    Solenoid  solenoid2 = new Solenoid(solenoid2PCM);
    static boolean solenoid1State = solenoid1.get();
    static boolean solenoid2State = solenoid2.get();
    
    DigitalInput slideBottomLimitSwitch = new DigitalInput(0);
    
   
    
    
   
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
                AutoMethods.resetEncoders();
            }
   


    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoState){
		case "turnMove":
			turnMoveState = AutoMethods.turnMove(shiftStartingPosition,turnMoveState);
			if(turnMoveState > 2){
				autoState = "moveForward";
				AutoMethods.resetEncoders();
			}
			break;
		case "moveForward": 
			if(AutoMethods.moveForward(distanceUntillInfront,1)){
				autoState = "cross";
				AutoMethods.resetEncoders();
			}
			break;
		case "cross":
			if(SmartDashboard.getNumber("DB/Slider 0", 0) == 0){
             	finished = AutoMethods.crossChevalDeFrise();
           }else  if(SmartDashboard.getNumber("DB/Slider 0", 0) == 1){
            	finished = AutoMethods.crossMoat();
           }else  if(SmartDashboard.getNumber("DB/Slider 0", 0) == 2){   
            	finished = AutoMethods.crossRoughTerrain();
           }else  if(SmartDashboard.getNumber("DB/Slider 0", 0) == 3){
            	finished = AutoMethods.crossRamparts();
           }else  if(SmartDashboard.getNumber("DB/Slider 0", 0) == 4){
            	finished = AutoMethods.crossRockWall();
           }
		if(finished){
			autoState = "finished";
		}
			break;
		case "finished":
			//whatever when its done
			break;
	}
    }


    public void teleopInit() {
    	clock.start();
      }
    
    
    public void teleopPeriodic() {
    	TeleOp.drive();
    	TeleOp.intake();
    }
    
   
    
    //*calculate current RPS
    
    //ADD THIS BACK WHEN PID CONTROL IS DONE 

    // {if(gamePad.getRawButton(0)){
            //set distance to certain numbers for presets
            //shooterMotor.set(pidControl(Math.sqrt(distance)*DISTANCE_POWER_CONSTANT,currentRPS,currentPower));
            //leave as error until pidControl is done
            

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

//add limit switch and encoder on the climber 
