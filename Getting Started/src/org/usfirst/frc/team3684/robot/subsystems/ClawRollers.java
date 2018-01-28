package org.usfirst.frc.team3684.robot.subsystems;

import org.usfirst.frc.team3684.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3684.robot.commands.ClawIntake;
/**
 *
 */
public class ClawRollers extends Subsystem {
	
	public CANTalon m_clawmotor1;
	public CANTalon m_clawmotor2;
	public ClawRollers() {
		m_clawmotor1 = new CANTalon (RobotMap.ClawMotor1);
		m_clawmotor2 = new CANTalon (RobotMap.ClawMotor2);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ClawIntake());
    }
    
    public void setMotors(double left, double right) {
    	left = scaleLeft(left);
    	right = scaleRight(right);
    	
    	setMotorsRaw(left, right);
    }
    
    public void setMotorsRaw(double left, double right) {
    	left = safetyTest(left);
    	right = safetyTest(right);
    	
    	m_clawmotor1.set(left);
    	m_clawmotor2.set(right);
	}
    
    public void stop() {
    	m_clawmotor1.set(0);
    	m_clawmotor2.set(0);
    }
    
    private double safetyTest(double motorValue) {
        motorValue = (motorValue < -1) ? -1 : motorValue;
        motorValue = (motorValue > 1) ? 1 : motorValue;
        
        return motorValue;
    }
    
    private double scaleLeft(double left) {
    	return 1.0 * left;
    }
    
    private double scaleRight(double right) {
    	return 1.0 * right;
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



    
    
    
    
    
    
    


