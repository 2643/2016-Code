package org.usfirst.frc.team2643.robot;


public class TeleOp extends Robot{
  
    // add "boolean tankDrive = false" in final
  public static void drive(){
if(rightStick.getRawButton(3)){ 
    isTankDrive = false;
  }
  else if(leftStick.getRawButton(3)){ //update button numbers
    isTankDrive = true;
  }
  else if(isTankDrive){
    tankDrive();
  }else{
    arcadeDrive();
  }
}
  
    
         if(rightStick.getRawButton(2)){
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
         
public static void climberCode(){
  if(gamePad.getRawButton(2)){
			switch(state){
			
			case climberState:
				
					climber.set(0.5);
				if(topLimitSwitch.get()){
					climber.set(0.0);
					
					state = climberStateDown;
				}
				break;
				
			climberStateDown:
				
			if(gamepad.getRawButton.get(3)){
				climber.set(-0.5);
				if(bottomLimitSwitch.get()){
					climber.set(0.0);
					state = hookMoveUp;
			}
			}
				break;
				
			hookMoveUp:
				
				 if(gamePad.getRawButton(4)){
		        	 winchOn=!winchOn;
				if(winchOn){
				winch1.set(0.5);
				winch2.set(0.5);
				winch3.set(0.5);
				state = hookMoveDown;
				}
				else{
					winch1.set(0.0);
					winch2.set(0.0);
					winch3.set(0.0);
					state = hookMoveDown;
				}
				 }
			break;
			
		hookMoveDown:
			 if(gamePad.getRawButton(5)){
	        	 winchDown=!winchDown;
			if(winchDown){
			winch1.set(-0.5);
			winch2.set(0.5);
			winch3.set(0.5);
			
			}
			else{
				winch1.set(0.0);
				winch2.set(0.0);
				winch3.set(0.0);
				
			}
	}
			break;

}

}

        
    public static void tankDrive(){
    	leftPosition = leftStick.getY();
    	rightPosition = rightStick.getY();
    	backLeftMotor.set(-leftPosition);
        backRightMotor.set(rightPosition);
        frontLeftMotor.set(-leftPosition);
        frontRightMotor.set(rightPosition); 
    }
     public static void arcadeDrive() {
    	   double yPosition = rightStick.getY();
    	   double xPosition = rightStick.getX();
    	    
    	    frontRightMotor.set(yPosition-xPosition);
    	    backRightMotor.set(yPosition-xPosition);
    	    frontLeftMotor.set((-1)*(yPosition+xPosition));
    	    backLeftMotor.set((-1)*(yPosition+xPosition));
}
    




]
