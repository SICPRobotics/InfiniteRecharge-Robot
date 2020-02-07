
package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RangeFinder;

public class AutonomusCommand extends CommandBase {
  private final DriveTrain drive;
  private Timer timer = new Timer();
  private RangeFinder ultrasonic = new RangeFinder();
  public AutonomusCommand(final DriveTrain subsystem) {
    drive = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public final void initialize() {
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public final void execute() {
    if (ultrasonic.getCmDistance() < Constants.AUTON_STOPPING_DISTANCE) {
      drive.cheesyDrive(0.5, 0.0);
    }
    else {
      drive.cheesyDrive(0.0, 0.0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public final boolean isFinished() {
    return ultrasonic.getCmDistance() > Constants.AUTON_STOPPING_DISTANCE;
  }
}
