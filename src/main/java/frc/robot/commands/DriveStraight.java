package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public final class DriveStraight extends CommandBase {
    private final DriveTrain driveTrain;

    /**
     * Distance for the robot to drive, in meters
     */
    private final double distance;

    /**
     * 
     * @param driveTrain
     * @param distance The distance for the robot to drive, in meters
     */
    public DriveStraight(final DriveTrain driveTrain, final double distance) {
        this.driveTrain = driveTrain;
        this.distance = distance;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {

    }
}