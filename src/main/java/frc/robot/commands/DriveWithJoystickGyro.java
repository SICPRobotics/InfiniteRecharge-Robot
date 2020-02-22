package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;

public class DriveWithJoystickGyro extends PIDCommand {
    private final DriveTrain driveTrain;
    public DriveWithJoystickGyro(final DriveTrain driveTrain) {
        super(new PIDController(0, 0, 0), driveTrain::getGyroVelocity, 0, m_useOutput, null);
        this.driveTrain = driveTrain;
    }
}