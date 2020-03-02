package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.SubsystemBaseWrapper;

public class Cameras extends SubsystemBaseWrapper {
    public Cameras() {
        UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture(0);
        UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture(1);
        UsbCamera cam2 = CameraServer.getInstance().startAutomaticCapture(2);
        cam0.setResolution(320, 240);
        cam1.setResolution(320, 240);
        cam2.setResolution(320, 240);
        cam0.setFPS(15);
        cam1.setFPS(15);
        cam2.setFPS(15);
    }
}