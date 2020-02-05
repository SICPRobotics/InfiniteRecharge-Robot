package frc.robot.controllers;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.buttons.DPadButton;
import frc.robot.buttons.DPadButton.Direction;

public class OperatorController {
    public final Button A         ;
    public final Button B         ;
    public final Button X         ;
    public final Button Y         ;
    public final Button LB        ;
    public final Button RB        ;
    public final Button back      ;
    public final Button start     ;
    public final Button leftStickPress ;
    public final Button rightStickPress;
    public final Button dPadLeft;
    public final Button dPadRight;
    public final Button dPadDown;
    public final Button dPadUp;
    //XboxTrig leftTrig = new XboxTrig(true);
    //XboxTrig rightTrig = new XboxTrig(false);

    private final XboxController xbox;

    public OperatorController(int port) {
        xbox = new XboxController(port);

        A               = new JoystickButton(xbox, 1); 
        B               = new JoystickButton(xbox, 2); 
        X               = new JoystickButton(xbox, 3);
        Y               = new JoystickButton(xbox, 4); 
        LB              = new JoystickButton(xbox, 5); 
        RB              = new JoystickButton(xbox, 6); 
        back            = new JoystickButton(xbox, 7); 
        start           = new JoystickButton(xbox, 8); 
        leftStickPress  = new JoystickButton(xbox, 9); 
        rightStickPress = new JoystickButton(xbox, 10);

        dPadLeft  = new DPadButton(xbox, Direction.LEFT );
        dPadRight = new DPadButton(xbox, Direction.RIGHT);
        dPadDown  = new DPadButton(xbox, Direction.DOWN );
        dPadUp    = new DPadButton(xbox, Direction.UP   );
    }

    public double getRightTriggerAxis() {
        return xbox.getTriggerAxis(Hand.kRight);
    }

    public double getLeftTriggerAxis() {
        return xbox.getTriggerAxis(Hand.kLeft);
    }

    public void rumbleRight(double value) {
        xbox.setRumble(RumbleType.kRightRumble, value);
    }

    public void rumbleLeft(double value) {
        xbox.setRumble(RumbleType.kLeftRumble, value);
    }
}