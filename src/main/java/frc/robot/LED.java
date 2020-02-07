package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

public final class LED {
    private AddressableLED ledStrip;
    private AddressableLEDBuffer ledBuffer;
    public LED() {
        super();
        ledStrip = new AddressableLED(0);
        ledBuffer = new AddressableLEDBuffer(120);
        ledStrip.setLength(ledBuffer.getLength());
        ledStrip.setData(ledBuffer);
        ledStrip.start();
    }

    public void allainceColor() {
        if (DriverStation.getInstance().getAlliance() == Alliance.Blue) {
            for (int i = 0; i < ledBuffer.getLength(); i++) {
                ledBuffer.setRGB(i, 0, 0, 255);
            }
        }
        else if (DriverStation.getInstance().getAlliance() == Alliance.Red) {
            for (int i = 0; i < ledBuffer.getLength(); i++) {
                ledBuffer.setRGB(i, 255, 0, 0);
            }
        }
        else {
            for (int i = 0; i < ledBuffer.getLength(); i++) {
                ledBuffer.setRGB(i, 255, 0, 255);
            }
        }   
    }
}