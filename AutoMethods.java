package org.usfirst.frc.team2643.robot;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
public class AutoMethods extends Robot{
    
public static void moveForward(double distanceTillUp,double speed){
                rightDriveEncoder.reset();
                leftDriveEncoder.reset();
                while(distanceTillUp > leftDriveEncoder.get() || distanceTillUp > rightDriveEncoder.get()){
                        frontLeftMotor.set(speed);
                        frontRightMotor.set(speed);
                        backLeftMotor.set(speed);
                        backRightMotor.set(speed);
                }
                frontLeftMotor.set(0);
                frontRightMotor.set(0);
                backLeftMotor.set(0);
                backRightMotor.set(0);
        }

        public static void crossChevalDeFrise() {
                //code
        }
        
        public static void crossMoat(double distanceOverObject) {
                moveForward(distanceOverObject,0.7);
        }


        public static void crossRoughTerrain(double distanceOverObject) {
                moveForward(distanceOverObject,0.7);
        }


        public static void crossRamparts(double distanceOverObject) {
                moveForward(distanceOverObject,0.6);
        }


        public static void crossRockWall(double distanceOverObject) {
                moveForward(distanceOverObject,0.2);
        }


        public static void turnMove(int shiftStartingPosition) {
                if(shiftStartingPosition > 0){
                        turnRight();
                        moveForward(distanceBetweenDefenses*shiftStartingPosition,1);
                        turnLeft();
                }else if(shiftStartingPosition < 0){
                        turnLeft();
                        moveForward((-1)*distanceBetweenDefenses*shiftStartingPosition,1);
                        turnRight();
                }
        }

 
        public static void turnRight() {
                rightDriveEncoder.reset();
                leftDriveEncoder.reset();
                while(turn90Amount > leftDriveEncoder.get() || (-1)*turn90Amount < rightDriveEncoder.get()){
                        frontLeftMotor.set(1);
                        frontRightMotor.set(-1);
                        backLeftMotor.set(1);
                        backRightMotor.set(-1);
                }
                frontLeftMotor.set(0);
                frontRightMotor.set(0);
                backLeftMotor.set(0);
                backRightMotor.set(0);
        }


        public static void turnLeft() {
                rightDriveEncoder.reset();
                leftDriveEncoder.reset();
                while((-1)*turn90Amount < leftDriveEncoder.get() || turn90Amount > rightDriveEncoder.get()){
                        frontLeftMotor.set(-1);
                        frontRightMotor.set(1);
                        backLeftMotor.set(-1);
                        backRightMotor.set(1);
                }
                frontLeftMotor.set(0);
                frontRightMotor.set(0);
                backLeftMotor.set(0);
                backRightMotor.set(0);
        }
}
