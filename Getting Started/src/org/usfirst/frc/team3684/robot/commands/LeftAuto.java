package org.usfirst.frc.team3684.robot.commands;
import org.usfirst.frc.team3684.robot.*;
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
	
	public LeftAutoKind autoKind;

    public LeftAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.switchselected) {
    		if (Robot.switchright) {
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
    			if (Robot.switchright) {
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
    		//put code here for scale
    		break;
    	case placeOnLeftSwitch:
    		//put code here for switch
    		break;
    	case driveForward:
    		//put code here to drive forward
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
