package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.ToggleSubsystem;

/**
 * Toggles a ToggleSubsystem to ON while the command is running.
 */
public final class Toggle extends FunctionalCommand {
    public Toggle(final ToggleSubsystem subsystem) {
        super(subsystem::start, () -> { }, (b) -> subsystem.stop(), () -> false);
        addRequirements((Subsystem) subsystem);
    }
}