package frc.robot.subsystems;

import java.util.Arrays;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.game_elements.ColorWheel;
import frc.robot.game_elements.ColorWheelColor;

public class ColorWheelSpinner extends SubsystemBase {
    TalonSRX spinnerMotor = new TalonSRX(4);
    private final ColorMatch colorMatcher = new ColorMatch();
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    
    public ColorWheelSpinner() {
        Arrays.stream(ColorWheelColor.values()).forEach(colorWheelColor -> {
            colorMatcher.addColorMatch(colorWheelColor.targetColor);
        });

        spinnerMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
    }

    /**
     * Gets the number of slices between the current color and the closest slice of the target color.
     * Returns a negative number if the closest slice is down (aka behind).
     */
    public int getSlicesTo(ColorWheelColor targetColor) {
        return ColorWheel.slicesToClosest(getCurrentColor(), targetColor);
    }

    /**
     * Gets the number of slices up (aka in front) from the current color to the targetColor.
     * @return the number of slices up
     */
    public int getSlicesUpTo(ColorWheelColor targetColor) {
        return ColorWheel.slicesUp(getCurrentColor(), targetColor);
    }

    /**
     * Gets the number of slices down (aka behind) from the current color to the targetColor.
     * @return the number of slices down
     */
    public int getSlicesDownTo(ColorWheelColor targetColor) {
        return ColorWheel.slicesUp(getCurrentColor(), targetColor);
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
     * Gets the color that the field's sensor is on right now. Just treat this code like a black box pls
     * @return the color that the field's color sensor is on right now, based on our color sensor.
     */
    public ColorWheelColor getCurrentColor() {
        Color detectedColor = colorSensor.getColor();
        ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
        return ColorWheel.getRelativeColor(Arrays.stream(ColorWheelColor.values()).filter(colorWheelColor -> colorWheelColor.targetColor == match.color).toArray(ColorWheelColor[]::new)[0], 3);
    }

    public double getConfidence() {
        Color detectedColor = colorSensor.getColor();
        ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
        return match.confidence;
    }

    public void set(double val) {
        spinnerMotor.set(ControlMode.PercentOutput, 1);
    }

    public void stop() {
        spinnerMotor.set(ControlMode.PercentOutput, 0);
    }

    /**
     * Gets the distance the encoder has spun.
     */
    private double getEncoderDistance() {
        System.out.println(spinnerMotor.getSelectedSensorPosition() / 4096 / 50);
        //return this.encoder.getDistance();
        return spinnerMotor.getSelectedSensorPosition();
    }

    private double toSlices(double distanceInEncoderUnits) {
        //Arc length of control panel is 4pi inches and wheel circumference is also 4pi inches!
        return distanceInEncoderUnits / 4096 / 50;
    }

    /**
     * Gets the distance the color wheel has spun, in slices.
     */
    public double getSlicesSpun() {
        return toSlices(getEncoderDistance());
    }

    /**
     * Resets the distance count on the encoder.
     */
    public void resetDistance() {
        //encoder.reset();
        spinnerMotor.setSelectedSensorPosition(0);
    }

}
