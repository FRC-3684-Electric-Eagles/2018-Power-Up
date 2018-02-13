package org.usfirst.frc.team3684.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.CANTalon;
import org.usfirst.frc.team3684.robot.RobotMap;
import org.usfirst.frc.team3684.robot.commands.DriveTrain_TankDrive;
/**
 *
 */
public class Drivetrain extends Subsystem {
	
	public CANTalon leftMotor;
	public CANTalon rightMotor;
	public CANTalon backleftMotor;
	public CANTalon backrightMotor;
	
	public Drivetrain() {
	
		leftMotor = new CANTalon(RobotMap.DRIVETRAIN_Talon_BACKLEFT);
		rightMotor = new CANTalon(RobotMap.DRIVETRAIN_Talon_RIGHT);
		backleftMotor = new CANTalon (RobotMap.DRIVETRAIN_Talon_LEFT);
		backrightMotor = new CANTalon (RobotMap.DRIVETRAIN_Talon_BACKRIGHT);
		
	}
	
	  public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveTrain_TankDrive());
    }
	
	public void setMotors(double left, double right) {
    	left = scaleLeft(left);
    	right = scaleRight(right);
    	//adding a scale which can be changed in "scaleleft" and "scaleright"
    	setMotorsRaw(left, right);
    	//telling it to go to the next public void
    }
    
    public void setMotorsRaw(double left, double right) {
    	left = safetyTest(left);
    	right = safetyTest(right);
    	//testing for... "safety"? What is this alien concept?
    	leftMotor.set(left);
    	backleftMotor.set(left);
    	rightMotor.set(right);		
    	backrightMotor.set(right);
    	//actually setting the motors to what I told them to do
	}
    
    public void stop() {
    	leftMotor.set(0);
    	backleftMotor.set(0);
    	rightMotor.set(0);		
    	backrightMotor.set(0);
    	//stopping the motors. 
    }
    
    private double safetyTest(double motorValue) {
        motorValue = (motorValue < -1) ? -1 : motorValue;
        motorValue = (motorValue > 1) ? 1 : motorValue;
        
        return motorValue;
        //apparently this is a safety test. who knew. 
    }
    
    private double scaleLeft(double left) {
    	return -1.0 * left;
    }
    
    private double scaleRight(double right) {
    	return 1.0 * right;
    } // the scale of motors
    



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


