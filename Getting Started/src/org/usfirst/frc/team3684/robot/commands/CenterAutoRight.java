package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CenterAutoRight extends CommandGroup {

    public CenterAutoRight() {
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
		addSequential(new Turn90Left());
		addParallel (new timedintake());
		addSequential(new AutoLift(2, .65));
		addParallel (new timedintake());
		addSequential(new DriveForward(1.6,.75));
		addParallel (new timedintake());
		addSequential (new WaitCommand(2));
		addSequential(new ClawOutput());
		addSequential (new WaitCommand(1));
		addSequential (new ClawStop());
		//Robot.CenterAutoFinished = true;
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
