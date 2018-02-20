package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.*;
import org.usfirst.frc.team3684.robot.commands.LeftAuto.LeftAutoKind;
import org.usfirst.frc.team3684.robot.subsystems.FlipUp;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class RightAuto extends CommandGroup {

	
	public enum RightAutoKind {
		placeOnRightScale,
		placeOnRightSwitch,
		driveForward
	}
	
	public RightAutoKind autoKind;
    public RightAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.switchselected) {
    		if (Robot.ourswitchright) {
    			autoKind = RightAutoKind.placeOnRightSwitch;
    			//if we are on the right, prefer the switch, and the switch is on the right, place a block on the right switch.
    		} else {
    			if (Robot.scaleright) {
    				autoKind = RightAutoKind.placeOnRightScale;
    				//if we are on the right, prefer the switch, the switch is on the left, and the scale is on the right, place a block on the right scale.
    			} else { 
    				autoKind = RightAutoKind.driveForward;
    				//if we are on the right, prefer the switch, the switch is on the left, and the scale is on the left, drive forward.
    			}
    		} 
    	} else { 
    		if (Robot.scaleright) {
    			autoKind = RightAutoKind.placeOnRightScale;
    			//If we are on the right, prefer the scale, and the scale is on the right, place a block on the right scale.
    		}else {
    			if (Robot.ourswitchright) {
    				autoKind = RightAutoKind.placeOnRightSwitch;
    				//if we are on the right, prefer the scale, the scale is on the left, and the switch is on the right, place a block on the right switch.
    			}else {
    				autoKind = RightAutoKind.driveForward;
    				//if we are on the right, prefer the scale, the scale is on the left, and the switch is on the left, drive forward.
    			}
    		}
    	}
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (autoKind) {
    	case placeOnRightScale:
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
    		Robot.driveTrain.setMotors(1, 1);
    		Robot.forkLift.setMotors(.25, .25);
    		Robot.clawRollers.stop();
    		//start the claw motors, move lift up, drive forward
    		Timer.delay(2);
    		Robot.forkLift.setMotors(0, 0);
    		//stop moving lift up
    		Timer.delay(.5);
    		Robot.driveTrain.setMotors(0, 0);
    		new ClawOutput();
    		//stop moving forward, shoot out a box
    		Timer.delay(1);
    		new ClawIntake();
    		Robot.forkLift.setMotors(.25, -.25);
    		Robot.driveTrain.setMotors(.5, .5);
    		//intake a cube, move the lift down, and turn left about 120 degrees
    		Timer.delay(.75);
    		Robot.clawRollers.stop();
    		Robot.driveTrain.setMotors(0, 0);
    		//stop moving forward
    		Timer.delay(1.25);
    		Robot.forkLift.setMotors(0, 0);
    		this.end();
    		//stop the lift moving down
    		break;
    	case placeOnRightSwitch:
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
    		Robot.driveTrain.setMotors(.25, .25);
    		Robot.forkLift.setMotors(.1, .1);
    		Robot.clawRollers.stop();
    		//start claw motors, move forward slowly, and move the lift up
    		Timer.delay(2);
    		Robot.driveTrain.setMotors(-.5, .5);
    		Robot.forkLift.setMotors(0, 0);
    		//turn left, stop moving the lift
    		Timer.delay(1);
    		Robot.driveTrain.setMotors(.25, .25);
    		Timer.delay(1);
    		new ClawOutput();
    		//shoot a box
    		Timer.delay(1);
    		Robot.clawRollers.stop();
    		this.end();
    		//restart the claw intake
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
    		Robot.driveTrain.setMotors(.25, .25);
    		//drive forward
    		Timer.delay(4);
    		Robot.driveTrain.setMotors(0, 0);
    		this.end();
    		//stop
    	default:
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
