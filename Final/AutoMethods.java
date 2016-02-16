/*Authors:
*Vikasni
*Niko
*/

package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Encoder;

public class AutoMethods extends Robot{
	
	//set drive sets the two sides to a speed indep.
    public static void setDrive(double leftSpeed,int rightSpeed){
    	frontRightMotor.set(rightSpeed);
    	backRightMotor.set(rightSpeed);
    	backLeftMotor.set(leftSpeed);
    	frontLeftMotor.set(leftSpeed);
    }
    //set drive sets all driving 
    public static void setDrive(double speed){
    	frontLeftMotor.set(speed);
        frontRightMotor.set(speed);
        backLeftMotor.set(speed);
        backRightMotor.set(speed);
    }
    
   
    public static boolean moveForward(double distanceTillUp,double speed){
    	//sets the drive to go forward , returns true or false
                if(distanceTillUp > Math.abs(leftDriveEncoder.get()) || distanceTillUp > Math.abs(rightDriveEncoder.get())){
                      	setDrive(speed);
                      	return false;
                }
                return true;
        }

   public static boolean crossChevalDeFrise() {
   	//moves piston and goes forward
        	if(leftDriveEncoder.get() >= 60 && rightDriveEncoder.get() >= 60){
        		piston.set(false);
        	}else{
        		piston.set(true);
        	}
			return moveForward(distanceOverDefense,.5);
        }
        //all of this moves forward 
        public static boolean crossMoat() {
                return moveForward(distanceOverDefense,0.7);
        }


        public static boolean crossRoughTerrain() {
        	 return moveForward(distanceOverDefense,0.7);
        }


        public static boolean crossRamparts() {
        	 return moveForward(distanceOverDefense,0.6);
        }


        public static boolean crossRockWall() {
        	 return moveForward(distanceOverDefense,0.3);
        }

//turnMove is self explaitory it makes the robot turn then move
        public static int turnMove(int shiftStartingPosition, int turnMoveState) {
               	switch(turnMoveState){
               	case 0:
               		//turn left or right
               		if(shiftStartingPosition < 0){
               			if(turnRight()){
               				setDrive(1);
               				resetEncoders();
               				return 1;
               			}
               		}else if(shiftStartingPosition > 0){
               			if(turnLeft()){
               				setDrive(1);
               				resetEncoders();
               				return 1;
               			}
               		}else{
               			return 1;
               		}
               		break;
               	case 1:
               		//move towards the desired defense
               		if(moveForward(shiftStartingPosition*distanceBetweenDefenses,1)){
               			resetEncoders();
               			return 2;
               		}
               		break;
               	case 2:
               		//turn again  
               		if(shiftStartingPosition > 0){
               			if(turnRight()){
               				setDrive(1);
               				resetEncoders();
               				return 1;
               			}
               		}else if(shiftStartingPosition < 0){
               			if(turnLeft()){
               				setDrive(1);
               				resetEncoders();
               				return 1;
               			}
               		}else{
               			return 1;
               		}
               	}
				return turnMoveState;
        }

        public static void resetEncoders(){
        	rightDriveEncoder.reset();
            leftDriveEncoder.reset();
        }
        public static boolean turnRight() {
                if(turn90Amount < leftDriveEncoder.get() || (-1)*turn90Amount > rightDriveEncoder.get()){
                       setDrive(1,-1);
                       return(false);
                }else{
                	return(true);
                }
        }


        public static boolean turnLeft() {
        	if((-1)*turn90Amount > leftDriveEncoder.get() || turn90Amount < rightDriveEncoder.get()){
                setDrive(-1,1);
                return(false);
         }else{
         	return(true);
         }
        }
}
