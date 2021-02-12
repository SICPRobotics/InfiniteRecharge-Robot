package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.TrajectoryGeneration;

public final class GenerateTragectory extends CommandBase {
    private final TrajectoryGeneration trajectoryGeneration;

    public GenerateTragectory(TrajectoryGeneration trajectoryGeneration) {
        this.trajectoryGeneration = trajectoryGeneration;
    }

    @Override
    public void initialize() {
        trajectoryGeneration.generateTrajectory();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}