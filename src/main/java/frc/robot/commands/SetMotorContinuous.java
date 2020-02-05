package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.MotorSubsystem;

public class SetMotorContinuous extends CommandBase {
    private final MotorSubsystem subsystem;
    private final DoubleSupplier valueGetter;
    public SetMotorContinuous(MotorSubsystem subsystem, DoubleSupplier valueGetter) {
        this.subsystem = subsystem;
        this.valueGetter = valueGetter;
        addRequirements((SubsystemBase) this.subsystem);
    }

    @Override
    public void execute() {
        this.subsystem.setMotor(valueGetter.getAsDouble());
    }
}