/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class Encoders {
        public static final int ONE_ENCODER_REVOLUTION = 4096;
    }
    public static final class CheesyDrive {
        public static final double X_AXIS_DEADZONE_RANGE = 0.005;
        public static final double Y_AXIS_DEADZONE_RANGE = 0.005;
        public static final double X_AXIS_DEADZONE_Y_MIN = 0.1;
    }
    public static final class ColorWheel {
        public static final int MOTOR_ID = 4;
        public static final int DOUBLE_SOLENOID_FORWARD_ID = 0;
        public static final int DOUBLE_SOLENOID_REVERSE_ID = 1;

        public static final int SHAFT_REVOLUTIONS_PER_GEARED_MOTOR_REVOLUTION = 50;
        public static final int MIN_SPINS = 3 * 8;
        public static final int MAX_SPINS = 5 * 8;
        public static final int SPINS_TARGET = MIN_SPINS + 6;
        public static final int FIELD_SENSOR_OFFSET = 2;
        
        public static final double SPEED = 0.5;
        public static final double MANUAL_SPEED = 0.5;
    }
}
