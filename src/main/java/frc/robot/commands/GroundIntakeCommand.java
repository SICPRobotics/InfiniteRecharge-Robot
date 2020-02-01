package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GroundIntake;

public class GroundIntakeCommand extends CommandBase {
    private final GroundIntake groundIntakeSubsystem;
    private boolean isRunning;
    private final DoubleSupplier getAmount;
    public GroundIntakeCommand(GroundIntake groundIntakeSubsystem, DoubleSupplier getAmount) {
        this.groundIntakeSubsystem = groundIntakeSubsystem;
        isRunning = false;
        this.getAmount = getAmount;
        addRequirements(groundIntakeSubsystem);
    }

    /*
    @Override
    public void initialize() {
        System.out.println("Ground intake command start!");
        groundIntakeSubsystem.start();
    }
    
    @Override
    public void end(boolean interrupted) {
        System.out.println("Ground intake command stop!");
        groundIntakeSubsystem.stop();
    }*/

    @Override
    public void execute() {
        boolean shouldRun = getAmount.getAsDouble() > 0.1;

        if (shouldRun != isRunning) {
            if (shouldRun) {
                groundIntakeSubsystem.start();
            } else {
                groundIntakeSubsystem.stop();
            }
            isRunning = shouldRun;
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}