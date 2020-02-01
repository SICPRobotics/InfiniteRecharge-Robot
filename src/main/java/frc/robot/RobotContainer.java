/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutonomusCommand;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.GroundIntakeCommand;
import frc.robot.commands.color_wheel.SpinNumberOfTimes;
import frc.robot.commands.color_wheel.SpinToColor;
import frc.robot.subsystems.ColorWheelSpinner;
import frc.robot.commands.PullPasta;
import frc.robot.commands.ExtendHangerArm;
import frc.robot.commands.GroundIntakeCommand;
import frc.robot.commands.PullHangerUp;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.GroundIntake;
import frc.robot.subsystems.RangeFinder;
import frc.robot.subsystems.PastaPuller;
import frc.robot.subsystems.Hanger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Joystick joystick = new Joystick(0);
  private final XboxController xboxController = new XboxController(1);
  private final DriveTrain driveTrain;
  private final GroundIntake groundIntake;
  private final ColorWheelSpinner colorWheelSpinner;
  private final PastaPuller pastaPuller;

  private final Hanger hanger;
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    driveTrain = new DriveTrain();
    driveTrain.setDefaultCommand(new DriveWithJoystick(driveTrain, this::getJoystickY, this::getJoystickX));
    groundIntake = new GroundIntake();
    colorWheelSpinner = new ColorWheelSpinner();
    pastaPuller = new PastaPuller();
    

    RangeFinder ultrasonic = new RangeFinder();
    SmartDashboard.putNumber("UltraSonic Distance", ultrasonic.getCmDistance());
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
    //new JoystickButton(this.joystick, 1).whileHeld(new GroundIntakeCommand(groundIntake));
    new JoystickButton(this.joystick, 2).toggleWhenPressed(new SpinToColor(colorWheelSpinner));
    new JoystickButton(this.joystick, 3).toggleWhenPressed(new SpinNumberOfTimes(colorWheelSpinner));
    new JoystickButton(this.joystick, 2).whileHeld(new PullPasta(pastaPuller));
    //new JoystickButton(this.joystick, 0).whenHeld(new GroundIntakeCommand(groundIntake));
    new JoystickButton(this.joystick, 2).whileHeld(new PullHangerUp(hanger));
    new JoystickButton(this.joystick,3).whenPressed(new ExtendHangerArm(hanger));
    //new JoystickButton(this.joystick, 1).whileHeld(new GroundIntakeCommand(groundIntake));
    groundIntake.setDefaultCommand(new GroundIntakeCommand(groundIntake, () -> xboxController.getTriggerAxis(Hand.kRight)));
  }

  public double getJoystickX() {
    return this.joystick.getRawAxis(0);
  }

  public double getJoystickY() {
    return this.joystick.getRawAxis(1);
  }

  /**
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