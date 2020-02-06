package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.PistonSubsystem;

public final class ExtendPiston extends CommandBase {

    private PistonSubsystem elevator;

    @Override
    public void initialize() {
        elevator.pistonForward();
    }

    @Override
    public void end(final boolean interrupted) {
        elevator.pistonReverse();
    }

}