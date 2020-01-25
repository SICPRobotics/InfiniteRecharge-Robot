/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RangeFinder;

public class AutonomusCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final DriveTrain drive;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public AutonomusCommand(DriveTrain subsystem) {
    drive = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override 
  public void initialize() {
    
}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Timer timer = new Timer();

    // super boring timing auton
    timer.start();
    if(timer.get() < 3.0)
    {
        drive.cheesyDrive(1.0, 0.0);
    }
    else
    {
        drive.cheesyDrive(0.2, 0.0);
    }
    timer.reset();

    // way cooler sonar/Ultrasound? auton

    
    RangeFinder ultrasonic = new RangeFinder();

    if(ultrasonic.getCmDistance() < 4)
    {
      drive.cheesyDrive(1.0, 0.0);
    }
    else
    {
      drive.cheesyDrive(0.0, 0.0);
    }
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
