package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
<<<<<<< HEAD:src/main/java/frc/robot/LED.java
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public final class LED {
    private final Spark led;

=======
import frc.robot.SubsystemBaseWrapper;

public final class LED extends SubsystemBaseWrapper {
    private AddressableLED ledStrip;
    private AddressableLEDBuffer ledBuffer;
>>>>>>> 85ad9180a8da40065fad1ed745c722dc834bb2ad:src/main/java/frc/robot/subsystems/LED.java
    public LED() {
        super();
        led = new Spark(0);
        SmartDashboard.putNumber("led", 0.0);
        
    }

    public void allainceColor() {
        if (DriverStation.getInstance().getAlliance() == Alliance.Blue) {
<<<<<<< HEAD:src/main/java/frc/robot/LED.java
            led.set(-0.41);
        }
        else if (DriverStation.getInstance().getAlliance() == Alliance.Red) {
            led.set(-0.39);
        }
        else {
           led.set(0.0);
        }   
    }
    public void smartDashboard() {
        led.set(SmartDashboard.getNumber("led", 0));
    }
    public void ramdom() {
        double neagtive;
            if (Math.random() < 0.5) {
                neagtive = -1;
            } else {
                neagtive = 1;
            }
        led.set(Math.random() * neagtive);
    }
    public void joystickLED(final Joystick j) {
        if (j.getRawAxis(3) < Math.abs(0.70)) {
           led.set(j.getRawAxis(3)); 
        }
        else {
            led.set(0);
        }
        
=======
            for (int i = 0; i < ledBuffer.getLength(); i++) {
                ledBuffer.setRGB(i, 0, 0, 255);
            }
        } else if (DriverStation.getInstance().getAlliance() == Alliance.Red) {
            for (int i = 0; i < ledBuffer.getLength(); i++) {
                ledBuffer.setRGB(i, 255, 0, 0);
            }
        } else {
            for (int i = 0; i < ledBuffer.getLength(); i++) {
                ledBuffer.setRGB(i, 255, 0, 255);
            }
        }   
    }
    public void blue() {
        for (int i = 0; i < ledBuffer.getLength(); i++) {
           ledBuffer.setRGB(i, 0, 0, 255); 
        }   
>>>>>>> 85ad9180a8da40065fad1ed745c722dc834bb2ad:src/main/java/frc/robot/subsystems/LED.java
    }
}