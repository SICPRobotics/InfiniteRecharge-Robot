package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public final class DriveWithoutJoystickInverted extends CommandBase {
    private final DriveTrain driveTrain;
    private final DoubleSupplier moveValueGetter;
    private final DoubleSupplier rotateValueGetter;

    public DriveWithoutJoystickInverted(final DriveTrain driveTrain, final DoubleSupplier moveValueGetter,
            final DoubleSupplier rotateValueGetter) {
        this.driveTrain = driveTrain;
        this.moveValueGetter = moveValueGetter;
        this.rotateValueGetter = rotateValueGetter;
        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        this.driveTrain.cheesyDrive(-this.moveValueGetter.getAsDouble(), this.rotateValueGetter.getAsDouble());
    }
}