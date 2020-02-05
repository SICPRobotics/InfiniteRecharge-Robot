package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.PistonSubsystem;

public class ExtendPiston extends CommandBase {

    private PistonSubsystem elevator;

    @Override
    public void initialize() {
        elevator.pistonForward();
    }

    @Override
    public void end(boolean interrupted) {
        elevator.pistonReverse();
    }

}