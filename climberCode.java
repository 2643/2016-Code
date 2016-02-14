import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;

climberCode {
	//CHANGE ALL THE PORT NUMBERS 
	
	public static Joystick gamePad2 = new Joystick(2);
	//change the port number 
	public static Victor climber = new Victor(2);
	public static Victor winch = new Victor(1);
	public static Victor winch2 = new  Victor(2);
	public static Victor winch3 = new Victor(0);
	public static DigitalInput topLimitSwitch = new DigitalInput(3);
	public static DigitalInput bottomLimitSwitch = new DigitalInput(4);
	// ALL CHANGE 
	final int state = 0;
	final int climberState = 0; 
	final int climberStateDown = 1;
	final int hookMoveUp = 2;
	final int hookMoveDown = 3;
	//ALL CHANGE
	boolean winchOn = false;
	boolean winchDown = false;
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if(gamePad2.getRawButton(1)){
			switch(state){
			
			case climberState:
				
					climber.set(0.5);
				if(topLimitSwitch.get()){
					climber.set(0.0);
					
					state = climberStateDown;
				}
				break;
				
			climberStateDown:
				
			if(gamepad2.getRawButton.get(3)){
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
	}	
}
