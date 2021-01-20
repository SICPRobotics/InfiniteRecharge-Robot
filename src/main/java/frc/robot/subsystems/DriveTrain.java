package frc.robot.subsystems;

import java.util.Arrays;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.SubsystemBaseWrapper;

/**
 * the DriveTrain, aka the thing that moves the robot
 */
public final class DriveTrain extends SubsystemBaseWrapper {
    private final DifferentialDrive robotDrive;
    private final DifferentialDriveOdometry odometry; 
    private final Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0); //SPI.Port.kMXP ?
    private final WPI_TalonSRX frontRight = new WPI_TalonSRX(Constants.DriveTrain.FRONT_RIGHT_MOTOR_ID);
    private final WPI_TalonSRX rearRight = new WPI_TalonSRX(Constants.DriveTrain.REAR_RIGHT_MOTOR_ID);
    private final WPI_TalonSRX frontLeft = new WPI_TalonSRX(Constants.DriveTrain.FRONT_LEFT_MOTOR_ID);
    private final WPI_TalonSRX rearLeft = new WPI_TalonSRX(Constants.DriveTrain.REAR_LEFT_MOTOR_ID);

    public DriveTrain() {
        super();
        // Motors
        gyro.calibrate();
        frontRight.configFactoryDefault();
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
        frontRight.setSelectedSensorPosition(0);
        rearRight.configFactoryDefault();
        SpeedControllerGroup right = new SpeedControllerGroup(frontRight, rearRight);
        frontLeft.configFactoryDefault();
        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
        frontLeft.setSelectedSensorPosition(0);
        rearLeft.configFactoryDefault();
        SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, rearLeft);
        this.robotDrive = new DifferentialDrive(left, right);
        odometry = new DifferentialDriveOdometry(new Rotation2d(Math.toRadians(gyro.getAngle())), new Pose2d(0, 0, new Rotation2d()));
        reset();
    }
    //Mostly taken from last year's robot
    /**
     * The method to drive the robot.
     * @param moveValue the amount that the robot should move (Y axis, Joystick rawAxis 1)
     * @param rotateValue the amount that the robot should rotate (X axis, Joystick rawAxis 0)
     * @param scaleValue the amount that everything should be scaled by (usually given by the
     * little flap thing on the bottom of the joystick, Joystick rawAxis 3)
     */
    public void cheesyDrive(final double moveValue, final double rotateValue, final double adjustValue) {
        final double actualAdjustValue = ((-adjustValue + 1) / 2);
        final double movevalue = Math.abs(moveValue) < Constants.CheesyDrive.Y_AXIS_DEADZONE_RANGE
                ? 0
                : moveValue * actualAdjustValue;

        //Deadzone on x axis only if y value is small
        final double turnvalue = Math.abs(rotateValue) < Constants.CheesyDrive.X_AXIS_DEADZONE_RANGE
                && Math.abs(moveValue) < Constants.CheesyDrive.X_AXIS_DEADZONE_Y_MIN
                ? 0
                : rotateValue * actualAdjustValue;

        this.robotDrive.arcadeDrive(movevalue, turnvalue, true);
        //this.robotDrive.tankDrive((moveValue + rotateValue) * adjustValue, (moveValue - rotateValue) * adjustValue);
    }
    
    public void periodic() {
        updatePose();
        SmartDashboard.putNumber("TalonSRX 0 (front right) Temperature", frontRight.getTemperature());
        SmartDashboard.putNumber("TalonSRX 1 (rear right) Temperature", rearRight.getTemperature());
        SmartDashboard.putNumber("TalonSRX 2 (rear left) Temperature", rearLeft.getTemperature());
        SmartDashboard.putNumber("TalonSRX 3 (front left) Temperature", frontLeft.getTemperature());
        SmartDashboard.putNumber("Front Right Motor Position", ((double)(frontRight.getSelectedSensorPosition()) / Constants.DriveTrain.COUNTS_PER_ROTAION) * Constants.DriveTrain.WHEEL_CIRCUMFRANCE); // 4096 per rotation 8pi circumfrance
        SmartDashboard.putNumber("Front Left Motor Position", ((double)(frontLeft.getSelectedSensorPosition()) / Constants.DriveTrain.COUNTS_PER_ROTAION) * Constants.DriveTrain.WHEEL_CIRCUMFRANCE);
        SmartDashboard.putNumber("Front Right Motor Velocity", frontRight.getSelectedSensorVelocity());
        SmartDashboard.putNumber("Front Left Motor Velocity", frontLeft.getSelectedSensorVelocity());
        //SmartDashboard.putNumberArray("test Array", new double[2]);
        System.out.println(odometry.getPoseMeters().toString());
        //System.out.println(getRadians());
        SmartDashboard.putString("Pose2d", odometry.getPoseMeters().toString());
    }
    public double getRightDistanceMeters(){
        return ((double)(frontRight.getSelectedSensorPosition()) / Constants.DriveTrain.COUNTS_PER_ROTAION) * Constants.DriveTrain.WHEEL_CIRCUMFRANCE;
    }
    public double getLeftDistanceMeters(){
        return ((double)(frontLeft.getSelectedSensorPosition()) / Constants.DriveTrain.COUNTS_PER_ROTAION) * Constants.DriveTrain.WHEEL_CIRCUMFRANCE;
    }
    public double getRadians(){
        return Math.toRadians(-gyro.getAngle());
    }
    private void updatePose(){
        odometry.update(new Rotation2d(getRadians()), getLeftDistanceMeters(), getRightDistanceMeters());
    }
    public void reset(){
        frontLeft.setSelectedSensorPosition(0);
        frontRight.setSelectedSensorPosition(0);
        odometry.resetPosition(new Pose2d(new Translation2d(0,0), new Rotation2d(getRadians())), new Rotation2d(getRadians()));
    }
}