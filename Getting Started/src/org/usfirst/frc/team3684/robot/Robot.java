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
import org.usfirst.frc.team3684.robot.subsystems.drivetrain;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
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
		SendableChooser<Command> m_chooser= new SendableChooser<>();
	
	   SpeedController m_frontLeft = new CANTalon(0);
	   SpeedController m_rearLeft = new CANTalon(1);
	   SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);

	   SpeedController m_frontRight = new CANTalon(2);
	   SpeedController m_rearRight = new CANTalon(3);
	   SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
       
	   SpeedController m_clawbase = new CANTalon (4);
	   SpeedController m_clawmid = new CANTalon (5);
	   SpeedController m_clawgrip =new CANTalon (6);
	   SpeedController m_Winch = new CANTalon (7);
	   //DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

	private Timer m_timer = new Timer();
	public static OI m_oi;
	public static drivetrain driveTrain;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		driveTrain= new drivetrain();
		m_chooser.addDefault("Default Auto", new DriveForward());
		// chooser.addObject("My Auto", new MyAutoCommand());
		m_chooser.addObject ("Left Auto", new LeftAuto());
		m_chooser.addObject ("Center Auto", new CenterAuto());
		m_chooser.addObject ("Right Auto", new RightAuto());
		SmartDashboard.putData("Auto mode", m_chooser);
		
		
	}
	
	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
	}
		m_autonomousCommand = m_chooser.getSelected();
		m_timer.reset();
		m_timer.start();
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
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
    	addSequential(new DriveTrain_TankDrive());

		
		//m_drive.tankDrive(m_leftstick.getX(), m_rightstick.getX());
	}

	private void addSequential(DriveTrain_TankDrive driveTrain_TankDrive) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
		
	}
}
