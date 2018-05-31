package org.usfirst.frc.team3684.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveForward extends CommandGroup {

    public AutoDriveForward() {
    	addSequential(new DriveForward(1,.75));
    	//addSequential (new AutoLift(.75,.5));
		//addParallel (new AutoLiftFlip());
		//addSequential(new AutoLift(1.5, .5));
		//addParallel (new AutoLiftFlip());
		//addSequential(new AutoLiftFlipAway());
		addSequential (new DriveForward (2,.75));
		//addParallel(new timedintake());
    }
}
