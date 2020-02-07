package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public final class PastaPuller extends SubsystemBase implements MotorSubsystem {
    private final VictorSPX motorRight;
    private final VictorSPX motorLeft;

    public PastaPuller() {
        motorRight = new VictorSPX(Constants.PastaPuller.RIGHT_MOTOR_ID);
        motorLeft = new VictorSPX(Constants.PastaPuller.LEFT_MOTOR_ID);
    }
    
    public void startPulling() {
        setMotor(1);
    }

    public void stopPulling() {
        setMotor(0);
    }

    public void setMotor(final double value) {
        motorRight.set(ControlMode.PercentOutput, value);
        motorLeft.set(ControlMode.PercentOutput, value * -1);
    }
}