package frc.robot;

/**
 * Distance class for the DriveTrain.
 */
public class Distance {
    private final double revolutions;
    public static final double wheelCircumference /*in cm*/ = 8 /*inches*/ * Math.PI;
    private Distance(final double revolutions) {
        this.revolutions = revolutions;
    }
    public static Distance fromRevolutions(final double revolutions) {
        return new Distance(revolutions);
    }
    public static Distance fromCentimeters(final double centimeters) {
        return new Distance(wheelCircumference / centimeters);
    }
    public static Distance fromMeters(final double meters) {
        return Distance.fromCentimeters(meters * 100);
    }
    public double getAsRevolutions() {
        return this.revolutions;
    }
    public double getAsCentimeters() {
        return this.revolutions * wheelCircumference;
    }
}