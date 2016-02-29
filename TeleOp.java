/*Authors:
*Adley: Debugging
*Oskar: Debugging
*Vikasni: Debugging
*Yvonne: Original Author
*Erika: stared at Yvonne while she made it
*/

 package org.usfirst.frc.team2643.robot;


public class TeleOp extends Robot{
  
	public static void intake(){
		//change back to operatorGamePad
	   intakeMotor.set(intakeController.getY());
	   
	   
	}
	
	public static void shooter(){
	shooterMotor.set(shooterStick.getRawAxis(1));
	}
	
    //add "boolean tankDrive = false" in final
  public static void drive(){
if(gamePad.getRawButton(10)){ //update button numbers
    isTankDrive = false;
    System.out.println("arcade Drive");
  }
  else if(gamePad.getRawButton(9)){ //update button numbers
    isTankDrive = true;
    System.out.println("tank Drive");
  }
  if(isTankDrive){
    tankDrive();
  }else{
    arcadeDrive();
  }
  }
  
 
  
        
    public static void tankDrive(){
    	leftPosition = gamePad.getY() * 0.75;
    	rightPosition = gamePad.getRawAxis(3) * 0.75;
    	backLeftMotor.set(leftPosition);
        backRightMotor.set(rightPosition);
        frontLeftMotor.set(leftPosition);
        frontRightMotor.set(rightPosition); 
    }
     public static void arcadeDrive() {
    	  double yPosition = gamePad.getY();
    	   double xPosition = gamePad.getX();
    	    
    	    frontRightMotor.set(yPosition+xPosition);
    	    backRightMotor.set(yPosition+xPosition);
    	    frontLeftMotor.set((yPosition-xPosition));
    	    backLeftMotor.set((yPosition-xPosition));
    	 
     }
  

public static void shiftGears()
{	
		if(gamePad.getRawButton(7)){
			 shiftSolenoid1.set(shiftSolenoid1.get());
        	shiftSolenoid2.set(shiftSolenoid2.get());
        	
        	//Toggles only once when the button is pressed
       	 }
       	 if(gamePad.getRawButton(8)){
       		 shiftSolenoid1.set(false);
       		shiftSolenoid2.set(false);
       		 
       		 	 
       		 //Once you let go of button, alreadyPressed resets to false
       	 }      
}
       	 
       	 
}
  


/*public static void opticSensorTset() {
	if(ballOpticSensor.get()) {
		System.out.println("No ball");
	}
	else {
		System.out.println("Ball");
	} */



    	

