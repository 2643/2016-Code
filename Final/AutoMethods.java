package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Encoder;

public class AutoMethods extends Robot{
	
	
    public static void setDrive(double leftSpeed,int rightSpeed){
    	frontRightMotor.set(rightSpeed);
    	backRightMotor.set(rightSpeed);
    	backLeftMotor.set(leftSpeed);
    	frontLeftMotor.set(leftSpeed);
    }
    
    public static void setDrive(double speed){
    	frontLeftMotor.set(speed);
        frontRightMotor.set(speed);
        backLeftMotor.set(speed);
        backRightMotor.set(speed);
    }
    
   
    public static boolean moveForward(double distanceTillUp,double speed){
                if(distanceTillUp > Math.abs(leftDriveEncoder.get()) || distanceTillUp > Math.abs(rightDriveEncoder.get())){
                      	setDrive(speed);
                      	return false;
                }
                return true;
        }

   public static boolean crossChevalDeFrise() {
        	if(leftDriveEncoder.get() >= 60 && rightDriveEncoder.get() >= 60){
        	//	piston.set(false);
        	}else{
        		//piston.set(true);
        	}
			return moveForward(distanceOverDefense,.5);
        }
        
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


        public static int turnMove(int shiftStartingPosition, int turnMoveState) {
               	switch(turnMoveState){
               	case 0:
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
               		if(moveForward(shiftStartingPosition*distanceBetweenDefenses,1)){
               			resetEncoders();
               			return 2;
               		}
               		break;
               	case 2:
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