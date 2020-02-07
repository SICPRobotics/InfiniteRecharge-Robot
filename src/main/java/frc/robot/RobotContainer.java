/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.SetMotorContinuous;
import frc.robot.commands.ExtendPiston;
import frc.robot.commands.NudgeMotor;
import frc.robot.commands.color_wheel.SpinNumberOfTimes;
import frc.robot.commands.color_wheel.SpinToColor;
import frc.robot.subsystems.ColorWheelSpinner;
import frc.robot.controllers.OperatorController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.GroundIntake;
import frc.robot.commands.ExtendHangerArm;
import frc.robot.commands.PullHangerUp;
import frc.robot.controllers.OperatorController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hanger;

/** 
 *  This class is where the bulk of the robot should be de
 * lared.  Since Command-based is a "declarative" paradigm, very lit
 * le robot logic sh ld actually be handled in the {@lin periodic methods (othe
 *  than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public final class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Joystick joystick = new Joystick(0);
  private final OperatorController operatorController = new OperatorController(2);
  private final DriveTrain driveTrain;
  private final GroundIntake groundIntake;
  private final ColorWheelSpinner colorWheelSpinner;

  private final Hanger hanger;
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    driveTrain = new DriveTrain();
    driveTrain.setDefaultCommand(new DriveWithJoystick(driveTrain, this::getJoystickY, this::getJoystickX));
    groundIntake = new GroundIntake();
    colorWheelSpinner = new ColorWheelSpinner();
    hanger = new Hanger();
    
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //GROUND INTAKE
    //new Trigger(() -> operatorController.triggers.right.get() > 0.1).whileActiveContinuous(new Toggle(groundIntake));
    groundIntake.setDefaultCommand(new SetMotorContinuous(groundIntake, operatorController.sticks.left::getY));
    //COLOR WHEEL
    //Rotate to color / rotate a number of times
    operatorController.buttons.RB.whenPressed(new SpinNumberOfTimes(colorWheelSpinner));
    operatorController.buttons.LB.whenPressed(new SpinToColor(colorWheelSpinner));
    
    //Manual left/right of color wheel
    operatorController.buttons.dPad.left
        .whileActiveContinuous(new NudgeMotor(colorWheelSpinner, -1 * Constants.ColorWheel.MANUAL_SPEED).perpetually());
    operatorController.buttons.dPad.right
        .whileActiveContinuous(new NudgeMotor(colorWheelSpinner, Constants.ColorWheel.MANUAL_SPEED).perpetually());

    //Extend up/down (toggle color wheel position)
    operatorController.buttons.dPad.up.whenPressed(new ExtendPiston(colorWheelSpinner));

    //HANGER
    operatorController.buttons.Y.toggleWhenPressed(new PullHangerUp(hanger));
    operatorController.buttons.A.toggleWhenPressed(new ExtendHangerArm(hanger));
    
  }

  public double getJoystickX() {
    return this.joystick.getRawAxis(0);
  }

  public double getJoystickY() {
    return this.joystick.getRawAxis(1);
  }

  /* *
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   *
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new AutonomusCommand(driveTrain);
  }
  */
}