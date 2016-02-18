
/*
Vikasni: starting positions
Justin Case: port numbers and solenoids
*/
package org.usfirst.frc.team2643.robot;
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
    //smartboard thing, probably needs to be changed
    double shiftStartingPosition;
    //change these numbers after testing
    static double distanceBetweenDefenses = 500;
    static double distanceOverDefense = 400;
    static double distanceUntillInfront = 1000;
    static double turn90Amount = 140;
    //static variables which dont need to be changed
    //doubles
   // static double currentPower = 0;
    //static double currentRPS = 0;
    static double leftPosition = 0;
    static double rightPosition = 0;
    //ints
    static int autoState = 0;
    static final int cross = 2;
    static final int finishedState = 4;
    static final int toShootState = 3;
    static final int moveForward = 1;
    static final int turnMove = 0;
    static int turnMoveState = 0;
    //booleans
    static boolean alreadyPressed = false;
	static boolean alreadyPressed2 = false;
    static boolean finished = false;
    
	static boolean startCounting = false;
	static boolean isTankDrive = false;
    //drive Motors
    static Talon backLeftMotor = new Talon(3);
    static Talon backRightMotor = new Talon(0);
    static Talon frontLeftMotor = new Talon(2);
    static Talon frontRightMotor = new Talon(1);
    //shooter motor stuff
   // static Victor shooterMotor = new Victor(4);
    static Victor intakeMotor = new Victor(5);
    //climb motors
   // static Victor climbArmMotor = new Victor(6);
    //static Victor winch1 = new Victor(7);
   // static Victor winch2 = new Victor(8);
  //  static Victor winch3 = new Victor(9);
    //encoders
    static Encoder leftDriveEncoder  = new Encoder(0,1);
    static Encoder rightDriveEncoder = new Encoder(2,3);
    //static Encoder shooterEncoder = new Encoder(4,5);
    //Joysticks
    static Joystick gamePad = new Joystick(0);
    static Joystick operatorGamePad = new Joystick(1);
    //Timers
    /*static Timer clock = new Timer();
	static Timer dontStartClimbing = new Timer();
	static Timer solenoidClock = new Timer();
	*/
    //digital inputs
   /* static  DigitalInput bottomLimitSwitch = new DigitalInput(7);
    static DigitalInput ballOpticSensor = new DigitalInput(8);
    static  DigitalInput topLimitSwitch = new DigitalInput(6);
    */
    //Solenoids
   // static Solenoid climbArmSolenoid = new Solenoid(3);
    //static Solenoid piston = new Solenoid(2);
    public static Solenoid shiftSolenoid1 = new Solenoid(0);
    public static Solenoid shiftSolenoid2 = new Solenoid(1);
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
    		AutoMethods.resetEncoders();
    		autoState = 0;
    		finished = false;
            autoSelected = (String) chooser.getSelected();
            
            for (double i = 0;i <= 5;i += .01){
    			if(SmartDashboard.getNumber("DB/Slider 1",0) < i + .005 && (SmartDashboard.getNumber("DB/Slider 1",0) > i - .005)){
    				shiftStartingPosition = ((int)((i*100)+.5))/100.0;
    				shiftStartingPosition -= 2.5;
    				shiftStartingPosition *= 2;
    				break;
    			}
    		}
//                autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
                System.out.println("Auto selected: " + autoSelected);
                AutoMethods.resetEncoders();
            }
   


    /**
     * This function is called periodically during autonomous
     */
     
    public void autonomousPeriodic() {
    	if(!SmartDashboard.getBoolean("DB/Button 1")){
    		//state machine here
    	System.out.println("AutoState: " + autoState + "\nturnMoveState: " + turnMoveState);
    	switch(autoState){
		case turnMove: 
			//this makes the robot turn and go  to the defense place 
			turnMoveState = AutoMethods.turnMove(shiftStartingPosition,turnMoveState);
			if(turnMoveState > 2){
				turnMoveState = 0;
				autoState = moveForward;
			}
			break;
			
		case moveForward: 
			//goes up to right in front of defense
			if(AutoMethods.moveForward(distanceUntillInfront, 0.5)){
				AutoMethods.resetEncoders();
				autoState = cross;
			}
			break;
		case cross:
			//crosses each defense here 
			//pick on db slider
			if(SmartDashboard.getNumber("DB/Slider 0", 0) == 4){
             	//finished = AutoMethods.crossChevalDeFrise();
           }else  if(SmartDashboard.getNumber("DB/Slider 0", 0) == 1){
            	finished = AutoMethods.crossMoat();
           }else  if(SmartDashboard.getNumber("DB/Slider 0", 0) == 2){   
            	finished = AutoMethods.crossRoughTerrain();
           }else  if(SmartDashboard.getNumber("DB/Slider 0", 0) == 3){
                 finished = AutoMethods.crossRamparts();
           }else  if(SmartDashboard.getNumber("DB/Slider 0", 0) == 0){
        	   finished = AutoMethods.crossRockWall();
           }else{
        	   finished = AutoMethods.crossMoat();
           }
           //once finished, then go to the finished state
		if(finished){
			autoState = toShootState;
		}
		break;
		
		case toShootState:
			if(AutoMethods.moveForward(distanceUntillInfront, 0.5)){
				AutoMethods.resetEncoders();
				autoState = finishedState;
				System.out.println("finished");
			}
		break;
		case finishedState:
			AutoMethods.setDrive(0);
			break;
	}
    	}else{
    		//"Arman State" (dumb state for autonomous)
        	System.out.println("AutoState: " + autoState + "\nturnMoveState: " + turnMoveState);
    		switch(autoState){
    		case 0:
    			if(AutoMethods.moveForward(distanceUntillInfront, 0.5)){
				AutoMethods.resetEncoders();
				autoState = finishedState;
				System.out.println("finished");
			}
    			break;
    		case finishedState:
    			AutoMethods.resetEncoders();
				autoState = finishedState;
				System.out.println("finished");
    			break;
    		}
    	}
    }


    public void teleopInit() {
    	//clock.start();
    	//dontStartClimbing.start();
      }
    
    
   
    
    public void teleopPeriodic() {
    	
    	//TeleOp.opticSensorTset();
    	TeleOp.drive();
    	//TeleOp.intake();
    	TeleOp.shiftGears();
    	//TeleOp.climber();
    	//intakeMotor.set(0.5);
    	
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
