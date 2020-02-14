package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public final class Invert extends CommandBase {
    private final DriveTrain driveTrain;

    public Invert(final DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        driveTrain.invert();
    }
    @Override
    public void end(boolean interrupted) {
        
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}