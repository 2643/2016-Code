/*Authors:
*Adley: Debugging
*Oskar: Debugging
*Vikasni: Debugging
*Yvonne: Original Author
*Niko: Rewrote Climber
*Erika: stared at Yvonne while she made it
*/

package org.usfirst.frc.team2643.robot;


public class TeleOp extends Robot{
  
	public static void intake(){
		   intakeMotor.set(operatorStick.getY());
	}
	
    // add "boolean tankDrive = false" in final
  public static void drive(){
if(gamePad.getRawButton(0)){ //update button numbers
    isTankDrive = false;
  }
  else if(gamePad.getRawButton(0)){ //update button numbers
    isTankDrive = true;
  }
  else if(isTankDrive){
    tankDrive();
  }else{
    arcadeDrive();
  }
  }
  
         //put into a method
         
  public static void climber() {
	  if(dontStartClimbing.get() > 115){
		if(operatorStick.getRawButton(2) && !topLimitSwitch.get()){
			climbArmMotor.set(0.5);
		}else if(!gamePad.getRawButton(3)){
				climbArmMotor.set(0.0);
		}
		if(operatorStick.getRawButton(3)){
			//arm retracts while the winch goes up
			startCounting = true;
			if(solenoidClock.get() > 2){
				setWinches(0.5);
			}else{
				climbArmSolenoid.set(false);
			}
			if(bottomLimitSwitch.get()){
				climbArmMotor.set(0.0);
			}else{
				climbArmMotor.set(-0.5);
			}
		}else{
			setWinches(0.0);
		}
		if(!startCounting){
			solenoidClock.reset();
		}
	  }
	}
	public static void setWinches(double speed){
		winch1.set(speed);
		winch2.set(speed);
		winch3.set(speed);
	}

        
    public static void tankDrive(){
    	leftPosition = gamePad.getY();
    	rightPosition = gamePad.getY();
    	backLeftMotor.set(-leftPosition);
        backRightMotor.set(rightPosition);
        frontLeftMotor.set(-leftPosition);
        frontRightMotor.set(rightPosition); 
    }
     public static void arcadeDrive() {
    	   double yPosition = gamePad.getY();
    	   double xPosition = gamePad.getX();
    	    
    	    frontRightMotor.set(yPosition-xPosition);
    	    backRightMotor.set(yPosition-xPosition);
    	    frontLeftMotor.set((-1)*(yPosition+xPosition));
    	    backLeftMotor.set((-1)*(yPosition+xPosition));
}
    

public static void shiftGears(){
	
		
		if(gamePad.getRawButton(2) && !alreadyPressed){
        	shiftSolenoid1.set(!shiftSolenoid1.get());
        	shiftSolenoid2.set(!shiftSolenoid2.get());
        	alreadyPressed = true;
       	 }
       	 if(!gamePad.getRawButton(2)){
		alreadyPressed = false;       	 
       	 }

         
}

public static void opticSensorTset() {
	if(ballOpticSensor.get()) {
		System.out.println("No ball");
	}
	else {
		System.out.println("Ball");
	}
}
}

    	
