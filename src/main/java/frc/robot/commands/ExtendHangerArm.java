package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hanger;

public class ExtendHangerArm extends CommandBase {
    private final Hanger hanger;

    public ExtendHangerArm(Hanger hanger) {
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
    public void end(boolean interrupted) {
        this.hanger.stopArmExtension();
    }
}