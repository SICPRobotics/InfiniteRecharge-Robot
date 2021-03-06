/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.controllers.OperatorController;
import frc.robot.subsystems.DriveTrain;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public final class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Joystick joystick = new Joystick(0);
  private final OperatorController operatorController = new OperatorController(2);
  private final DriveTrain driveTrain;
  private final JoystickButton thumbButton;
  private final CheesyDriveGetters cheesyDriveGetters;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    thumbButton = new JoystickButton(joystick, 2);
    cheesyDriveGetters = new CheesyDriveGetters(this::getJoystickY, this::getJoystickX, this::getJoystickAdjust, 
        /* Dummy command to make a toggle for the invert */
        () -> thumbButton.toggleWhenPressed(new RunCommand(() -> {})).get());

    driveTrain = new DriveTrain();
    driveTrain.setDefaultCommand(new DriveWithJoystick(driveTrain, cheesyDriveGetters));
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

  }

  public double getJoystickX() {
    return this.joystick.getRawAxis(Constants.Joystick.X_AXIS);
  }

  public double getJoystickY() {
    return this.joystick.getRawAxis(Constants.Joystick.Y_AXIS);
  }

  public double getJoystickAdjust() {
    return this.joystick.getRawAxis(Constants.Joystick.ADJUST_AXIS);
  }

  /* *
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   *
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoCommand;
  }
  */
}
