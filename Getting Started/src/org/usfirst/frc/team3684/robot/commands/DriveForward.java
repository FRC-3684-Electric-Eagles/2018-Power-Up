package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.Robot;
import org.usfirst.frc.team3684.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3684.robot.subsystems.FlipUp;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveForward<time> extends CommandGroup {

	int P = 1;
	int I = 1;
	int D = 1;
    int integral, previous_error, setpoint = 0;
	private double rcw;
    
    public DriveForward(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	time = scaleTime(time);
    }
    
    
    public void setSetpoint(int setpoint)
    {
        this.setpoint = setpoint;
    }
    
    
    public void PID(){
        double error = setpoint - Robot.gyro.getAngle(); // Error = Target - Actual
        this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        double derivative = (error - this.previous_error) / .02;
        this.rcw = P*error + I*this.integral + D*derivative;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.setMotors(0, 0);
    	Robot.DriveForwardFinished = false;
    	Robot.gyro.reset();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(double time) {
    	time = scaleTime(time);
    	//double angle = Robot.gyro.getAngle();
    	
    	long t= System.currentTimeMillis();
    	double end = t+time;
    	PID();
    	while (System.currentTimeMillis()> end) {
    	Drivetrain.myDrive.arcadeDrive(.75, rcw);
		Timer.delay(0.02);
    	}
    	Robot.DriveForwardFinished = true;
    } 
    
    	
		
		
    	//gyro test? please help me
    
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.DriveForwardFinished = true) {
        return true;
    }
    	else {
    		return false;
    	}
    }
    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    public double scaleTime(double time) {
    	return 1000.0 * time;
    }
}
