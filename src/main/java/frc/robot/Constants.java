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
    public static final class CheesyDrive {
        public static final double X_AXIS_DEADZONE_RANGE = 0.005;
        public static final double Y_AXIS_DEADZONE_RANGE = 0.005;
        public static final double X_AXIS_DEADZONE_Y_MIN = 0.1;
    }

    public final class DriveTrain {
        public static final int FRONT_RIGHT_MOTOR_ID = 0;
        public static final int FRONT_LEFT_MOTOR_ID = 3;
        public static final int REAR_RIGHT_MOTOR_ID = 1;
        public static final int REAR_LEFT_MOTOR_ID = 2;
    }

    public static final class Joystick {
        public static final int X_AXIS = 0;
        public static final int Y_AXIS = 1;
        public static final int Z_AXIS = 2;
        public static final int ADJUST_AXIS = 3;
    }
}