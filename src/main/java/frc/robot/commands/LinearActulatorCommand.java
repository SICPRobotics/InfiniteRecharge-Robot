package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LinearActulator;

public class LinearActulatorCommand extends CommandBase {

    private LinearActulator actuator;

    public LinearActulatorCommand(LinearActulator linearActulator) {
        actuator = new LinearActulator();

        addRequirements(linearActulator);
    }

    @Override
    public void initialize() {
            System.out.println("**** Initializing Actuator ****");
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