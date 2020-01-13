package frc.robot.game_elements;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;

public enum ColorWheelColor {
    BLUE(0.143, 0.427, 0.429),
    YELLOW(0.361, 0.524, 0.113),
    RED(0.561, 0.232, 0.114),
    GREEN(0.197, 0.561, 0.240);

    public final Color targetColor;
    private ColorWheelColor(Color targetColor){
        this.targetColor = targetColor;
    }
    private ColorWheelColor(double red, double green, double blue) {
        this(ColorMatch.makeColor(red, green, blue));
    }
}