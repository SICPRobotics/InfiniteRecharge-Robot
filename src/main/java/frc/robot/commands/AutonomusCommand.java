
package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.PastaPuller;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gate;
import frc.robot.subsystems.RangeFinder;

public class AutonomusCommand extends CommandBase {
  private final DriveTrain drive;
  private Timer timer = new Timer();
  private RangeFinder ultrasonic = new RangeFinder();
  private Gate gate;
  private PastaPuller hopper;
  public AutonomusCommand(final DriveTrain subsystem, final Gate gate, final PastaPuller hopper) {
    drive = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    this.gate = gate;
    this.hopper = hopper;
    addRequirements(subsystem, gate, hopper);
  }

  // Called when the command is initially scheduled.
  @Override
  public final void initialize() {
    timer.start();
    gate.pistonReverse();
    hopper.setMotor(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public final void execute() {
    if (timer.get() < 1.0/*ultrasonic.getCmDistance() < Constants.UltraSonic.AUTON_STOPPING_DISTANCE*/) {
      drive.cheesyDrive(0.5, 0.0, 1);
    }
    else {
      drive.cheesyDrive(0.1, 0.0, 1);
      gate.pistonForward();
      hopper.setMotor(1);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    timer.reset();
    hopper.setMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public final boolean isFinished() {
    return false;
  }
}
