package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.SubsystemBaseWrapper;
import frc.robot.TalonEncoder;

/**
 * the DriveTrain, aka the thing that moves the robot
 */
public final class DriveTrain extends SubsystemBaseWrapper {
    private final DifferentialDrive robotDrive;
    private final DifferentialDriveOdometry odometry;
    private final ADXRS450_Gyro gyro;
    private final TalonEncoder rightEncoder;
    private final TalonEncoder leftEncoder;

    public DriveTrain() {
        super();
        // Motors
        WPI_TalonSRX frontRight = new WPI_TalonSRX(0);
        WPI_TalonSRX rearRight = new WPI_TalonSRX(1);
        SpeedControllerGroup right = new SpeedControllerGroup(frontRight, rearRight);
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        rightEncoder = new TalonEncoder(() -> frontRight.setSelectedSensorPosition(0), frontRight::getSelectedSensorPosition);

        WPI_TalonSRX frontLeft = new WPI_TalonSRX(3);
        WPI_TalonSRX rearLeft = new WPI_TalonSRX(2);
        SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, rearLeft);
        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        leftEncoder = new TalonEncoder(() -> frontLeft.setSelectedSensorPosition(0), frontLeft::getSelectedSensorPosition);

        this.robotDrive = new DifferentialDrive(left, right);

        //Gyroscope--this is only loosely affiliated with the drive train lol
        gyro = new ADXRS450_Gyro(SPI.Port.kMXP);

        //Odometry--basically finding what position the robot is at
        //SmartDashboard.putNumber("Robot start offset X", 0.0);
        //SmartDashboard.putNumber("Robot start offset Y", 0.0);
        //robot is 28 x 31 INCHES
        odometry = new DifferentialDriveOdometry(new Rotation2d());
        resetPosition();

        gyro.calibrate();
    }
    
    /**
     * Gets the start offset again from the smartDashboard
     * Resets encoders
     */
    public void resetPosition() {
        odometry.resetPosition(
            new Pose2d(
                SmartDashboard.getNumber("Robot start offset X", 0.0),
                SmartDashboard.getNumber("Robot start offset Y", 0.0),
                new Rotation2d()
            ),
            Rotation2d.fromDegrees(-gyro.getAngle())
        );
    }

    /**
     * Updates the robot's odometry (position-tracker)
     */
    @Override
    public void periodic() {
        updateOdometry();
    }

    public void updateOdometry() {
        //System.out.println("Gyro angle: " + gyro.getAngle() + "   rate:" + gyro.getRate());
        System.out.println("Position: " + odometry.getPoseMeters());
        //SmartDashboard.putData((Sendable) gyro);
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
        this.robotDrive.arcadeDrive(

            //Deadzone on y axis value
            Math.abs(moveValue) < Constants.CheesyDrive.Y_AXIS_DEADZONE_RANGE ? 0 : moveValue,

            //Deadzone on x axis only if y value is small
            Math.abs(rotateValue) < Constants.CheesyDrive.X_AXIS_DEADZONE_RANGE
                    && Math.abs(moveValue) < Constants.CheesyDrive.X_AXIS_DEADZONE_Y_MIN
                ? 0 : rotateValue,

            //decrease input sensitivity at low speeds--i don't know why we have this but we do
            true
        );
    }
}