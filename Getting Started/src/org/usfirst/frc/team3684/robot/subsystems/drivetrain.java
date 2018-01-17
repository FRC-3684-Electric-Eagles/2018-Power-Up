package org.usfirst.frc.team3684.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3684.robot.CANTalon;
import org.usfirst.frc.team3684.robot.RobotMap;
import org.usfirst.frc.team3684.robot.commands.DriveTrain_TankDrive;
/**
 *
 */
public class drivetrain extends Subsystem {
	
	CANTalon leftMotor = new CANTalon(RobotMap.DRIVETRAIN_Talon_LEFT);
	CANTalon rightMotor = new CANTalon(RobotMap.DRIVETRAIN_Talon_RIGHT);
	CANTalon backleftMotor = new CANTalon (RobotMap.DRIVETRAIN_Talon_BACKLEFT);
	CANTalon backrightMotor = new CANTalon (RobotMap.DRIVETRAIN_Talon_BACKRIGHT);
	  public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveTrain_TankDrive());
    }
	
	public void setMotors(double left, double right) {
    	left = scaleLeft(left);
    	right = scaleRight(right);
    	
    	setMotorsRaw(left, right);
    }
    
    public void setMotorsRaw(double left, double right) {
    	left = safetyTest(left);
    	right = safetyTest(right);
    	
    	leftMotor.set(left);
    	backleftMotor.set(left);
    	rightMotor.set(right);		
    	backrightMotor.set(right);
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


