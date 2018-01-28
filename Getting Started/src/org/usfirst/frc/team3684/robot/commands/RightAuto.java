package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.*;
import org.usfirst.frc.team3684.robot.commands.LeftAuto.LeftAutoKind;

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
    		//put code here for scale
    		break;
    	case placeOnRightSwitch:
    		//put code here for switch
    		break;
    	case driveForward:
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
