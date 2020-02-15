package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Constants;
import frc.robot.SubsystemBaseWrapper;

public final class PastaPuller extends SubsystemBaseWrapper implements MotorSubsystem {
    private final VictorSPX motorRight;
    private final VictorSPX motorLeft;

    public PastaPuller() {
        super();
        
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