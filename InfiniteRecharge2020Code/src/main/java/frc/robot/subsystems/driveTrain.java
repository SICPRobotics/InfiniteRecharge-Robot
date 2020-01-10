/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class driveTrain extends SubsystemBase {

    private DifferentialDrive robotBase;
    private Talon rightF, leftF, rightB, leftB;
    private SpeedControllerGroup right, left;
    private int moveValue;
    private int move

    public driveTrain() {
    rightF = new Talon(0);
    rightB = new Talon(1);    
    leftF = new Talon(2);
    leftB = new Talon(3);    
    right = new SpeedControllerGroup(rightF, rightB);
    left = new SpeedControllerGroup(leftF, leftB);
    robotBase = new DifferentialDrive(left, right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(Joystick joy)
  {
    moveValue = joy.getRawAxis(1);
    rotateValue = joy.getRawAxis(2);

    //Dead zone on y axis value
    if (Math.abs(moveValue) < .005)
      moveValue = 0;
    
    //Dead zone on x axis only if y value is small
    if (Math.abs(rotateValue) < .005 && Math.abs(moveValue) < .1)
      rotateValue = 0;
    
    
  
  
    robotBase.arcadeDrive(moveValue, rotateValue);
  }







}
