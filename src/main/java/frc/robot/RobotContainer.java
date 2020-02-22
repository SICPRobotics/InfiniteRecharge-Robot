/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.AutonomusCommand;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.SetMotorContinuous;
import frc.robot.commands.ExtendPiston;
import frc.robot.subsystems.Compessor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveWithoutJoystickInverted;
import frc.robot.controllers.OperatorController;
import frc.robot.subsystems.Cameras;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.GroundIntake;
import frc.robot.subsystems.PastaPuller;
import frc.robot.subsystems.Gate;
import frc.robot.commands.SetLightsToColor;
import frc.robot.subsystems.Lights;

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
  private final OperatorController operatorController = new OperatorController(1);
  private final DriveTrain driveTrain;
   private final GroundIntake groundIntake;
   private final Compessor compressor;
  // private final ColorWheelSpinner colorWheelSpinner;
  // private final Hanger hanger;
   private final PastaPuller pastaPuller;
  // private final ColorWheelPiston colorWheelPiston;
   private final Gate gate;
  private final JoystickButton thumbButton;
  private final Cameras cameras;

  private final Lights lights;
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    driveTrain = new DriveTrain();
    driveTrain.setDefaultCommand(new DriveWithJoystick(driveTrain, this::getJoystickY, this::getJoystickX, this::getJoystickS));
     groundIntake = new GroundIntake();
    // colorWheelSpinner = new ColorWheelSpinner();
    // hanger = new Hanger();
     pastaPuller = new PastaPuller();
    compressor = new Compessor();
    // colorWheelPiston = new ColorWheelPiston();
    thumbButton = new JoystickButton(joystick, 2);
    gate = new Gate();

    lights = new Lights();
    //Sets lights to the alliance's color
    lights.setDefaultCommand(new SetLightsToColor(lights, lights.getColorForAlliance(DriverStation.getInstance().getAlliance())).perpetually());
    cameras = new Cameras();
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
    //GROUND INTAKE
    //new Trigger(() -> operatorController.triggers.right.get() > 0.1).whileActiveContinuous(new Toggle(groundIntake));
    groundIntake.setDefaultCommand(new SetMotorContinuous(groundIntake, operatorController.sticks.left::getY));
    
    //COLOR WHEEL
    //Rotate to color / rotate a number of times
    //operatorController.buttons.RB.whenPressed(new SpinNumberOfTimes(colorWheelSpinner));
    //operatorController.buttons.LB.whenPressed(new SpinToColor(colorWheelSpinner));
    
    //Manual left/right of color wheel
    //operatorController.buttons.dPad.left
      //  .whileActiveContinuous(new NudgeMotor(colorWheelSpinner, -1 * Constants.ColorWheel.MANUAL_SPEED).perpetually());
    //operatorController.buttons.dPad.right
     //   .whileActiveContinuous(new NudgeMotor(colorWheelSpinner, Constants.ColorWheel.MANUAL_SPEED).perpetually());

    //Extend up/down (toggle color wheel position)
    //operatorController.buttons.dPad.up.whenPressed(new ExtendPiston(colorWheelSpinner));
    //operatorController.buttons.dPad.up.toggleWhenPressed(new ExtendPiston(colorWheelPiston));

    //HANGER
    //operatorController.buttons.Y.toggleWhenPressed(new PullHangerUp(hanger));
    //operatorController.buttons.A.toggleWhenPressed(new ExtendHangerArm(hanger));
    
    //PASTA PULLER
    // new Trigger(() -> operatorController.triggers.left.get() > 0.1).whileActiveContinuous(new PullPasta(pastaPuller));
    pastaPuller.setDefaultCommand(new SetMotorContinuous(pastaPuller, operatorController.sticks.right::getY));
    
    //GATE
    //operatorController.buttons.dPad.down.toggleWhenPressed(new FunctionalCommand(gate::extend, () -> { }, b -> gate.retract(), () -> false, gate));
    new Trigger(() -> operatorController.triggers.left.get() > 0.1).toggleWhenActive(new ExtendPiston(gate));
    thumbButton.toggleWhenActive(new DriveWithoutJoystickInverted(driveTrain, this::getJoystickY, this::getJoystickX, this::getJoystickS));
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
  
  public double getJoystickS() {
    return this.joystick.getRawAxis(Constants.Joystick.S_AXIS);
  }
  
  // * @return the command to run in autonomous
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new AutonomusCommand(driveTrain, gate, pastaPuller);
  }
  
}