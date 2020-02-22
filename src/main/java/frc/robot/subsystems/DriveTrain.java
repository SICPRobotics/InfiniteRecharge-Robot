package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.SubsystemBaseWrapper;

/**
 * the DriveTrain, aka the thing that moves the robot
 */
public final class DriveTrain extends SubsystemBaseWrapper {
    private final DifferentialDrive robotDrive;
    
    private final WPI_TalonSRX frontRight = new WPI_TalonSRX(Constants.DriveTrain.FRONT_RIGHT_MOTOR_ID);
    private final WPI_TalonSRX rearRight = new WPI_TalonSRX(Constants.DriveTrain.REAR_RIGHT_MOTOR_ID);
    private final WPI_TalonSRX frontLeft = new WPI_TalonSRX(Constants.DriveTrain.FRONT_LEFT_MOTOR_ID);
    private final WPI_TalonSRX rearLeft = new WPI_TalonSRX(Constants.DriveTrain.REAR_LEFT_MOTOR_ID);

    private final ADXRS450_Gyro gyro;

    public DriveTrain() {
        super();
        // Motors
        frontRight.configFactoryDefault();
        rearRight.configFactoryDefault();
        SpeedControllerGroup right = new SpeedControllerGroup(frontRight, rearRight);

        frontLeft.configFactoryDefault();
        rearLeft.configFactoryDefault();
        SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, rearLeft);

        this.robotDrive = new DifferentialDrive(left, right);

        //Gyros
        gyro = new ADXRS450_Gyro();
        gyro.calibrate();
    }

    //Mostly taken from last year's robot
    /**
     * The method to drive the robot.
     * @param moveValue the amount that the robot should move (Y axis, Joystick rawAxis 1)
     * @param rotateValue the amount that the robot should rotate (X axis, Joystick rawAxis 0)
     * @param scaleValue the amount that everything should be scaled by (usually given by the
     * little flap thing on the bottom of the joystick, Joystick rawAxis 3)
     */
    public void cheesyDrive(final double moveValue, final double rotateValue, final double scaleValue) {
        final double actualScaleValue = ((-scaleValue + 1) / 2);
        this.robotDrive.arcadeDrive(

            //Deadzone on y axis value
            Math.abs(moveValue) < Constants.CheesyDrive.Y_AXIS_DEADZONE_RANGE
                    ? 0
                    : moveValue * actualScaleValue,

            //Deadzone on x axis only if y value is small
            Math.abs(rotateValue) < Constants.CheesyDrive.X_AXIS_DEADZONE_RANGE
                    && Math.abs(moveValue) < Constants.CheesyDrive.X_AXIS_DEADZONE_Y_MIN
                    ? 0
                    : rotateValue * actualScaleValue,

            //idk what this one means lol
            true
        );
        //this.robotDrive.tankDrive((moveValue + rotateValue) * adjustValue, (moveValue - rotateValue) * adjustValue);
    }

    public void calibrateGyro() {
        gyro.calibrate();
    }

    public double getGyroRotation() {
        return gyro.getAngle();
    }

    public double getGyroVelocity() {
        return gyro.getRate();
    }
    
    public void periodic() {
        SmartDashboard.putNumber("TalonSRX 0 (front right) Temperature", frontRight.getTemperature());
        SmartDashboard.putNumber("TalonSRX 1 (rear right) Temperature", rearRight.getTemperature());
        SmartDashboard.putNumber("TalonSRX 2 (rear left) Temperature", rearLeft.getTemperature());
        SmartDashboard.putNumber("TalonSRX 3 (front left) Temperature", frontLeft.getTemperature());
        SmartDashboard.putNumber("Gyro Heading", gyro.getAngle());
        SmartDashboard.putNumber("Gyro Velocity", gyro.getRate());
    }
}
