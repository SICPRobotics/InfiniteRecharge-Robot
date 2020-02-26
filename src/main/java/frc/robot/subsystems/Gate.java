package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;
import frc.robot.SubsystemBaseWrapper;

public final class Gate extends SubsystemBaseWrapper implements PistonSubsystem {
    private DoubleSolenoid solenoid;
    public Gate() {
        super();

        solenoid = new DoubleSolenoid(Constants.Gate.FORWARD_SOLENOID_ID, Constants.Gate.REVERSE_SOLENOID_ID);
    }

    public void pistonForward() {
        solenoid.set(Value.kForward);
    }

    public void pistonReverse() {
        solenoid.set(Value.kReverse);
    }

    public boolean isUp() {
        return solenoid.get() == Value.kReverse;
    }
}
