package org.usfirst.frc.team2643.robot;


public class TeleOp extends Robot{
  
    // add "boolean tankDrive = false" in final
  public static void drive(){
if(gamePad.getRawButton(9)){ //update button numbers
    isTankDrive = false;
  }
  else if(gamePad.getRawButton(10)){ //update button numbers
    isTankDrive = true;
  }
  if(isTankDrive){
    tankDrive();
  }else{
    arcadeDrive();
  }
}
  
    
         if(gamePad.getRawButton(2)){
        	 solenoid1.set(solenoid1State);
        	 solenoid2.set(solenoid2State);
  }
    	/*int solenoid1PCM = 1;
    	int solenoid2PCM = 2;
    	boolean solenoid1State = solenoid1.get();
    	boolean solenoid2State = solenoid2.get();
    	solenoid1S
    	Add these in final
    	*/
    	
    	
   
         //put into a method
         

    
    public static void tankDrive(){
    	leftPosition = gamePad.getY();
    	rightPosition = gamePad2.getRawAxis(3);
    	backLeftMotor.set(leftPosition);
        backRightMotor.set(rightPosition);
        frontLeftMotor.set(leftPosition);
        frontRightMotor.set(rightPosition); 
    }
     public static void arcadeDrive() {
    	   double yPosition = gamePad.getY();
    	   double xPosition = gamePad.getX();
    	    
    	    frontRightMotor.set(yPosition-xPosition);
    	    backRightMotor.set(yPosition-xPosition);
    	    frontLeftMotor.set(yPosition+xPosition);
    	    backLeftMotor.set(yPosition+xPosition);
}
    
}
public static void intake(){

    if(gamePad.getRawButton(0)){
    	intakeMotor.set(speed);
    }
    else if(gamePad.getRawButton(1)){
    	intakeMotor.set(-speed);
    }
    }
    }
    
