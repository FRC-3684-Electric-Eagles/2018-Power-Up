package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterAuto extends CommandGroup {
	
	public enum CenterAutoKind {
		placeOnLeftSwitch,
		placeOnRightSwitch,
		driveForward
	}
	public CenterAutoKind autoKind;
    public CenterAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.switchright) {
    		autoKind = CenterAutoKind.placeOnRightSwitch;
    	} else {
    		autoKind = CenterAutoKind.placeOnLeftSwitch;
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (autoKind) {
    	case placeOnLeftSwitch:
    		new ClawIntake();
    		Robot.driveTrain.setMotors(.5, .5);
    		Robot.forkLift.setMotors(.25, .25);
    		Timer.delay(1);
    		Robot.driveTrain.setMotors(-.5, .5);
    		Timer.delay(1);
    		Robot.forkLift.setMotors(0,0);
    		Robot.driveTrain.setMotors(-.5, -.5);
    		Timer.delay(1);
    		Robot.driveTrain.setMotors(.5, .5);
    		Timer.delay(2);
    		new ClawOutput();
    		Timer.delay(1);
    		new ClawIntake();
    		break;
    	case placeOnRightSwitch:
    		new ClawIntake();
    		Robot.driveTrain.setMotors(-.5, -.5);
    		Robot.forkLift.setMotors(.25, .25);
    		Timer.delay(1);
    		Robot.driveTrain.setMotors(-.5, .5);
    		Timer.delay(1);
    		Robot.forkLift.setMotors(0,0);
    		Robot.driveTrain.setMotors(.5, .5);
    		Timer.delay(1);
    		Robot.driveTrain.setMotors(.5, .5);
    		Timer.delay(2);
    		new ClawOutput();
    		Timer.delay(1);
    		new ClawIntake();
    		//put code here for right switch
    		break;
    	case driveForward:
    		new ClawIntake();
    		Robot.driveTrain.setMotors(.25, .25);
    		Timer.delay(4);
    		Robot.driveTrain.setMotors(0, 0);
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
