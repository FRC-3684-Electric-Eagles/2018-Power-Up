package org.usfirst.frc.team3684.robot.subsystems;

import org.usfirst.frc.team3684.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.CANTalon;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FlipUp extends Subsystem {
	public Spark flipmotor;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public FlipUp() {
		flipmotor = new Spark(RobotMap.FlipMotor);
		}
		//the spark may be temporary, we might decide to use a servo, which I don't think requires a motor controller. Never know though.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setMotors(double flip) {
    	flip = scaleFlip(flip);
    	setMotorsRaw(flip);
	}
    
    public void setMotorsRaw(double flip) {
    	flip = safetyTest(flip);
    	
    	flipmotor.set(flip);
    	
    }
    
    public void stop() {
    	flipmotor.set(0);
    	//stopping the motors. 
    }
    private double safetyTest(double motorValue) {
        motorValue = (motorValue < -1) ? -1 : motorValue;
        motorValue = (motorValue > 1) ? 1 : motorValue;
        
        return motorValue;
        //apparently this is a safety test. who knew. 
    }
    
    private double scaleFlip(double flip) {
    	return 1.0 * flip;
    }
}
//i've basically been using the same code for all the subsystems. Hopefully that works out fine. Hopefully.
