/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3684.robot;




import org.usfirst.frc.team3684.robot.commands.AutoDriveForward;
import org.usfirst.frc.team3684.robot.commands.CenterAuto;
import org.usfirst.frc.team3684.robot.commands.DriveForward;
import org.usfirst.frc.team3684.robot.commands.DriveTrain_TankDrive;
import org.usfirst.frc.team3684.robot.commands.LeftAuto;
import org.usfirst.frc.team3684.robot.commands.RightAuto;
import org.usfirst.frc.team3684.robot.commands.TeleopLift;
import org.usfirst.frc.team3684.robot.commands.Turn90Left;
import org.usfirst.frc.team3684.robot.commands.turn90right;
import org.usfirst.frc.team3684.robot.subsystems.ClawRollers;
import org.usfirst.frc.team3684.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3684.robot.subsystems.FlipUp;
import org.usfirst.frc.team3684.robot.subsystems.Forklift;

import com.ctre.phoenix.motorcontrol.can.CANTalon;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
	public static Command m_autonomousCommand;
	public static Command m_teleopCommand;
	//setting commands for teleop and autonomous. I don't believe teleop is used.
	SendableChooser m_autoposition= new SendableChooser<>();
	SendableChooser m_scaleorswitch= new SendableChooser<>();
	//making our autoposition and scale/switch preference as choosables
	public static boolean hasgameData;
	public static boolean isAutonomous;
	public static boolean isTeleop;
	public static boolean scaleright;
	public static boolean ourswitchright;
	public static boolean theirswitchright;
	public static boolean switchselected;
	public static boolean LeftAutoFinished;
	public static boolean CenterAutoFinished;
	public static boolean RightAutoFinished;
	//adding booleans for autonomous to use switch or scale.
	public static OI m_oi;
	//Instantiating OI
	public static Drivetrain driveTrain;
	public static Forklift forkLift;
	public static ClawRollers clawRollers;
	public static FlipUp flipUp;
	//Instantiating subsystems
	public static AnalogGyro gyro;
	//instantiating gyro sandwich
	public static DigitalInput limitswitchtop;
	public static DigitalInput limitswitchbottom;
	//instantiating limit switches
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		gyro = new AnalogGyro(0);
		//initializing gyro
		limitswitchtop = new DigitalInput(1);
		limitswitchbottom = new DigitalInput(0);
		driveTrain= new Drivetrain();
		forkLift= new Forklift();
		clawRollers = new ClawRollers();
		flipUp = new FlipUp();
		//initializing subsystems
		m_oi = new OI();
		//initializing OI
		PowerDistributionPanel pdp = new PowerDistributionPanel();
		m_scaleorswitch.addDefault("Switch", true);
		m_scaleorswitch.addObject("Scale", false);
		m_autoposition.addObject ("Left", new LeftAuto());
		m_autoposition.addDefault ("Center", new CenterAuto());
		m_autoposition.addObject ("Right", new RightAuto());
		//Choosers for shuffleBoard
		SmartDashboard.putData("Auto Position", m_autoposition);
		SmartDashboard.putData("Scale or switch preferred?", m_scaleorswitch);
		SmartDashboard.putData("Claw Rollers", clawRollers);
		SmartDashboard.putData("Drivetrain", driveTrain);
		SmartDashboard.putData("Lift", forkLift);
		SmartDashboard.putData("gyro", gyro);
		SmartDashboard.putData("DriveForward", new DriveForward(3, .5));
		SmartDashboard.putData("DriveMotors", Drivetrain.myDrive);
		SmartDashboard.putData("TurnLeft", new Turn90Left());
		SmartDashboard.putData("TurnRight", new turn90right());
		CameraServer server = CameraServer.getInstance();
		server.startAutomaticCapture();
		//useful data for shuffleBoard.
	}
	
	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
		gyro.reset();
		Robot.isAutonomous = true;
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData!=null) {
			hasgameData = true;
			if(gameData.charAt(0) == 'L')
			{
				ourswitchright = false;
			} else {
				ourswitchright = true;
			}
			if(gameData.charAt(1) == 'L')
			{
				scaleright = false;
			}
			else {
				scaleright = true;
			}
			if(gameData.charAt(2) == 'L')
			{
				theirswitchright = false;
			}
			else {
				theirswitchright = true;
			}
				
			switchselected = (boolean) m_scaleorswitch.getSelected();
				
			m_autonomousCommand = (Command) m_autoposition.getSelected();
			//m_autonomousCommand = new AutoDriveForward();
			if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
			}
		}
		
		//adding booleans for use in autonomous as well as choosing which autonomous to use	
		SmartDashboard.putBoolean("ourswitchonright?", ourswitchright);
		SmartDashboard.putBoolean("scaleonright?", scaleright);
		SmartDashboard.putBoolean("theirswitchonright?", theirswitchright);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		//runs the scheduler so we can run the commands set in autos
	}
	
	/**
	 * This function is called once each time the robot enters teleoperated mode.
	 */
	@Override

	public void teleopInit() {
		gyro.reset();
		Robot.isTeleop = true;
		Robot.isAutonomous = false;
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		//making sure the robit knows we are in teleop.
		Scheduler.getInstance().removeAll();
		//Removing all commands from the scheduler, just in case something went horribly wrong in auto
		 m_teleopCommand = new DriveTrain_TankDrive();
		 m_teleopCommand.start();
		 //I don't understand this part, because I thought I called TankDrive as the default anyway? Meh.
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//command scheduling. Most of the commands are in the OI.

	}
	public void disabledInit() {
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		
		if (m_teleopCommand != null) {
			m_teleopCommand.cancel();
		}//disable auto command and teleop command, but checks if they exist to prevent crashes.
	}
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		//I feel like this shouldn't be here, I'll check with somebody
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
		//i THINK this goes here. 
	}
}
