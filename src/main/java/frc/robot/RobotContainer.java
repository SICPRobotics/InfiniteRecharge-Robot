/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.DriveWithJoystickForward;
import frc.robot.commands.SetMotorContinuous;
import frc.robot.commands.ExtendPiston;
import frc.robot.commands.Invert;
import frc.robot.commands.NudgeMotor;
import frc.robot.commands.color_wheel.SpinNumberOfTimes;
import frc.robot.commands.color_wheel.SpinToColor;
import frc.robot.subsystems.ColorWheelPiston;
import frc.robot.subsystems.ColorWheelSpinner;
import frc.robot.controllers.OperatorController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.GroundIntake;
import frc.robot.commands.ExtendHangerArm;
import frc.robot.commands.PullHangerUp;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.PastaPuller;
import frc.robot.subsystems.Gate;

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
  private final PastaPuller pastaPuller;
  private final ColorWheelPiston colorWheelPiston;
  private final Gate gate;
  private final JoystickButton thumbButton;
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    driveTrain = new DriveTrain();
    driveTrain.setDefaultCommand(new DriveWithJoystickForward(driveTrain, this::getJoystickY, this::getJoystickX, this::getJoystickSlider));
    groundIntake = new GroundIntake();
    colorWheelSpinner = new ColorWheelSpinner();
    hanger = new Hanger();
    pastaPuller = new PastaPuller();
    gate = new Gate();
    colorWheelPiston = new ColorWheelPiston();
    thumbButton = new JoystickButton(joystick, 2);
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
    thumbButton.whenPressed(new Invert(driveTrain));
    
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
    //operatorController.buttons.dPad.up.whenPressed(new ExtendPiston(colorWheelSpinner));
    operatorController.buttons.dPad.up.toggleWhenPressed(new ExtendPiston(colorWheelPiston));

    //HANGER
    operatorController.buttons.Y.toggleWhenPressed(new PullHangerUp(hanger));
    operatorController.buttons.A.toggleWhenPressed(new ExtendHangerArm(hanger));
    
    //PASTA PULLER
    //new Trigger(() -> operatorController.triggers.left.get() > 0.1).whileActiveContinuous(new PullPasta(pastaPuller));
    pastaPuller.setDefaultCommand(new SetMotorContinuous(pastaPuller, operatorController.sticks.right::getY));
    
    //GATE
    //operatorController.buttons.dPad.down.toggleWhenPressed(new FunctionalCommand(gate::extend, () -> { }, b -> gate.retract(), () -> false, gate));
    new Trigger(() -> operatorController.triggers.left.get() > 0.1).toggleWhenActive(new ExtendPiston(gate));
  }

  public double getJoystickX() {
    return this.joystick.getRawAxis(Constants.Joystick.X_AXIS);
  }

  public double getJoystickY() {
    return this.joystick.getRawAxis(Constants.Joystick.Y_AXIS);
  }
  public double getJoystickSlider(){
    return this.joystick.getRawAxis(Constants.Joystick.SLIDER);
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