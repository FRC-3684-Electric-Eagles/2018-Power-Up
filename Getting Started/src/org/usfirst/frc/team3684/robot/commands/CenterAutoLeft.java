package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CenterAutoLeft extends CommandGroup {

    public CenterAutoLeft() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	addSequential(new DriveForward(1,.75));
    	addSequential (new AutoLift(.75,.5));
		addParallel (new AutoLiftFlip());
		addParallel (new timedintake());
		addSequential(new AutoLift(1.5, .5));
		addParallel(new timedintake());
		addParallel (new AutoLiftFlip());
		addSequential(new AutoLiftFlipAway());
		addSequential(new DriveForward(2,.75));
		addParallel (new timedintake());
		addSequential(new turn90right());
		addParallel (new timedintake());
		addSequential(new AutoLift(2.1, .65));
		addParallel (new timedintake());
		addSequential(new DriveForward(2.75,.75));
		addParallel (new timedintake());
		addSequential (new WaitCommand(2));
		addSequential(new ClawOutput());
		addSequential (new WaitCommand(1));
		addSequential (new ClawStop());
		
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