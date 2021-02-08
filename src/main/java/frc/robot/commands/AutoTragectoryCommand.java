
package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.PastaPuller;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gate;
import frc.robot.subsystems.HangerArm;
import frc.robot.subsystems.RangeFinder;

public class AutoTragectoryCommand extends CommandBase {
    private final DriveTrain drive;
    private Timer timer;
    private int counter = 0;
    private double delay;

    public AutoTragectoryCommand(final DriveTrain drive) {
    this.drive = drive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public final void initialize() {
    timer = new Timer();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public final void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    timer.reset();
    System.out.println("Auton End");
  }

  // Returns true when the command should end.
  @Override
  public final boolean isFinished() {
    return timer.get() > 15 ? true : false;
  }
}
