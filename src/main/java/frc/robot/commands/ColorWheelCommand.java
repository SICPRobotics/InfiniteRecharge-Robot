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
public class ColorWheelCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ColorWheelSpinner subsystem;
  VictorSPX spinnerMotor = new VictorSPX(4);
  private final ColorMatch colorMatcher = new ColorMatch();


  
  
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  String colorString;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    Arrays.stream(ColorWheelColor.values()).forEach(colorWheelColor -> {
      colorMatcher.addColorMatch(colorWheelColor.targetColor);
    });

}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    ColorWheel colorwheel = new ColorWheel();
    Timer spinnerTimer = new Timer();
    int directionOfSpin = 1;
    ColorWheelColor placeHolderName;
    placeHolderName = subsystem.toColor();
    ColorWheelColor nextColor;

    
    /*if(colorwheel.distanceTo(nextColor, placeHolderName) < 0)
    {
        directionOfSpin = -1;
    }
    else if(colorwheel.distanceTo(nextColor, placeHolderName) > 0)
    {
        directionOfSpin = 1;
    }
    else 
    {
        directionOfSpin = 0;
    }*/

    if(spinnerTimer.get() < 0.2)
    {
        spinnerMotor.set(0.75 * directionOfSpin);
    }
  }

  public ColorWheelColor getCurrentColor() {
    Color detectedColor = colorSensor.getColor();
    String colorString;
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
    return Arrays.stream(ColorWheelColor.values()).filter(colorWheelColor -> colorWheelColor.targetColor == match.color).toArray(ColorWheelColor[]::new)[0];
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return 
  }
}
