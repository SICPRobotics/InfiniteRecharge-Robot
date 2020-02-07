package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HangerWhinch;

public final class PullHangerUp extends CommandBase {
    private final HangerWhinch hanger;

    public PullHangerUp(final HangerWhinch hanger) {
        this.hanger = hanger;
    }

    @Override
    public void initialize() {
        this.hanger.startPullingUp();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(final boolean interrupted) {
        this.hanger.stopPullingUp();
    }
}