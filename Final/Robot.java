
package org.usfirst.frc.team2643.robot;
//imports here 
import java.lang.Math;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
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
    //doubles here 
    //change numbers after testing
   // static boolean isTankDrive = false;
    static double turn90Amount = 400;
    static double distanceBetweenDefenses = 200;
    static double distanceToDefense = 300;
    static double distanceToFinishDefense = 100;
    static double leftPosition = 0;
    static double rightPosition = 0;
    static double distanceUntillInfront = 100;
    static double distanceOverDefense = 400;
    final double DISTANCE_POWER_CONSTANT = 0;
    static final int turnMoveState = 0;
    // static doubles
    static double currentRPS = 0;
    static double currentPower = 0;
    static double distance = 1;
    static boolean finished = false;
    static boolean isTankDrive = false;
    
    static final int turnMoveState = 0;
    static final int turnMove = 0;
    static final int moveForward = 1;
    static final int cross = 2;
    static int autoState = moveForward;
    static final int finishedState = 3;
    static boolean solenoidToggleIfAlreadyPressed = false;
    //variables here 
    static Solenoid piston = new Solenoid(2);
    //Drive Motors
    static Talon backLeftMotor = new Talon(3);
    static Talon backRightMotor = new Talon(1);
    static Talon frontLeftMotor = new Talon(2);
    static Talon frontRightMotor = new Talon(0);
    //shooter motor stuff
    static Victor shooterMotor = new Victor(4);
    static Victor intakeMotor = new Victor(5);
    //climb motors
    static Victor climbMotor1 = new Victor(7);
    static Victor climbMotor2 = new Victor(8);
    static Victor climbMotor3 = new Victor(9);
    static Victor climbArmMotor = new Victor(6);
    //encoders
    static Encoder leftDriveEncoder  = new Encoder(0,1);
    static Encoder rightDriveEncoder = new Encoder(2,3);
    static Encoder shooterEncoder = new Encoder(4,5);
    //joysticks
    static Joystick gamePad = new Joystick(0);
    static Joystick gamePad2 = new Joystick(1);
    static Joystick gamePad = new Joystick(0);
    static Joystick gamePad2 = new Joystick(1);
    
    static Timer clock = new Timer();
    DigitalInput  ballOpticSensor = new DigitalInput(4);
    //Solenoids
   Solenoid  solenoid1 = new Solenoid(solenoid1PCM);
    Solenoid  solenoid2 = new Solenoid(solenoid2PCM);
    //RobotDrive drive = new RobotDrive( frontLeftMotor,  backLeftMotor,  frontRightMotor,  backRightMotor);
    
    
    
    
    
    DigitalInput slideBottomLimitSwitch = new DigitalInput(0);
    
   
    
    
   
    //Declaration of variables^
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	System.out.println("1");
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
    	System.out.println("2");
            autoSelected = (String) chooser.getSelected();
//                autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
                System.out.println("Auto selected: " + autoSelected);
                AutoMethods.resetEncoders();
            }
   


    /**
     * This function is called periodically during autonomous
     */
     
    public void autonomousPeriodic() {
    
//state machine here
    	switch(autoState){
		case turnMove: 
			//this makes the robot turn and go  to the defense place 
			turnMoveState = AutoMethods.turnMove(shiftStartingPosition,turnMoveState);
			if(turnMoveState > 2){
				AutoMethods.resetEncoders();
				autoState = moveForward;
				
			}
			break;
			
		case moveForward: 
			//goes up to right in front of defense
			
			System.out.println(leftDriveEncoder.get());
			if(AutoMethods.moveForward(distanceUntillInfront, 0.5)){
				AutoMethods.resetEncoders();
				autoState = cross;
			}
			break;
		case cross:
			//crosses each defense here 
			//pick on db slider
			System.out.println("cross");
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
           //once finished, then go to the finished state
		if(finished){
			autoState = finishedState;
		}
		break;
		case finishedState:
			//here put the shooting code that goes up to the shooting position and shoots 
	    AutoMethods.setDrive(0);
			break;
	}
    }


    public void teleopInit() {
    	clock.start();
      }
    
    
   
    
    public void teleopPeriodic() {
    	
    	if(ballOpticSensor.get()) {
    		System.out.println("No ball");
    	}
    	else {
    		System.out.println("Ball");
    	
    	
    	}
    	
    	TeleOp.drive();
    	
    		int solenoid1PCM = 1;
	int solenoid2PCM = 2;
	
    Joystick gamePad = new Joystick(1);
    
    
    public void teleopPeriodic() {

         //add already pressed boolean in robot
         if(gamePad.getRawButton(2) && !alreadyPressed){
        	solenoid1.set(!solenoid1.get());
        	solenoid2.set(!solenoid2.get());
        	alreadyPressed = true;
       	 }
       	 if(!gamePad.getRawButton(2)){
		alreadyPressed = false;       	 
       	 }

         }
    	
    	
    	//TeleOp.intake();
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
