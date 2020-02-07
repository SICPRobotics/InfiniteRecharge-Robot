package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;

import frc.robot.Constants;

public final class Hanger {
    private final TalonSRX armMotor;
    private final VictorSPX winchMotor1;
    private final VictorSPX winchMotor2;
    private final Encoder encoder;

    public Hanger() {
        armMotor = new TalonSRX(Constants.Hanger.ARM_MOTOR);
        winchMotor1 = new VictorSPX(Constants.Hanger.WINCH_MOTOR1);
        winchMotor2 = new VictorSPX(Constants.Hanger.WINCH_MOTOR2);
        encoder = new Encoder(Constants.Hanger.ENCODER_ID_A, Constants.Hanger.ENCODER_ID_B);
    }

    public void startArmExtension() {
        armMotor.set(ControlMode.PercentOutput, 0.5);
    }

    public void stopArmExtension() {
        armMotor.set(ControlMode.PercentOutput, 0);
    }

    public void startPullingUp() {
        winchMotor1.set(ControlMode.PercentOutput, 0.5);
        winchMotor2.set(ControlMode.PercentOutput, 0.5);
    }

    public void stopPullingUp() {
        winchMotor1.set(ControlMode.PercentOutput, 0);
        winchMotor2.set(ControlMode.PercentOutput, 0);
    }

    public double getArmDistance() {
        return encoder.getDistance();
    }

    public void resetArmEncoder() {
        encoder.reset();
    }
}