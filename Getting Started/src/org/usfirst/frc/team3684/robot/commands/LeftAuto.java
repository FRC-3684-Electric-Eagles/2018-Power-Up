package org.usfirst.frc.team3684.robot.commands;
import org.usfirst.frc.team3684.robot.*;
import org.usfirst.frc.team3684.robot.subsystems.FlipUp;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftAuto extends CommandGroup {
	
	public enum LeftAutoKind {
		placeOnLeftScale,
		placeOnLeftSwitch,
		driveForward
	}
	//no idea what an enum is, I think this is a list of things to choose from, kinda like a boolean but with more than one option. 
	
	public LeftAutoKind autoKind;
	//naming the LeftAutoKind as autoKind
    public LeftAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.LeftAutoFinished = false;
    	if (Robot.switchselected) {
    		if (Robot.ourswitchright) {
    			// if we are on left, prefer the switch, and the switch is on the right, check for the scale.
    			if (Robot.scaleright) {
    				autoKind = LeftAutoKind.driveForward;
    				//if we are on the left, prefer the switch, and the switch is on the right, and the scale is on the right, drive forward.
    			}
    			else {
    				autoKind = LeftAutoKind.placeOnLeftScale;
    				//if we are on the left, prefer the switch, and the switch is on the right, and the scale is on the left, place a block on the left scale. 
    			}
    			
    		} else {
				autoKind = LeftAutoKind.placeOnLeftSwitch;
				//if we are on the left, prefer the switch, and the switch is on the left, place a block on the left switch.
			}
    	} else {
    		if (Robot.scaleright) {
    			if (Robot.ourswitchright) {
    				autoKind = LeftAutoKind.driveForward;
    				//if we are on the left, prefer the scale, and the scale is on the right, and the switch is on the right, drive forward.
    			} else {
    				autoKind = LeftAutoKind.placeOnLeftSwitch;
    				//if we are on the left, prefer the scale, and the scale is on the right, and the switch is on the left, place a block on the left switch.
    			}
    		}else {
    			autoKind = LeftAutoKind.placeOnLeftScale;
    			//if we are on the left, prefer the scale, and the scale is on the left, place a block on the left scale.
    		}
    	}
    }
    	
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (autoKind) {
    	case placeOnLeftScale:
    		LeftScaleAuto ScaleAuto = new LeftScaleAuto();
    		ScaleAuto.start();
    		/*
    		addSequential(new DriveForward(1));
    		addParallel (new AutoLiftFlip(true));
    		addSequential(new AutoLift(.75, 1000));
    		addSequential(new AutoLiftFlip(false));
    		addSequential(new DriveForward(7));
    		addSequential(new AutoLift(.75, 1000));
    		addSequential(new turn90right());
    		addSequential(new ClawOutput());
    		Timer.delay(1);
    		Robot.clawRollers.stop();
    		Robot.LeftAutoFinished = true;
    		
    		
    		
    		
    		
    		Robot.driveTrain.setMotors(.5,.5);
        	FlipUp.flipMotor.set(.1);
        	Timer.delay(1);
        	Robot.driveTrain.setMotors(0, 0);
        	Robot.forkLift.setMotors(.5,.5);
        	Timer.delay(1);
        	Robot.forkLift.setMotors(0, 0);
        	FlipUp.flipMotor.set(-.10);
        	Timer.delay(1);
        	FlipUp.flipMotor.set(0);
        	//
    		Robot.driveTrain.setMotors(.5, .5);
    		//start claw motors, move forward 25 feet, move lift up
    		Timer.delay(6);
    		Robot.forkLift.setMotors(.5, .5);
    		//stop lift
    		Timer.delay(1);
    		Robot.driveTrain.setMotors(-.5, .5);
    		Timer.delay(1);
    		Robot.forkLift.setMotors(0, 0);
    		new ClawOutput();
    		//stop moving and shoot a box
    		Timer.delay(1);
    		Robot.clawRollers.stop();
    		Robot.LeftAutoFinished = true;
    		//restart claw, stop robot from moving more
    		
    		//put code here for scale
    		 *
    		 */
    		break;
    	case placeOnLeftSwitch:
    		LeftSwitchAuto SwitchAuto = new LeftSwitchAuto();
    		SwitchAuto.start();
    		/*
    		addSequential(new DriveForward(1));
    		addParallel (new AutoLiftFlip(true));
    		addSequential(new AutoLift(.75, 1000));
    		addSequential(new AutoLiftFlip(false));
    		addSequential(new Turn90Left());
    		addSequential(new DriveForward(1));
    		addSequential(new turn90right());
    		addParallel(new AutoLift(.75, 1000));
    		addSequential(new DriveForward(2));
    		addParallel(new AutoLift(.05, 2000));
    		addSequential(new ClawOutput());
    		Timer.delay(1);
    		Robot.clawRollers.stop();
    		Robot.LeftAutoFinished = true;
    		
    		Robot.driveTrain.setMotors(.5,.5);
        	FlipUp.flipMotor.set(.1);
        	Timer.delay(1);
        	Robot.driveTrain.setMotors(0, 0);
        	Robot.forkLift.setMotors(.5,.5);
        	Timer.delay(1);
        	Robot.forkLift.setMotors(0, 0);
        	FlipUp.flipMotor.set(-.10);
        	Timer.delay(1);
        	FlipUp.flipMotor.set(0);
        	Robot.driveTrain.setMotors(-.5, .5);
    		//turn right
    		Timer.delay(1.525);
    		Robot.driveTrain.setMotors(.5, .5);
    		Robot.forkLift.setMotors(.1, .1);
    		Timer.delay(1);
    		Robot.driveTrain.setMotors(.5, -.5);
    		//turn left
    		Timer.delay(1);
    		Robot.forkLift.setMotors(0, 0);
    		Robot.driveTrain.setMotors(.5, .5);
    		//drive forward, stop the lift motors
    		Timer.delay(2);
    		Robot.driveTrain.setMotors(0, 0);
    		new ClawOutput();
    		//stop turning, shoot a box
    		Timer.delay(1);
    		Robot.clawRollers.stop();
    		Robot.LeftAutoFinished = true;
    		*/
    		//restart the claw intake, make sure AUTO doesn't restart.
    		break;
    	case driveForward:
    		
    		Robot.driveTrain.setMotors(.5,.5);
        	FlipUp.flipMotor.set(.1);
        	Timer.delay(1);
        	Robot.driveTrain.setMotors(0, 0);
        	Robot.forkLift.setMotors(.5,.5);
        	Timer.delay(1);
        	Robot.forkLift.setMotors(0, 0);
        	FlipUp.flipMotor.set(-.10);
        	Timer.delay(1);
        	FlipUp.flipMotor.set(0);
    		Robot.driveTrain.setMotors(.5, .5);
    		//drive forward slowly
    		Timer.delay(4);
    		Robot.driveTrain.setMotors(0, 0);
    		Robot.LeftAutoFinished = true;
    		//stop
    	default :
    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.LeftAutoFinished)
        return true;
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
}
