package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterAutoRight extends CommandGroup {

    public CenterAutoRight() {
    	addSequential(new DriveForward(1, .75));
		addParallel (new AutoLiftFlip());
		addSequential(new AutoLift(1, .75));
		addParallel (new AutoLiftFlip());
		addSequential(new AutoLiftFlipAway());
		addSequential(new turn90right());
		addParallel(new AutoLift(1, .75));
		addSequential(new DriveForward(2, .75));
		addParallel (new AutoLift(2,.05));
		addSequential(new Turn90Left());
		addSequential(new DriveForward(2, .75));
		addParallel(new AutoLift(2, .05));
		addSequential(new ClawOutput());
		Timer.delay(1);
		Robot.clawRollers.stop();
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
