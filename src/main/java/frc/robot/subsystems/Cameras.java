package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.SubsystemBaseWrapper;

public class Cameras extends SubsystemBaseWrapper {
    public Cameras() {
        UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture(0);
        UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture(1);
        cam0.setResolution(320, 320);
        cam1.setResolution(320, 320);
    }
}