package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveWithJoystick extends CommandBase {
    private final DriveTrain driveTrain;
    private final DoubleSupplier moveValueGetter;
    private final DoubleSupplier rotateValueGetter;
    
    public DriveWithJoystick(DriveTrain driveTrain, DoubleSupplier moveValueGetter, DoubleSupplier rotateValueGetter) {
        this.driveTrain = driveTrain;
        this.moveValueGetter = moveValueGetter;
        this.rotateValueGetter = rotateValueGetter;
        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        this.driveTrain.cheesyDrive(this.moveValueGetter.getAsDouble(), this.rotateValueGetter.getAsDouble());
    }
}