package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hanger;

public class PullHangerUp extends CommandBase {
    private final Hanger hanger;

    public PullHangerUp(Hanger hanger) {
        this.hanger = hanger;
    }

    @Override
    public void initialize() {
        this.hanger.startPullingUp();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        this.hanger.stopPullingUp();
    }
}