package frc.robot.commands.color_wheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheelSpinner;

public class SpinNumberOfTimes extends CommandBase {
    private final ColorWheelSpinner spinner;
    private final int minSpins = 3 * 8;
    private final int maxSpins = 5 * 8;

    public SpinNumberOfTimes(ColorWheelSpinner spinner) {
        this.spinner = spinner;
    }

    @Override
    public void execute() {
        this.spinner.set(1 - (this.spinner.getSlicesSpun() / this.minSpins));
    }

    @Override
    public boolean isFinished() {
        return this.spinner.getSlicesSpun() > this.minSpins;
    }

    @Override
    public void end(boolean interrupted) {
        this.spinner.stop();
    }
}