package org.usfirst.frc.team3684.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveForward extends CommandGroup {

    public AutoDriveForward() {
    	addSequential(new DriveForward(1,.75));
    	addSequential (new AutoLift(.75,.5));
		addParallel (new AutoLiftFlip());
		addParallel (new timedintake());
		addSequential(new AutoLift(1.5, .5));
		addParallel(new timedintake());
		addParallel (new AutoLiftFlip());
		addSequential(new AutoLiftFlipAway());
		addSequential (new DriveForward (2,.75));
		addParallel(new timedintake());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
