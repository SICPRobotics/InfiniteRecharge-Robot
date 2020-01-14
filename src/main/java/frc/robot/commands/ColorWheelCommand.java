package frc.robot.commands;

import frc.robot.game_elements.ColorWheel;
import frc.robot.game_elements.ColorWheelColor;
import frc.robot.subsystems.ColorWheelSpinner;
import frc.robot.subsystems.ExampleSubsystem;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ColorWheelCom extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ColorWheelSpinner subsystem;

  ColorWheelColor targetColor;
  targetColor = subsystem.toColor();
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
   



}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    ColorWheel colorwheel = new ColorWheel();
    Timer spinnerTimer = new Timer();
    int directionOfSpin = 1;
   
    if(colorwheel.distanceTo(, targetColor) < 0)
    {
        directionOfSpin = -1;
    }
    else if(colorwheel.distanceTo(, targetColor) > 0)
    {
        directionOfSpin = 1;
    }
    else 
    {
        directionOfSpin = 0;
    }
    spinnerTimer.start();
    if(spinnerTimer.get() < 0.2)
    {
        spinnerMotor.set(0.75 * directionOfSpin);
    }
    spinnerTimer.reset();

    







  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
