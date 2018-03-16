package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterAutoLeft extends CommandGroup {

    public CenterAutoLeft() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	addSequential(new DriveForward(1,.75));
		addParallel (new AutoLiftFlip());
		addSequential(new AutoLift(1, .75));
		addParallel (new AutoLiftFlip());
		addSequential(new AutoLiftFlipAway());
		addSequential(new Turn90Left());
		addParallel(new AutoLift(1, .75));
		addSequential(new DriveForward(2,.75));
		addSequential(new turn90right());
		addSequential(new DriveForward(2,.75));
		addParallel(new AutoLift(2, .05));
		addSequential(new ClawOutput());
		Timer.delay(1);
		Robot.clawRollers.stop();
		//Robot.CenterAutoFinished = true;
    }
}
    // Called just before this Command runs the first time
  /*  protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.CenterAutoFinished = true) {
    		return true;
    	} else {
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
}
*/