package frc.robot.commands.color_wheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.game_elements.ColorWheelColor;
import frc.robot.subsystems.ColorWheelSpinner;

public final class SpinToColor extends CommandBase {
    private final ColorWheelSpinner spinner;
    private double slicesToSpin;
    private ColorWheelColor targetColor;
    public SpinToColor(final ColorWheelSpinner spinner) {
        this.spinner = spinner;
        addRequirements(spinner);
    }

    @Override
    public void initialize() {
        this.spinner.resetDistance();
        targetColor = spinner.getTargetColor();
        slicesToSpin = this.spinner.getSlicesUpTo(targetColor);
    }

    @Override
    public void execute() {
        this.spinner.setMotor(0.5/*1.1 - (this.spinner.getSlicesSpun() / slicesToSpin)*/);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(this.slicesToSpin - this.spinner.getSlicesSpun()) > 0.25 && this.spinner.getCurrentColor() == this.spinner.getTargetColor();
    }

    @Override
    public void end(final boolean interrupted) {
        this.spinner.stop();
    }
}