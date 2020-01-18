package frc.robot.game_elements;

import java.util.ArrayList;
import java.util.Arrays;

import edu.wpi.first.wpilibj.util.Color;

public class ColorWheel {
    public static ArrayList<ColorWheelColor> order = new ArrayList<ColorWheelColor>(Arrays.asList(new ColorWheelColor[]{
        ColorWheelColor.BLUE,
        ColorWheelColor.YELLOW,
        ColorWheelColor.RED,
        ColorWheelColor.GREEN
    }));

    private static int distanceToWithoutWrapping(ColorWheelColor fromColor, ColorWheelColor toColor) {
        return ColorWheel.order.indexOf(toColor) - ColorWheel.order.indexOf(fromColor);
    }

    private static int distanceWrappedTo(ColorWheelColor fromColor, ColorWheelColor toColor) {
        return (ColorWheel.order.indexOf(toColor) - ColorWheel.order.size()) - ColorWheel.order.indexOf(toColor);
    }

    public static int distanceTo(ColorWheelColor fromColor, ColorWheelColor toColor) {
        return Math.abs(distanceToWithoutWrapping(fromColor, toColor)) < Math.abs(distanceWrappedTo(fromColor, toColor)) ? distanceToWithoutWrapping(fromColor, toColor) : distanceWrappedTo(fromColor, toColor);
    }
}