package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
    
    private final WPI_TalonSRX frontRight = new WPI_TalonSRX(0);
    private final WPI_TalonSRX rearRight = new WPI_TalonSRX(1);
    private final WPI_TalonSRX frontLeft = new WPI_TalonSRX(3);
    private final WPI_TalonSRX rearLeft = new WPI_TalonSRX(2);

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

            //idk what this one means lol
            true
        );
    }

    public void periodic() {
        SmartDashboard.putNumber("TalonSRX 0 (front right) Temperature", frontRight.getTemperature());
        SmartDashboard.putNumber("TalonSRX 1 (rear right) Temperature", rearRight.getTemperature());
        SmartDashboard.putNumber("TalonSRX 2 (rear left) Temperature", rearLeft.getTemperature());
        SmartDashboard.putNumber("TalonSRX 3 (front left) Temperature", frontLeft.getTemperature());
    }
}