package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveWithJoystick extends CommandBase {
    private final DriveTrain driveTrain;
    public DriveWithJoystick(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
    }
}