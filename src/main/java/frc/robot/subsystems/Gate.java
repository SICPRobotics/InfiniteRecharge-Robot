package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public final class Gate extends SubsystemBase implements PistonSubsystem {
    private DoubleSolenoid solenoid;
    public Gate() {
        solenoid = new DoubleSolenoid(Constants.Gate.FORWARD_SOLENOID_ID, Constants.Gate.REVERSE_SOLENOID_ID);
    }

    public void pistonForward() {
        solenoid.set(Value.kForward);
    }

    public void pistonReverse() {
        solenoid.set(Value.kReverse);
    }
}
