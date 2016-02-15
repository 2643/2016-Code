	public static void climber() {
		if(gamePad2.getRawButton(2) && !topLimitSwitch.get()){
			climberArm.set(0.5);
		}else if(!gamePad.getRawButton(3)){
				climberArm.set(0.0);
		}

		if(gamepad.getRawButton(3)){
			//arm retracts while the winch goes up
			startCounting = true;
			if(solenoidClock.get() > 2){
				setWinches(0.5);
			}else{
				solenoid1.set(false);
				solenoid1.set(false);
			}
			if(bottomLimitSwitch.get()){
				climberArm.set(0.0);
			}else{
				climberArm.set(-0.5);
			}
		}else{
			setWinches(0.0);
		}
		if(!startCounting){
			solenoidClock.reset();
		}
	}
	public static void setWinches(double speed){
		winch1.set(speed);
		winch2.set(speed);
		winch3.set(speed);
	}

