package org.usfirst.frc.team3684.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;

public class CANTalon extends TalonSRX implements SpeedController{
	
	public CANTalon(int deviceNumber) {
		super(deviceNumber);
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(double speed) {
		super.set(ControlMode.PercentOutput, speed);
		
	}

	@Override
	public double get() {
		return super.getMotorOutputPercent();
				}

	@Override
	public void disable() {
		super.set(ControlMode.Disabled, 0);
		
	}

	@Override
	public void stopMotor() {
		super.set(ControlMode.PercentOutput, 0);
		
	}
	
}
