package frc.robot.commands;

import java.util.function.DoubleConsumer;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.CheesyDriveGetters;
import frc.robot.subsystems.DriveTrain;

public class DriveWithJoystickGyro extends CommandBase {
    private final DriveTrain driveTrain;
    private final CheesyDriveGetters cheesyDriveGetters;
    private final PIDController pidController;
    public DriveWithJoystickGyro(final DriveTrain driveTrain, final CheesyDriveGetters cheesyDriveGetters) {
        pidController = new PIDController(1, 0, 10);
        this.cheesyDriveGetters = cheesyDriveGetters;
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        driveTrain.cheesyDrive(
                -this.cheesyDriveGetters.getForward() * (cheesyDriveGetters.getIsInverted() ? -1 : 1),
                pidController.calculate(driveTrain.getGyroVelocity(), cheesyDriveGetters.getRotate() * 30),
                this.cheesyDriveGetters.getScale()
        );
    }
}