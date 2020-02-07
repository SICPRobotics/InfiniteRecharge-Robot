package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public final class GroundIntake extends SubsystemBase implements MotorSubsystem {

    private VictorSPX intakeMotor;
    public GroundIntake() {
        intakeMotor = new VictorSPX(Constants.GroundIntake.MOTOR_ID);
    }

    public void start() {
        setMotor(1);
        System.out.println("Ground intake subsystem start!");
    }

    public void stop() {
        setMotor(0);
        System.out.println("Ground intake subsystem end!");
    }

    public void setMotor(final double value) {
        intakeMotor.set(ControlMode.PercentOutput, value);
    }
}