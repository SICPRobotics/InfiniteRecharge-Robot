package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LinearActuator;

public class LinearActuatorCommand extends CommandBase {

    private LinearActuator actuator;

    public LinearActuatorCommand(LinearActuator linearActulator) {
        actuator = linearActulator;

        addRequirements(linearActulator);
    }

    @Override
    public void initialize() {
        actuator.start();
    }

    @Override
    public void end(boolean interupted) {
        actuator.stop();
    }

    public boolean isFinished() {
        return false;
    }
}