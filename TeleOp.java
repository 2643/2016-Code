package org.usfirst.frc.team2643.robot;

public class TeleOp {
  
  tankDrive();
    	
  
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
    
    /**
     * This function is called periodically during test mode
     */

}
