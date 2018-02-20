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
    		Robot.driveTrain.setMotors(.25, .25);
    		Timer.delay(.5);
    		new FlipUp();
    		new ClawIntake();
    		Timer.delay(.5);
    		Robot.forkLift.setMotors(.75, .75);
    		Timer.delay(2);
    		Robot.forkLift.setMotors(0, 0);
    		new LiftFlipAway();
    		Timer.delay(.5);
    		Robot.flipUp.stop();
    		Robot.driveTrain.setMotors(1, 1);
    		Robot.forkLift.setMotors(.25, .25);
    		Robot.clawRollers.stop();
    		//start claw motors, move forward, move lift up
    		Timer.delay(2);
    		Robot.forkLift.setMotors(0, 0);
    		//stop lift
    		Timer.delay(.5);
    		Robot.driveTrain.setMotors(0, 0);
    		new ClawOutput();
    		//stop moving and shoot a box
    		Timer.delay(1);
    		new ClawIntake();
    		Robot.forkLift.setMotors(-.25, -.25);
    		Robot.driveTrain.setMotors(.5, -.5);
    		//turn right 120 deg, move lift down, restart claw intake
    		Timer.delay(.75);
    		Robot.driveTrain.setMotors(0, 0);
    		//stop driving forward
    		Timer.delay(1.25);
    		Robot.forkLift.setMotors(0, 0);
    		Timer.delay(1);
    		Robot.clawRollers.stop();
    		this.end();
    		//restart claw, stop robot from moving more
    		
    		//put code here for scale
    		break;
    	case placeOnLeftSwitch:
    		Robot.driveTrain.setMotors(.25, .25);
    		Timer.delay(.5);
    		Robot.driveTrain.setMotors(0, 0);
    		new FlipUp();
    		new ClawIntake();
    		Timer.delay(.5);
    		Robot.forkLift.setMotors(.75, .75);
    		Timer.delay(2);
    		Robot.forkLift.setMotors(0, 0);
    		new LiftFlipAway();
    		Timer.delay(.5);
    		Robot.flipUp.stop();
    		Robot.driveTrain.setMotors(.25, .25);
    		Robot.forkLift.setMotors(.1, .1);
    		Robot.clawRollers.stop();
    		//start the claw motors, go forward slowly, move the lift up
    		Timer.delay(2);
    		Robot.driveTrain.setMotors(.5, -.5);
    		Robot.forkLift.setMotors(0, 0);
    		//turn right, stop the lift motors
    		Timer.delay(1);
    		Robot.driveTrain.setMotors(.25, .25);
    		Timer.delay(1);
    		Robot.driveTrain.setMotors(0, 0);
    		new ClawOutput();
    		//stop turning, shoot a box
    		Timer.delay(1);
    		Robot.clawRollers.stop();
    		this.end();
    		//restart the claw intake, make sure AUTO doesn't restart.
    		break;
    	case driveForward:
    		Robot.driveTrain.setMotors(.25, .25);
    		Timer.delay(.5);
    		Robot.driveTrain.setMotors(0, 0);
    		new FlipUp();
    		Timer.delay(.5);
    		Robot.forkLift.setMotors(.75, .75);
    		Timer.delay(2);
    		Robot.forkLift.setMotors(0, 0);
    		new LiftFlipAway();
    		Timer.delay(.5);
    		Robot.flipUp.stop();
    		Robot.driveTrain.setMotors(.25, .25);
    		//drive forward slowly
    		Timer.delay(4);
    		Robot.driveTrain.setMotors(0, 0);
    		this.end();
    		//stop
    	default :
    		
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
