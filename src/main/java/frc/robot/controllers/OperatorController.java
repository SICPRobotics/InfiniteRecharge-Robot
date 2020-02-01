package frc.robot.controllers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OperatorController {
    public final DPad dPad;
    public final Buttons buttons;
    private final XboxController controller;

    public OperatorController(int port) {
        controller = new XboxController(port);
        
        buttons = new Buttons(controller);
        dPad = new DPad(controller);
    }

    public double getRightTriggerAxis() {
        return controller.getTriggerAxis(Hand.kRight);
    }

    public double getLeftTriggerAxis() {
        return controller.getTriggerAxis(Hand.kLeft);
    }

    public void rumbleRight(double value) {
        controller.setRumble(RumbleType.kRightRumble, value);
    }

    public void rumbleLeft(double value) {
        controller.setRumble(RumbleType.kLeftRumble, value);
    }
}