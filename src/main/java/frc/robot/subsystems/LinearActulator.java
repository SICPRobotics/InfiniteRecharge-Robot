package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LinearActulator extends SubsystemBase {
    private final Servo actuator;
    public LinearActulator() {
        actuator = new Servo(0);
    }

    public void start() {
        actuator.set(.5);
    }

    public void stop() {
        actuator.set(0);
    }
}