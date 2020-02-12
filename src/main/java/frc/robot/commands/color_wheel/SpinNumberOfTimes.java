package frc.robot.commands.color_wheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ColorWheelSpinner;

public final class SpinNumberOfTimes extends CommandBase {
    private final ColorWheelSpinner spinner;

    public SpinNumberOfTimes(final ColorWheelSpinner spinner) {
        this.spinner = spinner;
        addRequirements(spinner);
    }

    @Override
    public void initialize() {
        this.spinner.resetDistance();
        this.spinner.setToPosition(Constants.ColorWheel.SPINS_TARGET);
    }

    @Override
    public void execute() {
        //this.spinner.setMotor(1.1 - (this.spinner.getSlicesSpun() / (Constants.ColorWheel.SPINS_TARGET)));
    }

    @Override
    public boolean isFinished() {
        return this.spinner.getSlicesSpun() > Constants.ColorWheel.MIN_SPINS;
    }

    @Override
    public void end(final boolean interrupted) {
        this.spinner.stop();
    }
}