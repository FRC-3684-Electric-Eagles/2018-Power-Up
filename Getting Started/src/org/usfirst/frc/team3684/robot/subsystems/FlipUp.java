package org.usfirst.frc.team3684.robot.subsystems;

import org.usfirst.frc.team3684.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.CANTalon;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FlipUp extends Subsystem {
	
	public static VictorSP flipMotor;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public FlipUp() {
		flipMotor = new VictorSP(RobotMap.FlipMotor);
		}
		//the spark may be temporary, we might decide to use a servo, which I don't think requires a motor controller. Never know though. Nope we're using a spark.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
  //  public void setMotors(double input) {
    //	flipMotor.set(input);
    	
  //  }
    
    public void stop() {
    	flipMotor.set(0);
    	//stopping the motors. 
    }
  
}
//i've basically been using the same code for all the subsystems. Hopefully that works out fine. Hopefully.
