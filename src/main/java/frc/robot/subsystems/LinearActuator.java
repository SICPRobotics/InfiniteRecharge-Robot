package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LinearActuator extends SubsystemBase {
    private final Servo actuator;
    public LinearActuator() {
        actuator = new Servo(0);
    }

    public void start() {
        actuator.set(0.5);
        System.out.println("Started!");
        actuator.set(1.0);
    }

    public void stop() {
        //actuator.set(0);
        System.out.println("Stopped!");
    }
}