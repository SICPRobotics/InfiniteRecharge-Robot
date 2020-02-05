
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Compessor extends SubsystemBase
{
    Compressor compressor;
    
    public Compessor()
    {
        compressor = new Compressor(Constants.Compressor.COMPRESSOR_ID);
       
        compressor.setClosedLoopControl(true);
    }
}