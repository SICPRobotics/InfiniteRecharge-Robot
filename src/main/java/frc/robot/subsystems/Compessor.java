
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import frc.robot.Constants;
import frc.robot.SubsystemBaseWrapper;

public class Compessor extends SubsystemBaseWrapper {
    private final Compressor compressor;
    
    public Compessor() {
        compressor = new Compressor(Constants.Compressor.COMPRESSOR_ID);
       
        compressor.setClosedLoopControl(true);
    }
}