package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Gate extends SubsystemBase {
    private DoubleSolenoid solenoid;
    public Gate() {
        solenoid = new DoubleSolenoid(Constants.Gate.FORWARD_SOLENOID_ID, Constants.Gate.REVERSE_SOLENOID_ID);
    }

    public void extend() {
        solenoid.set(Value.kForward);
    }

    public void retract() {
        solenoid.set(Value.kReverse);
    }
}