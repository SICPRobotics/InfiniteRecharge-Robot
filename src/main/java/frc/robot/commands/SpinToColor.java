package frc.robot.commands;

import frc.robot.game_elements.ColorWheel;
import frc.robot.game_elements.ColorWheelColor;
import frc.robot.subsystems.ColorWheelSpinner;
import frc.robot.subsystems.ExampleSubsystem;

import java.util.Arrays;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class SpinToColor extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ColorWheelSpinner spinner;

  public SpinToColor(ColorWheelSpinner spinner) {
    this.spinner = spinner;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    spinner.set(spinner.getSlicesTo(spinner.getTargetColor()) * 0.1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    spinner.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return spinner.getTargetColor() != spinner.getCurrentColor();
  }
}
