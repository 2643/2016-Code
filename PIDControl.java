/*Authors:
*Vikasni
*Niko
*/

package org.usfirst.frc.team2643.robot;

public class AutoMethods extends Robot{
    //set drive sets the two sides to a speed indep.
    public static void setDrive(double leftSpeed,double rightSpeed){
    	// sets the motors to a speed: the right is negative bc the motors are inverted
    	frontRightMotor.set(-rightSpeed);	
    	backRightMotor.set(-rightSpeed);
    	backLeftMotor.set(leftSpeed);
    	frontLeftMotor.set(leftSpeed);
    }
    
    //set drive sets all driving 
    public static void setDrive(double speed){
    	//sets the motors to a speed, right side negative bc of inverted motors
    	frontLeftMotor.set(speed);
        frontRightMotor.set(-speed);
        backLeftMotor.set(speed);
        backRightMotor.set(-speed);
    }
    
    public static boolean moveForward(double distanceTillUp,double speed){
    	//sets the drive to go forward , returns true or false
        if( Math.abs(distanceTillUp) < Math.abs(leftDriveEncoder.get()) ||  Math.abs(distanceTillUp) < Math.abs(rightDriveEncoder.get())){
                //if the distance  is less than the encoder, then it'll set the drive to a speed
                setDrive(speed);
                return true;
        }
        else{
                return false;
        }
    
        //all of this moves forward 
        public static boolean crossMoat() {
        	//moves forward at a speed
                return moveForward(distanceOverDefense,0.5);
        }

        public static boolean crossRoughTerrain() {
        	//same as above
        	 return moveForward(distanceOverDefense,0.5);
        }


        public static boolean crossRamparts() {
        	//same as above
        	 return moveForward(distanceOverDefense,0.5);
        }


        public static boolean crossRockWall() {
        	//same as above
        	 return moveForward(distanceOverDefense,0.3);
        }

	//turnMove is self explaitory it makes the robot turn then move
        public static int turnMove(double shiftStartingPosition, int turnMoveState) {
               	switch(turnMoveState){
               		
               		case 0:
               			//turn left or right
               			if(shiftStartingPosition > 0){ 
               			//if the starting position you put in the slider  is greater than 0, then...
               				if(turnedRight()){
               				//if you turned right
               					setDrive(.5);
               					resetEncoders();
          					return 1;
               					//returns a 1 so it can go to the next state 
               				}
               				
               			}
               		
               			else if(shiftStartingPosition < 0){
               			// if less than zero 
               				if(turnLeft()){
               					setDrive(.5);
               					resetEncoders();
               					return 1;
               					//returns a 1
               				}
               			}
               		
               			else{
               				return 1;
               			}
               		
               		break;
               		
               	case 1:
               		//move towards the desired defense
               		if(moveForward(shiftStartingPosition*distanceBetweenDefenses,.5)){
               			resetEncoders();
               			return 2;
               		}
               		
               		break;
               		
               	case 2:
               		//turn again
               		if(shiftStartingPosition < 0){
               			if(turnedRight()){
               				setDrive(.5);
               				resetEncoders();
               				return 3;
               			}
               		}
               		
               		else if(shiftStartingPosition > 0){
               			if(turnLeft()){
               				setDrive(.5);
               				resetEncoders();
               				return 3;
               			}
               		}
               		
               		else {
               			
               			return 3;
               			
               		}
               	}
				
		return turnMoveState;
        
        	
        }

        public static void resetEncoders(){
        	
        	rightDriveEncoder.reset();
        	leftDriveEncoder.reset();
        
        }
        
        public static boolean turnedRight() {
        	
                if(turn90Amount >  Math.abs(leftDriveEncoder.get()) || turn90Amount >  Math.abs(rightDriveEncoder.get())){
                	
                 // if the encoders are less than the turn 90 amount, then it'll turn       
                	setDrive(.5,-.5);
                	return(false);
                       
                }
               
                else {
                		
                //when it's greater than the turn 90 amount, it'll return true
                	return(true);
                	
               	}
        }
        
        public static String getEncoders(){
        	
        	//gives you the encoder values
        	return leftDriveEncoder.get() + " " + rightDriveEncoder.get();
        }

        public static boolean turnLeft() {
        	
        	// if the encoders are less than the turn 90 amount, then it'll turn       
        	if(turn90Amount >  Math.abs(leftDriveEncoder.get()) || turn90Amount >  Math.abs(rightDriveEncoder.get())){
        		
        		setDrive(-.5,.5);
        		return(false);
                
                }
         
         	else{
        	//when it's greater than the turn 90 amount, it'll return true
         
         			return(true);
         	}
        }
		
}
