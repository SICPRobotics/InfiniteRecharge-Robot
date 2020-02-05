package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GroundIntake extends SubsystemBase implements MotorSubsystem {

    private VictorSPX intakeMotor;
    public GroundIntake() {
        intakeMotor = new VictorSPX(9);
    }

    public void start() {
        setMotor(1);
        System.out.println("Ground intake subsystem start!");
    }

    public void stop() {
        setMotor(0);
        System.out.println("Ground intake subsystem end!");
    }

    public void setMotor(double value) {
        intakeMotor.set(ControlMode.PercentOutput, value);
    }
}