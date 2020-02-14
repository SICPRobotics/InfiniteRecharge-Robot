package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.SubsystemBaseWrapper;

/**
 * the DriveTrain, aka the thing that moves the robot
 */
public final class DriveTrain extends SubsystemBaseWrapper {
    private final TalonSRX frontRight;
    private final TalonSRX frontLeft;

    /* SEE : 
https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/VelocityClosedLoop_ArbFeedForward/src/main/java/frc/robot/Robot.java
    */

    public DriveTrain() {
        super();
        // Motors
        frontRight = new WPI_TalonSRX(0);
        TalonSRX rearRight = new WPI_TalonSRX(1);

        frontLeft = new WPI_TalonSRX(3);
        TalonSRX rearLeft = new WPI_TalonSRX(2);

        //Disable and wipe them
        frontRight.set(ControlMode.PercentOutput, 0);
        rearRight.set(ControlMode.PercentOutput, 0);
        frontLeft.set(ControlMode.PercentOutput, 0);
        rearLeft.set(ControlMode.PercentOutput, 0);

        frontRight.configFactoryDefault();
        rearRight.configFactoryDefault();
        frontLeft.configFactoryDefault();
        rearLeft.configFactoryDefault();

        frontRight.setNeutralMode(NeutralMode.Brake);
        frontLeft.setNeutralMode(NeutralMode.Brake);

        // Followers (back motors)
        rearRight.follow(frontRight);
        rearLeft.follow(frontLeft);

        // Masters (front motors)
        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
        frontRight.configRemoteFeedbackFilter(frontLeft.getDeviceID(), RemoteSensorSource.TalonSRX_SelectedSensor, 0, 30);
    }

    //Mostly taken from last year's robot
    /**
     * The method to drive the robot.
     * @param moveValue the amount that the robot should move (Y axis, Joystick rawAxis 1)
     * @param rotateValue the amount that the robot should rotate (X axis, Joystick rawAxis 0)
     * @param scaleValue the amount that everything should be scaled by (usually given by the
     * little flap thing on the bottom of the joystick, Joystick rawAxis 3)
     */
    public void cheesyDrive(final double moveValue, final double rotateValue) {
        
    }
}