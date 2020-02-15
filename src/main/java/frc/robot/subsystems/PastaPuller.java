package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Constants;
import frc.robot.SubsystemBaseWrapper;

public final class PastaPuller extends SubsystemBaseWrapper implements MotorSubsystem {
    private final VictorSPX motorLeft;

    public PastaPuller() {
        super();
        motorLeft = new VictorSPX(Constants.PastaPuller.LEFT_MOTOR_ID);
    }
    
    public void startPulling() {
        setMotor(1);
    }

    public void stopPulling() {
        setMotor(0);
    }

    public void setMotor(final double value) {
        motorLeft.set(ControlMode.PercentOutput, value * -1);
    }
}