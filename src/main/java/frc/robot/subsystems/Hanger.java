package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

public class Hanger {
    private final TalonSRX armMotor;
    private final VictorSPX winchMotor1;
    private final VictorSPX winchMotor2;
    private final Encoder encoder;

    public Hanger() {
        armMotor = new TalonSRX(4);
        winchMotor1 = new VictorSPX(2);
        winchMotor2 = new VictorSPX(3);
        encoder = new Encoder(0, 1);
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