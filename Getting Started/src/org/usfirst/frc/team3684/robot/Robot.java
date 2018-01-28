/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3684.robot;




import org.usfirst.frc.team3684.robot.commands.CenterAuto;
import org.usfirst.frc.team3684.robot.commands.DriveForward;
import org.usfirst.frc.team3684.robot.commands.DriveTrain_TankDrive;
import org.usfirst.frc.team3684.robot.commands.LeftAuto;
import org.usfirst.frc.team3684.robot.commands.RightAuto;
import org.usfirst.frc.team3684.robot.subsystems.ClawRollers;
import org.usfirst.frc.team3684.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3684.robot.subsystems.Forklift;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
		Command m_autonomousCommand;
		Command m_teleopCommand;
		SendableChooser m_autoposition= new SendableChooser<>();
		SendableChooser m_scaleorswitch= new SendableChooser<>();
		

	public static boolean scaleright;
	public static boolean switchright;
	public static boolean switchselected;

	private Timer m_timer = new Timer();
	public static OI m_oi;
	public static Drivetrain driveTrain;
	public static Forklift forkLift;
	public static ClawRollers clawRollers;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		driveTrain= new Drivetrain();
		forkLift= new Forklift();
		clawRollers = new ClawRollers();
		m_oi = new OI();
		m_scaleorswitch.addDefault("Switch", true);
		m_scaleorswitch.addObject("Scale", false);
		m_autoposition.addDefault("Driveforward", new DriveForward());
		m_autoposition.addObject ("Left", new LeftAuto());
		m_autoposition.addObject ("Center", new CenterAuto());
		m_autoposition.addObject ("Right", new RightAuto());
		SmartDashboard.putData("Auto mode", m_autoposition);
		CameraServer server = CameraServer.getInstance();
		server.startAutomaticCapture();
		
	}
	
	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
		m_timer.reset();
		m_timer.start();
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L')
		{
			switchright = false;
				
		} else {
			switchright = true;
		}
		if(gameData.charAt(1) == 'L')
		{
			scaleright = false;
		}
		else {
			scaleright = true;
		}
		switchselected = (boolean) m_scaleorswitch.getSelected();
		m_autonomousCommand = (Command) m_autoposition.getSelected();
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
	}
		else {
			m_autonomousCommand= new DriveForward(); 
			m_autonomousCommand.start();
		}
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called once each time the robot enters teleoperated mode.
	 */
	@Override
	public void teleopInit() {
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		 m_teleopCommand = new DriveTrain_TankDrive();
		 m_teleopCommand.start();
		
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {

		Scheduler.getInstance().run();
		

	}
	public void disabledInit() {
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		if (m_teleopCommand != null) {
			m_teleopCommand.cancel();
		}
	}
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
		
	}
}
