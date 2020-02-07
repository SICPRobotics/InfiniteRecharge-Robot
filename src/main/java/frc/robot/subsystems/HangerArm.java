package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;
import frc.robot.SubsystemBaseWrapper;

public final class HangerArm extends SubsystemBaseWrapper implements MotorSubsystem {
    private final TalonSRX armMotor;
    private final Encoder encoder;
    public HangerArm() {
        super();
    armMotor = new TalonSRX(Constants.Hanger.ARM_MOTOR_ID);
    encoder = new Encoder(Constants.Hanger.ENCODER_ID_A, Constants.Hanger.ENCODER_ID_B);
    }
    public void setMotor(final double value) {
        armMotor.set(ControlMode.PercentOutput, value);
    }
    public void startArmExtension() {
        setMotor(0.5);
    }

    public void stopArmExtension() {
        setMotor(0);
    }
    public double getArmDistance() {
        return encoder.getDistance();
    }

    public void resetArmEncoder() {
        encoder.reset();
    }
}