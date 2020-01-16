package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GroundIntake;

public class GroundIntakeCommand extends CommandBase {
    GroundIntake groundIntakeSubsystem;
    public GroundIntakeCommand(GroundIntake groundIntakeSubsystem) {
        this.groundIntakeSubsystem = groundIntakeSubsystem;
    }

    public void initialize() {
        System.out.println("Ground intake command start!");
        groundIntakeSubsystem.start();
    }

    public void end() {
        System.out.println("Ground intake command stop!");
        groundIntakeSubsystem.stop();
    }

    public boolean isFinished() {
        return true;
    }
}