package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLift<speed, time> extends Command {

    public AutoLift(double speed,long time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.forkLift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.forkLift.setMotors(0, 0);
    	Robot.AutoLiftFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(double speed, long time) {
    	

    	long t= System.currentTimeMillis();
    	double end = t+time;
    	
    	while (System.currentTimeMillis()>end) {
    		Robot.forkLift.setMotors(speed, speed);
    	}
    	Robot.AutoLiftFinished = true;
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.AutoLiftFinished = false) {
        return false;
    	}
    	else {
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.forkLift.setMotors(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
