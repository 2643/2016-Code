package org.usfirst.frc.team2643.robot;
import edu.wpi.first.wpilibj.Solenoid;


public class TeleOp {
  
  tankDrive();
    boolean arcadeDrive = false
    boolean tankDrive = false
  public void arcadedrive() {
    	   double yPosition = driveStick.getY();
    	   double xPosition = driveStick.getX();
    	    
    	    frontRightMotor.set(yPosition-xPosition);
    	    backRightMotor.set(yPosition-xPosition);
    	    frontLeftMotor.set(yPosition+xPosition);
    	    backLeftMotor.set(yPosition+xPosition);
}
  public void tankDrive(){
    	leftPosition = gamePad.getY();
    	rightPosition = gamePad2.getRawAxis(3);
    	backLeftMotor.set(leftPosition);
        backRightMotor.set(rightPosition);
        frontLeftMotor.set(leftPosition);
        frontRightMotor.set(rightPosition); 
    }
  if(gamePad.getRawButton(0)){ //update button numbers
   arcadeDrive = true
  }
  else if(gamePad.getRawButton(0)){ //update button numbers
    tankDrive = true
  }
  
    	int solenoid1PCM = 1;
    	int solenoid2PCM = 2;
    	boolean solenoid1State = solenoid1.get();
    	boolean solenoid2State = solenoid2.get();
    	
    	
   
         
         if(gamePad.getRawButton(2)){
        	 
        	 solenoid1.set(!solenoid1State);
        	 solenoid2.set(!solenoid2State);
  
    	if(clock.get() > 130 && gamePad.getRawButton(9))
    	{
    	        String state = "raiseHooks";
    	        int stop = 0;
    	        while(stop == 0)
    	        {
    	                switch(state)
    	                {
    	                        //raise hooks
    	                        case "raiseHooks":
    	                                //move slow as you near the top of the linear slide, if not close then go at normal speed
    	                                if(slideEncoder.get() > highHeightBoundary - 30)
    	                                {
    	                                        //stop at the top, if not at the top go slow
    	                                        if(slideEncoder.get() > highHeightBoundary)
    	                                        {
    	                                                linearSlide.set(0);
    	                                                leftDriveEncoder.reset();
    	                                                rightDriveEncoder.reset();
    	                                                state = "moveForward";
    	                                        }
    	                                        else
    	                                        {
    	                                                linearSlide.set(.2);
    	                                        }
    	                                }
    	                                else
    	                                {
    	                                        linearSlide.set(.5);
    	                                }
    	                        break;
    	                        //move forward a bit
    	                        case "moveForward":
    	                                if(leftDriveEncoder.get() > 100 || rightDriveEncoder.get() < -100)
    	                                {
    	                                        backRightMotor.set(0);
    	                                        frontRightMotor.set(0);
    	                                        backLeftMotor.set(0);
    	                                        frontLeftMotor.set(0);
    	                                        state = "pullUp";
    	                                }
    	                                else
    	                                {
    	                                        backRightMotor.set(-.3);
    	                                        frontRightMotor.set(-.3);
    	                                        backLeftMotor.set(.3);
    	                                        frontLeftMotor.set(.3);
    	                                }
    	                        break;
    	                        //pull robot up
    	                        case "pullUp":
    	                                //as you near the bottom slow down
    	                                if(slideEncoder.get() < lowHeightBoundary - 30)
    	                                {
    	                                        //stop at the bottom, if not at bottom then go slow
    	                                        if(slideEncoder.get() < lowHeightBoundary)
    	                                        {
    	                                                linearSlide.set(0);
    	                                                state = "pullUp";
    	                                        }
    	                                        else
    	                                        {
    	                                                linearSlide.set(-.2);
    	                                        }
    	                                }
    	                                else
    	                                {
    	                                        linearSlide.set(-.5);
    	                                }
    	                        
    	                      // case otherwise: 
    	                    	   //stop = 1;
    	                        	break;
    	                }
    	        }
    	        
    	}
    	
    }
    
    public void tankDrive(){
    	leftPosition = gamePad.getY();
    	rightPosition = gamePad2.getRawAxis(3);
    	backLeftMotor.set(leftPosition);
        backRightMotor.set(rightPosition);
        frontLeftMotor.set(leftPosition);
        frontRightMotor.set(rightPosition); 
    }
     public void arcadedrive() {
    	   double yPosition = driveStick.getY();
    	   double xPosition = driveStick.getX();
    	    
    	    frontRightMotor.set(yPosition-xPosition);
    	    backRightMotor.set(yPosition-xPosition);
    	    frontLeftMotor.set(yPosition+xPosition);
    	    backLeftMotor.set(yPosition+xPosition);
}
    
    /**
     * This function is called periodically during test mode
     */

}
