package frc.robot.commands.color_wheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ColorWheelSpinner;

public class SpinNumberOfTimes extends CommandBase {
    private final ColorWheelSpinner spinner;

    public SpinNumberOfTimes(ColorWheelSpinner spinner) {
        this.spinner = spinner;
    }

    @Override
    public void execute() {
        this.spinner.set(1.1 - (this.spinner.getSlicesSpun() / (Constants.ColorWheel.SPINS_TARGET)));
    }

    @Override
    public boolean isFinished() {
        return this.spinner.getSlicesSpun() > Constants.ColorWheel.MIN_SPINS;
    }

    @Override
    public void end(boolean interrupted) {
        this.spinner.stop();
    }
}