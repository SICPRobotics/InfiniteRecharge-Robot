package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GroundIntake;

public class GroundIntakeCommand extends CommandBase {
    GroundIntake groundIntakeSubsystem;
    public GroundIntakeCommand(GroundIntake groundIntakeSubsystem) {
        this.groundIntakeSubsystem = groundIntakeSubsystem;
    }

    public void initialize() {
        groundIntakeSubsystem.start();
    }

    public void end() {
        groundIntakeSubsystem.stop();
    }
}