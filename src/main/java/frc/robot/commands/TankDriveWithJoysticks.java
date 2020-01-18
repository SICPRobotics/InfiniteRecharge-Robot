package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TankDriveWithJoysticks extends CommandBase {
    private final DoubleSupplier getLeft;
    private final DoubleSupplier getRight;
    private final DriveTrain driveTrain;

    public TankDriveWithJoysticks(DoubleSupplier getLeft, DoubleSupplier getRight, DriveTrain driveTrain) {
        this.getLeft = getLeft;
        this.getRight = getRight;
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        driveTrain.tankDrive(getLeft.getAsDouble(), getRight.getAsDouble());
    }
}