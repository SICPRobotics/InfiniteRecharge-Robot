package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.CheesyDriveGetters;
import frc.robot.subsystems.DriveTrain;

public final class DriveWithJoystick extends CommandBase {
    private final DriveTrain driveTrain;
    private final CheesyDriveGetters cheesyDriveGetters;

    public DriveWithJoystick(final DriveTrain driveTrain, final CheesyDriveGetters cheesyDriveGetters) {
        this.driveTrain = driveTrain;
        this.cheesyDriveGetters = cheesyDriveGetters;
        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        this.driveTrain.cheesyDrive(-this.cheesyDriveGetters.getForward() * (cheesyDriveGetters.getIsInverted() ? -1 : 1), this.cheesyDriveGetters.getRotate(), this.cheesyDriveGetters.getScale());
    }

}