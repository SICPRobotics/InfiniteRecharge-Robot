package frc.robot.subsystems;

import java.util.Arrays;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.game_elements.ColorWheel;
import frc.robot.game_elements.ColorWheelColor;

public class ColorWheelSpinner extends SubsystemBase {

    
    WPI_TalonSRX spinnerMotor = new WPI_TalonSRX(4);
    
    
    
    public void spinTo(ColorWheelColor fromColor, ColorWheelColor toColor) {
        
        ColorWheel colorwheel = new ColorWheel();
        Timer spinnerTimer = new Timer();
        int directionOfSpin = 1;
        if(colorwheel.distanceTo(fromColor, toColor) < 0)
        {
            directionOfSpin = -1;
        }
        else if(colorwheel.distanceTo(fromColor, toColor) > 0)
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

    public ColorWheelColor goToColor()
    {
        String color = DriverStation.getInstance().getGameSpecificMessage();
        ColorWheelColor nextColor = Arrays.stream(ColorWheelColor.values()).filter(c -> c.string.equals(color)).toArray(ColorWheelColor[]::new)[0];
        return nextColor;



    }

}
