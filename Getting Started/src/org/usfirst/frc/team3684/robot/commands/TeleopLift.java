package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.Robot;
import org.usfirst.frc.team3684.robot.RobotMap;
import org.usfirst.frc.team3684.robot.OI;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopLift extends Command {

    public TeleopLift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.forkLift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.forkLift.setMotors(0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double input = Robot.m_oi.m_operatorstick.getY();
    	if (!(input>0)) {
    		if(!(Robot.limitswitchtop.get())) {
    			Robot.forkLift.setMotors(0, 0);
    		} else {
    			Robot.forkLift.setMotors(-input, -input);
    
    		}
    		
    	} else {
    		if (!(Robot.limitswitchbottom.get())) {
    			Robot.forkLift.setMotors(0, 0);
    		} else {
    			Robot.forkLift.setMotors(-input, -input);
    		}
    		
    	}
    	
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.forkLift.setMotors(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
