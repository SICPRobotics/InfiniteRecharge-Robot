package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GroundIntake;

public class GroundIntakeCommand extends CommandBase {
    GroundIntake groundIntakeSubsystem;
    public GroundIntakeCommand(GroundIntake groundIntakeSubsystem) {
        this.groundIntakeSubsystem = groundIntakeSubsystem;
    }

    @Override
    public void initialize() {
        System.out.println("Ground intake command start!");
        groundIntakeSubsystem.start();
    }
    
    @Override
    public void end(boolean interrupted) {
        System.out.println("Ground intake command stop!");
        groundIntakeSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}