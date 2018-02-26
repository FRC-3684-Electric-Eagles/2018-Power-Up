package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class turn90right extends Command {

    public turn90right() {
    	requires(Robot.driveTrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.TurnRightFinished = false;
    	Robot.gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
double angle = Robot.gyro.getAngle();

        Robot.gyro.reset();
    	while (angle>85) {
			Robot.myDrive.arcadeDrive(.5, .5);
			Timer.delay(.01);
		} 
    	Robot.TurnRightFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.TurnRightFinished) {
    		return true;
    	} else {
        return false;
    }
    }
    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setMotors(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
