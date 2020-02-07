package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Constants;
import frc.robot.SubsystemBaseWrapper;

public final class HangerWinch extends SubsystemBaseWrapper implements MotorSubsystem {
    private final VictorSPX rightWinchMotor;
    private final VictorSPX leftWinchMotor;

    public HangerWinch() {
        super();
        rightWinchMotor = new VictorSPX(Constants.Hanger.RIGHT_WINCH_MOTOR_ID);
        leftWinchMotor = new VictorSPX(Constants.Hanger.LEFT_WINCH_MOTOR_ID);
    }

    public void setMotor(final double value) {
        rightWinchMotor.set(ControlMode.PercentOutput, -value);
        leftWinchMotor.set(ControlMode.PercentOutput, value);
    }
    public void startPullingUp() {
      setMotor(0.5);
    }

    public void stopPullingUp() {
       setMotor(0);
    }
}