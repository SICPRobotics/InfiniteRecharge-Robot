/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RangeFinder;

public class AutonomusCommand extends CommandBase {
  private final DriveTrain drive;
  private Timer timer = new Timer();
  private RangeFinder ultrasonic = new RangeFinder();
  public AutonomusCommand(DriveTrain subsystem) {
    drive = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override 
  public void initialize() {
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if(timer.get() < 0.5)
    // {
    //     drive.cheesyDrive(0.7, 0.0);
    // }
    // else
    // {
    //     drive.cheesyDrive(0.5, 0.0);
    // }
    

    // way cooler sonar/Ultrasound? auton
   

    if(ultrasonic.getCmDistance() < 40)
    {
      drive.cheesyDrive(0.0, 0.0);
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
    return false;//timer.get() > 5.0;
  }
}
