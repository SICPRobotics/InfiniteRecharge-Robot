package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.PistonSubsystem;

public final class ExtendPiston extends CommandBase {
    private final PistonSubsystem piston;
    public ExtendPiston(final PistonSubsystem piston) {
        this.piston = piston;
    }

    @Override
    public void initialize() {
        piston.pistonForward();
    }

    @Override
    public void end(final boolean interrupted) {
        piston.pistonReverse();
    }

}