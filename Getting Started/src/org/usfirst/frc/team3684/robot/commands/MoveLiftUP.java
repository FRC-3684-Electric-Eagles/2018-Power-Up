package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLiftUP extends Command {

    public MoveLiftUP() {
    	  // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//DigitalInput limitSwitchtop;
    	//limitSwitchtop = new DigitalInput(0);
    	requires(Robot.forkLift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.forkLift.setMotors(0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if  (!(Robot.limitswitchtop.get())) {
    		Robot.forkLift.setMotors(.05, .05);
    		
    	} else {
    		
    		Robot.forkLift.setMotors(.50,.50);
    		
    		
    	}
    	//moves the claw up
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    //	if (Robot.limitswitchtop) {
    	//	return true;
    //	} else 
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.forkLift.setMotors(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
