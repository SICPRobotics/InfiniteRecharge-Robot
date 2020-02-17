package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.SubsystemBaseWrapper;

/**
 * the DriveTrain, aka the thing that moves the robot
 */
public final class DriveTrain extends SubsystemBaseWrapper {
    private final DifferentialDrive robotDrive;
    private final ADXRS450_Gyro gyro;

    public DriveTrain() {
        super();
        // Motors
        WPI_TalonSRX frontRight = new WPI_TalonSRX(Constants.Motors.FRONT_RIGHT_TALON);
        WPI_TalonSRX rearRight = new WPI_TalonSRX(Constants.Motors.BACK_RIGHT_TALON);
        SpeedControllerGroup right = new SpeedControllerGroup(frontRight, rearRight);

        WPI_TalonSRX frontLeft = new WPI_TalonSRX(Constants.Motors.FRONT_LEFT_TALON);
        WPI_TalonSRX rearLeft = new WPI_TalonSRX(Constants.Motors.BACK_LEFT_TALON);
        SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, rearLeft);

        gyro = new ADXRS450_Gyro(SPI.Port.kMXP);
        gyro.calibrate();

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
    public void cheesyDrive(final double moveValue, final double rotateValue, final double adjustValue) {
        this.robotDrive.arcadeDrive(

            //Deadzone on y axis value
            Math.abs(moveValue) < Constants.CheesyDrive.Y_AXIS_DEADZONE_RANGE ? 0 : moveValue * ((-adjustValue + 1) /2),

            //Deadzone on x axis only if y value is small
            Math.abs(rotateValue) < Constants.CheesyDrive.X_AXIS_DEADZONE_RANGE
                    && Math.abs(moveValue) < Constants.CheesyDrive.X_AXIS_DEADZONE_Y_MIN
                ? 0 : rotateValue * ((-adjustValue + 1) / 2),

            //idk what this one means lol
            true
        );
        //this.robotDrive.tankDrive((moveValue + rotateValue) * adjustValue, (moveValue - rotateValue) * adjustValue);
    }

    @Override
    public void periodic() {
        System.out.println("Rotation: " + gyro.getAngle());
    }
}