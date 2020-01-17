package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GroundIntake extends SubsystemBase {

    private VictorSPX intakeMotor;
    public GroundIntake() {
        intakeMotor = new VictorSPX(9);
    }

    public void start() {
        intakeMotor.set(ControlMode.PercentOutput, -1);
        System.out.println("Ground intake subsystem start!");
    }

    public void stop() {
        intakeMotor.set(ControlMode.Disabled, 0);
        System.out.println("Ground intake subsystem end!");
    }
}