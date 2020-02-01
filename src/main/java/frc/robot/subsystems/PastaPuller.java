package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class PastaPuller extends SubsystemBase {
    
    private VictorSPX motorR;
    private VictorSPX motorL;

    public PastaPuller() {
        motorR = new VictorSPX(Constants.PastaPuller.IDR);
        motorL = new VictorSPX(Constants.PastaPuller.IDL);
        
    }
    
    public void startPulling() {
        motorR.set(ControlMode.PercentOutput, 1);
        motorL.set(ControlMode.PercentOutput, -1);
    }

    public void stopPulling() {
        motorR.set(ControlMode.PercentOutput, 0);
        motorL.set(ControlMode.PercentOutput, 0);
        
    }
}