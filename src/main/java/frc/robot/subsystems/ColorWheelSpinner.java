package frc.robot.subsystems;

import java.util.Arrays;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.game_elements.ColorWheel;
import frc.robot.game_elements.ColorWheelColor;

public class ColorWheelSpinner extends SubsystemBase {
    VictorSP spinnerMotor = new VictorSP(4);
    private final ColorMatch colorMatcher = new ColorMatch();
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    
    public ColorWheelSpinner() {
        Arrays.stream(ColorWheelColor.values()).forEach(colorWheelColor -> {
            colorMatcher.addColorMatch(colorWheelColor.targetColor);
        });
    }

    /**
     * Gets the number of slices between the current color and the closest slice of the target color.
     * Returns a negative number if the closest slice is behind.
     */
    public int getSlicesTo(ColorWheelColor targetColor) {
        return ColorWheelColor.distanceTo(getCurrentColor(), targetColor);
    }

    /**
     * Gets the color that FMS wants us to spin to.
     */
    public ColorWheelColor getTargetColor() {
        String color = DriverStation.getInstance().getGameSpecificMessage();
        ColorWheelColor nextColor = Arrays.stream(ColorWheelColor.values()).filter(c -> c.string.equals(color)).toArray(ColorWheelColor[]::new)[0];
        return nextColor;
    }

    /**
     * Gets the color that the color wheel is on right now.
     * @return the color that the color wheel is on right now.
     */
    public ColorWheelColor getCurrentColor() {
        Color detectedColor = colorSensor.getColor();
        ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
        return Arrays.stream(ColorWheelColor.values()).filter(colorWheelColor -> colorWheelColor.targetColor == match.color).toArray(ColorWheelColor[]::new)[0];
    }

    public void set(double val) {
        spinnerMotor.set(val);
    }


}
