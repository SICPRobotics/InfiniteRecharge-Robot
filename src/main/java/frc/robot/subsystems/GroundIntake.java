package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GroundIntake extends SubsystemBase {

    private VictorSP intakeMotor;
    public GroundIntake() {
        intakeMotor = new VictorSP(4);
    }

    public void start() {
        intakeMotor.set(0.5);
    }

    public void stop() {
        intakeMotor.set(0);
    }
}