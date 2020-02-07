package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HangerArm;

public final class ExtendHangerArm extends CommandBase {
    private final HangerArm hanger;

    public ExtendHangerArm(final HangerArm hanger) {
        this.hanger = hanger;
    }

    @Override
    public void initialize() {
        this.hanger.startArmExtension();
    }

    @Override
    public boolean isFinished() {
        return this.hanger.getArmDistance() > 100;
    }

    @Override
    public void end(final boolean interrupted) {
        this.hanger.stopArmExtension();
    }
}