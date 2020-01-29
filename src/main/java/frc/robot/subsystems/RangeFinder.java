/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RangeFinder extends SubsystemBase {
    
    AnalogInput ultrasonicInput = new AnalogInput(0);
   
    public RangeFinder() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

 

  public double getCmDistance()
  {
      
      return ultrasonicInput.getVoltage() / Constants.VOLT_TO_CM;
  }


}
