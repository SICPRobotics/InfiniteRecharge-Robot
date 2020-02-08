package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LED;

public final class LEDBlue extends CommandBase {
    private final LED led;

    public LEDBlue(final LED led) {
        super();
        this.led = led;
    }
    @Override
    public void initialize() {
        led.blue();
    }
    @Override
    public void end(final boolean interrupted) {
        super.end(interrupted);
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}