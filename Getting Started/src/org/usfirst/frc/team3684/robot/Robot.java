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
public class Robot extends IterativeRobot {
		Command m_autonomousCommand;
		Command m_teleopCommand;
		//idk what this does tbh
		SendableChooser m_autoposition= new SendableChooser<>();
		SendableChooser m_scaleorswitch= new SendableChooser<>();
		//making our autoposition and scale/switch preference as choosables
		
	public static boolean scaleright;
	public static boolean ourswitchright;
	public static boolean theirswitchright;
	public static boolean switchselected;
	public static DigitalInput limitswitchtop;
	public static DigitalInput limitswitchbottom;
	//adding booleans for autonomous to use switch or scale.

	private Timer m_timer = new Timer();
	//starting timer
	public static OI m_oi;
	//Instantiating OI
	public static Drivetrain driveTrain;
	public static Forklift forkLift;
	public static ClawRollers clawRollers;
	public static FlipUp flipUp;
	//Instantiating subsystems
	
	
	//initializing drivetrain for use with gyros
	public static DifferentialDrive myDrive;
	public SpeedControllerGroup m_right = new SpeedControllerGroup(Drivetrain.backrightMotor, Drivetrain.rightMotor);
	public SpeedControllerGroup m_left = new SpeedControllerGroup(Drivetrain.backleftMotor, Drivetrain.leftMotor);
	public static Gyro gyro;
	public static final double Kp = 0.03;
	public static final double kAngleSetpoint = 0;
	//more experimental driving code
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		myDrive = new DifferentialDrive(m_left, m_right);
		gyro = new AnalogGyro(0);
		//adding gyro code
		
		
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
		m_autoposition.addDefault("Driveforward", new DriveForward());
		m_autoposition.addObject ("Left", new LeftAuto());
		m_autoposition.addObject ("Center", new CenterAuto());
		m_autoposition.addObject ("Right", new RightAuto());
		SmartDashboard.putData("Auto Position", m_autoposition);
		SmartDashboard.putData("Scale or switch preferred?", m_scaleorswitch);
		SmartDashboard.putData("Claw Rollers", clawRollers);
		SmartDashboard.putData("Drivetrain", driveTrain);
		SmartDashboard.putData("Lift", forkLift);
		CameraServer server = CameraServer.getInstance();
		server.startAutomaticCapture();
		//adding stuff to smartDashboard
		
	}
	
	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
		m_timer.reset();
		m_timer.start();
		//restarting timer
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
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
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
	}
		else {
			m_autonomousCommand= new DriveForward(); 
			m_autonomousCommand.start();
		}//adding booleans for use in autonomous as well as choosing which autonomous to use
		
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
		
		
		//again i honestly don't know what this does, I think that it is used for command scheduling?

	}
	public void disabledInit() {
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		if (m_teleopCommand != null) {
			m_teleopCommand.cancel();
		}//disable auto command AND teleop command.
	}
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		//see above
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
