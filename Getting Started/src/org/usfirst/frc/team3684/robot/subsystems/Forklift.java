package org.usfirst.frc.team3684.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.CANTalon;
import org.usfirst.frc.team3684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Forklift extends Subsystem {
	CANTalon m_LiftUP = new CANTalon(RobotMap.LiftMotorUp);
	CANTalon m_LiftDOWN = new CANTalon(RobotMap.LiftMotorDown);
	
	public Forklift() {
		
		m_LiftUP = new CANTalon(RobotMap.LiftMotorUp);
		m_LiftDOWN = new CANTalon(RobotMap.LiftMotorDown);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	}

	 public void initDefaultCommand() {
	        // Set the default command for a subsystem here.
	    }
		
		public void setMotors(double up, double down) {
	    	up = scaleup(up);
	    	down = scaledown(down);
	    	
	    	setMotors(up, down);
	    }
	    
	    public void setMotorsRaw(double up, double down) {
	    	up = safetyTest(up);
	    	down = safetyTest(down);
	    	
	    	m_LiftUP.set(up);
	    	m_LiftDOWN.set(down);
	    	
		}
	    
	    public void stop() {
	    	m_LiftUP.set(0);
	    	m_LiftDOWN.set(0);
	    }
	    
	    private double safetyTest(double motorValue) {
	        motorValue = (motorValue < -1) ? -1 : motorValue;
	        motorValue = (motorValue > 1) ? 1 : motorValue;
	        
	        return motorValue;
	    }
	    
	 
	    
	    
	    private double scaleup(double up) {
	    	return 1.0 * up;
	    }
	    private double scaledown(double down) {
	    	return 1.0 * down;
	    }

	//public final CANTalon leftFrontDrive;
	//public final CANTalon leftRearDrive;
	//public final CANTalon rightFrontDrive;
	//public final CANTalon rightRearDrive;


	    // Put methods for controlling this subsystem
	    // here. Call these from Commands.
	    //public void tankDrive(double leftPower, double rightPower) {
	    	  //this.leftFrontDrive.set(ControlMode.PercentOutput, leftPower);
	          //this.leftRearDrive.set(ControlMode.PercentOutput, leftPower);
	          //this.rightFrontDrive.set(ControlMode.PercentOutput, rightPower);
	          //this.rightRearDrive.set(ControlMode.PercentOutput, rightPower);
	        // Set the default command for a subsystem here.
	        //setDefaultCommand(new MySpecialCommand());
	    }


