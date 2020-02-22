package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.CheesyDriveGetters;
import frc.robot.subsystems.DriveTrain;

public final class DriveWithJoystick extends CommandBase {
    private final DriveTrain driveTrain;
    private final CheesyDriveGetters cheesyDriveGetters;
    private final boolean isInverted;

    public DriveWithJoystick(final DriveTrain driveTrain, final CheesyDriveGetters cheesyDriveGetters) {
        this(driveTrain, cheesyDriveGetters, false);    
    }

    public DriveWithJoystick(final DriveTrain driveTrain, final CheesyDriveGetters cheesyDriveGetters, final boolean isInverted) {
        this.driveTrain = driveTrain;
        this.cheesyDriveGetters = cheesyDriveGetters;
        this.isInverted = isInverted;
        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        this.driveTrain.cheesyDrive(-this.cheesyDriveGetters.getForward() * (isInverted ? -1 : 1), this.cheesyDriveGetters.getRotate(), this.cheesyDriveGetters.getScale());
    }

}