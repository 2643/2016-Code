/*Authors:
*Oskar: Debugging
*Vikasni: Debugging
*Yvonne: Original Author
*Niko: Rewrote Climber
*Erika: stared at Yvonne while she made it
*/

package org.usfirst.frc.team2643.robot;


public class TeleOp extends Robot{
  
	public static void intake(){
		   intakeMotor.set(operatorGamePad.getY());
	}
	
    // add "boolean tankDrive = false" in final
  public static void drive(){
if(gamePad.getRawButton(10)){ //update button numbers
    isTankDrive = false;
  }
  else if(gamePad.getRawButton(9)){ //update button numbers
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
		if(operatorGamePad.getRawButton(3) && !topLimitSwitch.get()){
			climbArmMotor.set(0.5);
		}else if(!gamePad.getRawButton(3)){
				climbArmMotor.set(0.0);
		}
		if(operatorGamePad.getRawButton(2)){
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
    	rightPosition = gamePad.getRawAxis(3);
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

    	
public static void visionProcess(){
	  {
    	
        Scheduler.getInstance().run();
 
        while(isOperatorControl() && isEnabled())
        {      	 
        	for (double width : table.getNumberArray("myContoursReport/width", new double[0])) 
        	{
        		switch(state)
                {
                    case 0:
                        double[] widths = table.getNumberArray("myContoursReport/width", new double[0]);
                        temp = 0;
                        for(int i = 0;i < widths.length; i++)
                        {
                            if(widths[i] > widths[temp])
                            {
                                temp = i;
                            }
                        }

                        System.out.println("width: " + widths[temp] + "\ndistance: " + ((136.0/118.0)*(1.08*(20*320)/widths[temp])));
                        //Timer.delay(2);
                }
        	}
        }
    }
}

}
