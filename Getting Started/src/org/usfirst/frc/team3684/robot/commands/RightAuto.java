package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.*;
import org.usfirst.frc.team3684.robot.commands.LeftAuto.LeftAutoKind;

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
    		if (Robot.switchright) {
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
    			if (Robot.switchright) {
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
    		new ClawIntake();
    		Robot.driveTrain.setMotors(-1, 1);
    		Robot.forkLift.setMotors(.25, .25);
    		Timer.delay(2.5);
    		Robot.driveTrain.setMotors(0, 0);
    		new ClawOutput();
    		Timer.delay(1);
    		new ClawIntake();
    		Robot.forkLift.setMotors(-.25, -.25);
    		Robot.driveTrain.setMotors(.5, .5);
    		Timer.delay(.75);
    		Robot.driveTrain.setMotors(0, 0);
    		Timer.delay(1.25);
    		Robot.forkLift.setMotors(0, 0);
    		
    		//put code here for scale
    		break;
    	case placeOnRightSwitch:
    		new ClawIntake();
    		Robot.driveTrain.setMotors(-.25, .25);
    		Robot.forkLift.setMotors(.25, .25);
    		Timer.delay(2);
    		Robot.driveTrain.setMotors(.5, .5);
    		Robot.forkLift.setMotors(0, 0);
    		Timer.delay(1);
    		new ClawOutput();
    		Timer.delay(1);
    		new ClawIntake();
    		//put code here for switch
    		break;
    	case driveForward:
    		new ClawIntake();
    		Robot.driveTrain.setMotors(-.25, .25);
    		Timer.delay(4);
    		Robot.driveTrain.setMotors(0, 0);
    		//put code here to drive forward
    		//put code here to drive forward
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
