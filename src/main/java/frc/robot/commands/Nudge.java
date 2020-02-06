package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.MotorSubsystem;


/**
 * Sets the motor of the subsystem to the nudgeAmount for one tick.
 */
public final class Nudge extends CommandBase {
    private final MotorSubsystem subsystem;
    private final double nudgeAmount;

    public Nudge(final MotorSubsystem subsystem, final double nudgeAmount) {
        this.subsystem = subsystem;
        this.nudgeAmount = nudgeAmount;
    }

    @Override
    public void initialize() {
        this.subsystem.setMotor(nudgeAmount);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(final boolean interrupted) {
        this.subsystem.setMotor(0);
    }
}