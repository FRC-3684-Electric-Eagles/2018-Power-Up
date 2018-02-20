package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.*;
import org.usfirst.frc.team3684.robot.subsystems.FlipUp;

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
    	if (Robot.ourswitchright) {
    		autoKind = CenterAutoKind.placeOnRightSwitch;
    		//if the switch is on the right, place it on the right. 
    	} else {
    		autoKind = CenterAutoKind.placeOnLeftSwitch;
    		//if the switch is on the left, place it on the left
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (autoKind) {
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
    		Robot.driveTrain.setMotors(-.5, .5);
    		Robot.forkLift.setMotors(.25, .25);
    		Robot.clawRollers.stop();
    		//turn claw on, turn left, and start the claw
    		Timer.delay(1);
    		Robot.driveTrain.setMotors(.5, .5);
    		//move forward while turned to the left
    		Timer.delay(1);
    		Robot.forkLift.setMotors(0,0);
    		Robot.driveTrain.setMotors(.5, -.5);
    		//stop the forklift and turn to the right
    		Timer.delay(1);
    		Robot.driveTrain.setMotors(.5, .5);
    		//drive forward
    		Timer.delay(2);
    		new ClawOutput();
    		//shoot box
    		Timer.delay(1);
    		new ClawIntake();
    		this.end();
    		//turn claw back on
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
    		Robot.clawRollers.stop();
    		Robot.driveTrain.setMotors(.5, -.5);
    		Robot.forkLift.setMotors(.25, .25);
    		//start claw, turn right, move lift up
    		Timer.delay(1);
    		Robot.driveTrain.setMotors(.5, .5);
    		//go forward
    		Timer.delay(1);
    		Robot.forkLift.setMotors(0,0);
    		Robot.driveTrain.setMotors(-.5, .5);
    		//stop forklift, turn left
    		Timer.delay(1);
    		Robot.driveTrain.setMotors(.5, .5);
    		//drive forward
    		Timer.delay(2);
    		new ClawOutput();
    		//shoot a box
    		Timer.delay(1);
    		new ClawIntake();
    		this.end();
    		
    		//restart claw
    		//put code here for right switch
    		break;
    	case driveForward:
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
    		Robot.driveTrain.setMotors(-.25, .25);
    		Timer.delay(4);
    		Robot.driveTrain.setMotors(0, 0);
    		this.end();
    		//something must be broken if this happens, but just in case it's still here. It will start the claw and flip up the thingy as well as cross the auto line. If for some reason you want to force a driveforward, use the driveforward command.
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
