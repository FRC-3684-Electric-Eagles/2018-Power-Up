package org.usfirst.frc.team3684.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.CANTalon;
import org.usfirst.frc.team3684.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Forklift extends Subsystem {
	CANTalon m_ForkUP = new CANTalon(RobotMap.ForkliftMotorUp);
	CANTalon m_ForkDOWN = new CANTalon(RobotMap.ForkliftMotorDown);
	CANTalon m_clawmotor1 = new CANTalon (RobotMap.ClawMotor1);
	CANTalon m_clawmotor2 = new CANTalon (RobotMap.ClawMotor2);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

