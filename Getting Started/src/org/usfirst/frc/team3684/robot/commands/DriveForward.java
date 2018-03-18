package org.usfirst.frc.team3684.robot.commands;

import org.usfirst.frc.team3684.robot.Robot;
import org.usfirst.frc.team3684.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3684.robot.subsystems.FlipUp;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveForward extends Command {
	
	int P = 1;
	//int I = 1;
	//int D = 1;
    int integral, previous_error = 0;
    public Timer timer;
    public double setpoint;
	private double rcw;
	public double time;
	public double power;
    public double staticdouble;
    public DriveForward(double time, double power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.time = time;
    	this.timer = new Timer();
    	this.power = power;
    }
    
    
    public void setSetpoint(int setpoint)
    {
        this.setpoint = setpoint;
    }
    
    


    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	Robot.driveTrain.setMotors(0, 0);
    	setpoint = Robot.gyro.getAngle();
    	
    }
    public void PID(){
        double error = setpoint - Robot.gyro.getAngle(); // Error = Target - Actual
        //this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        //double derivative = (error - this.previous_error) / .02;
        rcw = P*error; 
        //+ I*this.integral + D*derivative;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Drivetrain.myDrive.arcadeDrive (-power, (Robot.gyro.getAngle()-setpoint)*.2);

    	
    	}
     
    
    	
		
		
    	//gyro test? please help me
    
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return this.timer.get() >= time;
    	
    	}
    
    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setMotors(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
