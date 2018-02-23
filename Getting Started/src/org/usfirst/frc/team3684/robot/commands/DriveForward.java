package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.Robot;
import org.usfirst.frc.team3684.robot.subsystems.FlipUp;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveForward extends CommandGroup {

    public DriveForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.setMotors(0, 0);
    	double angle = Robot.gyro.getAngle();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angle = Robot.gyro.getAngle();
		Robot.myDrive.arcadeDrive(1.0, -angle * Robot.Kp);
		Timer.delay(0.01);
    	//gyro test? please help me
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
