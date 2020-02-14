package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public final class DriveWithJoystickForward extends CommandBase {
    private final DriveTrain driveTrain;
    private final DoubleSupplier moveValueGetter;
    private final DoubleSupplier rotateValueGetter;
    private final DoubleSupplier adjustValueGetter;

    public DriveWithJoystickForward(final DriveTrain driveTrain, final DoubleSupplier moveValueGetter, final DoubleSupplier rotateValueGetter, final DoubleSupplier adjustValueGetter) {
        this.driveTrain = driveTrain;
        this.moveValueGetter = moveValueGetter;
        this.rotateValueGetter = rotateValueGetter;
        this.adjustValueGetter = adjustValueGetter;
    }

    @Override
    public void execute() {
        this.driveTrain.cheesyDrive(-this.moveValueGetter.getAsDouble(), -this.rotateValueGetter.getAsDouble(), this.adjustValueGetter.getAsDouble());
    }

}