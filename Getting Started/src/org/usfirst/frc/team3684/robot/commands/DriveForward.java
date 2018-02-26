package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.Robot;
import org.usfirst.frc.team3684.robot.subsystems.FlipUp;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveForward<time> extends CommandGroup {

    public DriveForward(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	time = scaleTime(time);
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
    	double angle = Robot.gyro.getAngle();
    	
    	long t= System.currentTimeMillis();
    	double end = t+time;
    	
    	while (System.currentTimeMillis()> end) {
    	Robot.myDrive.arcadeDrive(1.0, -angle * Robot.Kp);
		Timer.delay(0.01);
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
