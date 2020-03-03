
package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.PastaPuller;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gate;
import frc.robot.subsystems.HangerArm;
import frc.robot.subsystems.RangeFinder;

public class AutonomusCommand extends CommandBase {
  private final DriveTrain drive;
  private Timer timer = new Timer();
 // private RangeFinder ultrasonic = new RangeFinder();
  private Gate gate;
  private PastaPuller hopper;
  private HangerArm hanger;
  public AutonomusCommand(final DriveTrain drive, final Gate gate, final PastaPuller hopper, final HangerArm hanger) {
    this.drive = drive;
    // Use addRequirements() here to declare subsystem dependencies.
    this.gate = gate;
    this.hopper = hopper;  
    this.hanger = hanger;
    addRequirements(drive, gate, hopper);
  }

  // Called when the command is initially scheduled.
  @Override
  public final void initialize() {
    timer.start();
    gate.pistonReverse();
    hopper.stopPulling();
    new Calibrate(hanger).schedule();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public final void execute() {
    System.out.println("Auton exct");
    if(SmartDashboard.getNumber("Auton Chooser", 0) == 0)
    {
     System.out.println("Auton go!!");
      if (timer.get() < 2.0/*ultrasonic.getCmDistance() < Constants.UltraSonic.AUTON_STOPPING_DISTANCE*/) {
      drive.cheesyDrive(-0.6, 0.0, -1);
      }
      else{
        drive.cheesyDrive(0, 0, 0);
      }
    }
    else if (SmartDashboard.getNumber("Auton Chooser", 0) == 1){
      if (timer.get() < 4.0/*ultrasonic.getCmDistance() < Constants.UltraSonic.AUTON_STOPPING_DISTANCE*/) {
        drive.cheesyDrive(-0.6, 0.0, -1);
      }
      else {
        drive.cheesyDrive(-0.4, 0.0, -1);
        gate.pistonForward();
        hopper.startPulling();
      }
    }
    else{

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    timer.reset();
    System.out.println("Auton End");
    hopper.setMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public final boolean isFinished() {
    return timer.get() > 15 ? true : false;
  }
}
