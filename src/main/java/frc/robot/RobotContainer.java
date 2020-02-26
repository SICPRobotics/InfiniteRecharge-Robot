/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.DriveWithoutJoystickInverted;
import frc.robot.commands.PullPasta;
import frc.robot.commands.SetMotorContinuous;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.PastaPuller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.controllers.OperatorController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.controllers.OperatorController;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.ExtendPiston;
import frc.robot.controllers.OperatorController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gate;
import frc.robot.commands.SetLightsToColor;
import frc.robot.subsystems.Lights;

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
  private final PastaPuller pastaPuller;
  private final Gate gate;

  private final Lights lights;
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    driveTrain = new DriveTrain();
    driveTrain.setDefaultCommand(new DriveWithJoystick(driveTrain, this::getJoystickY, this::getJoystickX));
    thumbButton = new JoystickButton(joystick, 2);
    pastaPuller = new PastaPuller();
    gate = new Gate();

    lights = new Lights();
    //Sets lights to the alliance's color
    lights.setDefaultCommand(new SetLightsToColor(lights, lights.getColorForAlliance(DriverStation.getInstance().getAlliance())).perpetually());
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
    thumbButton.toggleWhenActive(new DriveWithoutJoystickInverted(driveTrain, this::getJoystickY, this::getJoystickX));
    //PASTA PULLER
    //new Trigger(() -> operatorController.triggers.left.get() > 0.1).whileActiveContinuous(new PullPasta(pastaPuller));
    pastaPuller.setDefaultCommand(new SetMotorContinuous(pastaPuller, operatorController.sticks.right::getY));
    //GATE
    //operatorController.buttons.dPad.down.toggleWhenPressed(new FunctionalCommand(gate::extend, () -> { }, b -> gate.retract(), () -> false, gate));
    new Trigger(() -> operatorController.triggers.left.get() > 0.1).toggleWhenActive(new ExtendPiston(gate));
    new Trigger(gate::isUp).whileActiveContinuous(new SetLightsToColor(lights, Lights.LightsColor.ORANGE).perpetually());
  }

  public double getJoystickX() {
    return this.joystick.getRawAxis(Constants.Joystick.X_AXIS);
  }

  public double getJoystickY() {
    return this.joystick.getRawAxis(Constants.Joystick.Y_AXIS);
  }
  public double getJoystickZ() {
    return this.joystick.getRawAxis(2);
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
