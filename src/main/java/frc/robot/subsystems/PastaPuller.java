package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class PastaPuller extends SubsystemBase {
    
    private VictorSPX motor;

    public PastaPuller() {
        motor = new VictorSPX(Constants.PastaPuller.ID);
    }
    
    public void startPulling() {
        motor.set(ControlMode.PercentOutput, 1);
    }

    public void stopPulling() {
        motor.set(ControlMode.PercentOutput, 0);
    }
}