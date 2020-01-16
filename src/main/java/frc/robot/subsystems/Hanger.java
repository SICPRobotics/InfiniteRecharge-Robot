package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;

public class Hanger {
    private final VictorSP armMotor;
    private final VictorSP winchMotor1;
    private final VictorSP winchMotor2;

    public Hanger() {
        armMotor = new VictorSP(1);
        winchMotor1 = new VictorSP(2);
        winchMotor2 = new VictorSP(3);
    }

    public void startArmExtension() {
        armMotor.set(0.5);
    }

    public void stopArmExtension() {
        armMotor.setDisabled();
    }

    public void startPullingUp() {
        winchMotor1.set(0.5);
        winchMotor2.set(0.5);
    }

    public void stopPullingUp() {
        winchMotor1.set(0.5);
        winchMotor2.set(0.5);
    }
}