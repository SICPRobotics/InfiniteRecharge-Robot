package frc.robot.subsystems;

import java.util.List;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.Constants;
import frc.robot.SubsystemBaseWrapper;

public final class TrajectoryGeneration extends SubsystemBaseWrapper {
    private TrajectoryConfig trajectoryConfig;
    private Pose2d startPoint,endPoint;
    private List<Translation2d> wayPoints;
    private Trajectory trajectory;
    public TrajectoryGeneration(Pose2d startPoint, Pose2d endPoint, List<Translation2d> wayPoints){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.wayPoints = wayPoints;
        trajectoryConfig = new TrajectoryConfig(1, 1); //max v and a 4m/s and 4m/s^2 respectivly dosen't really matter right now but can fine tune it later
        trajectoryConfig.addConstraint(
            new DifferentialDriveVoltageConstraint(
                new SimpleMotorFeedforward(
                    Constants.VoltageConstants.kS,Constants.VoltageConstants.kV,Constants.VoltageConstants.kA),
                    DriveTrain.kinematics, 10));
        trajectoryConfig.setKinematics(DriveTrain.kinematics);
        trajectory = TrajectoryGenerator.generateTrajectory(startPoint, wayPoints, endPoint, trajectoryConfig);
    }
    
    public Trajectory getTrajectory() {
        return trajectory;
    }
}