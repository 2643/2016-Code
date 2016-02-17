/*
Authors:
Arman Amjad
*/
package org.usfirst.frc.team2643.robot;
 
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
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
   
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    //default variables
   
    static double powerIncrement = 0.05;
    static double speedUpWait = 0.05;
    static double secondWait = 0.05;
    //UPDATE NUMBERS LATER^
   
   
    static final double preset1 = 3;
    static final double preset2 = 6;
    static double currentRPS = 0;
    static double currentPower = 0;
    static double desiredRPS;
    static double desiredPower;
    static boolean is1Preset = false;
    static boolean is2Preset = false;
    //UPDATE LATER
   
    static Timer pidTimer = new Timer();
    static Timer rpsTimer = new Timer();
    static Timer timer = new Timer();
    static Joystick stick1 = new Joystick(0);
    static Victor shooterMotor = new Victor(4);
    static Encoder shooterEncoder = new Encoder(4,5);
    final int powerChangingState = 0;
    final int waitingState = 1;
    
    int state = powerChangingState;
 //   final int calculatingRPSState = 2;
   
    double timeInterval = 500;
   
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        //Default code
       
        shooterEncoder.reset();
       
       
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
//      autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
        System.out.println("Auto selected: " + autoSelected);
    }
 
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        switch(autoSelected) {
        case customAuto:
        //Put custom auto code here  
            break;
        case defaultAuto:
        default:
        //Put default auto code here
            break;
        }
    }
    public double toPower(double rps){
        double power;
        if(rps < 0.4){
            power = 0.0;
        }
        else{
        power = 3.067 - (Math.sqrt((-0.52192 * (rps +0.3)) + 9.24679));
        //Uses equation from known data
        }
        return power;
    }
   
    public void incrementPower(double differenceInPower){
    	if(desiredPower > 0){
    		if(currentPower > desiredPower){
    			System.out.println("1");
    			//do nothing
    		}
    		else if(differenceInPower >= powerIncrement){
    			//Increments power to max power increment
    			//System.out.println("2");
    			currentPower += powerIncrement;
    		}
    		else{
    			//Increases power to desired power
    			currentPower += differenceInPower;
    			// System.out.println("3");
      	 }
    	}
    if(desiredPower < 0){
    	if(currentPower < desiredPower){
			System.out.println("1");
			//do nothing
		}
		else if(differenceInPower >= powerIncrement){
			//Increments power to max power increment
			//System.out.println("2");
			currentPower -= powerIncrement;
		}
		else{
			//Increases power to desired power
			currentPower -= differenceInPower;
			// System.out.println("3");
  	 }
    }
    }
   /* public void pidControl(){
       
       
           
    }*/
   
    /**
     * This function is called periodically during operator control
     */
   
    public void teleopPeriodic() {
       //System.out.println("1");
        rpsTimer.start();
        pidTimer.start();
       //System.out.println("2");
        long lastTime = System.currentTimeMillis();
        while(isOperatorControl() && isEnabled()){
           if(lastTime <= System.currentTimeMillis() + 500){
              if(rpsTimer.get() > 0.5){
                currentRPS = Math.abs(shooterEncoder.get()*2.0/255.0);
                System.out.println(currentRPS);
                rpsTimer.reset();
                shooterEncoder.reset();
               }
                //Gets revolutions every second
           
           
                //set distance to certain numbers for presets
               /* if(stick1.getRawButton(1)){
                    is1Preset = !is1Preset;
                    is2Preset = false;
                }
                else if(stick1.getRawButton(2)){
                    is2Preset = !is2Preset;
                    is1Preset = false;
                }
               
                if(is1Preset){
                    desiredRPS = preset1;
                }
                else if(is2Preset){
                    desiredRPS = preset2;
                }
                else{
                    desiredRPS = 0;
                }*/
           
                //PID Control
                
                desiredRPS = 7.5;
                desiredPower = toPower(desiredRPS);
               
                switch(state){
                
                
           
                    case powerChangingState:
                   
                        double powerDifference = Math.abs(desiredPower - currentPower);
                        incrementPower(powerDifference);
                        frontLeftMotor.set(currentPower);
                        backLeftMotor.set(currentPower);
                      //  System.out.println("Incremented");
                        state = waitingState;
                        break;
                   
                    case waitingState:
                   
                        if(pidTimer.get() >= (speedUpWait + secondWait)){
                            desiredPower = (desiredRPS/currentRPS)*currentPower;
                            //calculates desired RPS
                       //   System.out.println("Done waiting");
                            state = powerChangingState;
                        }
                        break;
                   
                    default:
                   
                        System.out.println("PID State Default Error!!");
                        state = powerChangingState;
                }//switch
            lastTime = System.currentTimeMillis();
            }//if
        }//while
    }
   
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
   
    }
   
}
