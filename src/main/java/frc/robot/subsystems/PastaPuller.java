package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class PastaPuller extends SubsystemBase {
    
    private VictorSPX motorRight;
    private VictorSPX motorLeft;

    public PastaPuller() {
        motorRight = new VictorSPX(Constants.PastaPuller.MOTOR_ID_RIGHT);
        motorLeft = new VictorSPX(Constants.PastaPuller.MOTOR_ID_LEFT);
        
    }
    
    public void startPulling() {
        motorRight.set(ControlMode.PercentOutput, 1);
        motorLeft.set(ControlMode.PercentOutput, -1);
    }

    public void stopPulling() {
        motorRight.set(ControlMode.PercentOutput, 0);
        motorLeft.set(ControlMode.PercentOutput, 0);
        
    }
}