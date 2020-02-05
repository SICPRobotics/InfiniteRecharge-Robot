package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class PastaPuller extends SubsystemBase implements MotorSubsystem {
    
    private VictorSPX motorRight;
    private VictorSPX motorLeft;

    public PastaPuller() {
        motorRight = new VictorSPX(Constants.PastaPuller.MOTOR_ID_RIGHT);
        motorLeft = new VictorSPX(Constants.PastaPuller.MOTOR_ID_LEFT);
    }
    
    public void startPulling() {
        setMotor(1);
    }

    public void stopPulling() {
        setMotor(0);
    }

    public void setMotor(double value) {
        motorRight.set(ControlMode.PercentOutput, value);
        motorLeft.set(ControlMode.PercentOutput, value * -1);
    }
}