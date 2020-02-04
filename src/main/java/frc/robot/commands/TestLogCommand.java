package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class TestLogCommand extends CommandBase {
    private final String message;
    public TestLogCommand(String message) {
        this.message = message;
    }
    @Override
    public void initialize() {
        System.out.println(" >> " + message);
    }
}