package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLiftFlip<in> extends Command {

    public AutoLiftFlip(boolean in) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(boolean in) {
    	if (in) {
    		Robot.flipUp.flipMotor.set(.2);
    		Timer.delay(1);
    		Robot.flipUp.stop();
    	} else {
    		Robot.flipUp.flipMotor.set(-.2);
    		Timer.delay(1);
    		Robot.flipUp.stop();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
