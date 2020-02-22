package frc.robot;

import java.util.function.DoubleSupplier;

public class CheesyDriveGetters {
    private final DoubleSupplier getForward;
    private final DoubleSupplier getRotate;
    private final DoubleSupplier getScale;

    public CheesyDriveGetters(final DoubleSupplier getForward, final DoubleSupplier getRotate, final DoubleSupplier getScale) {
        this.getForward = getForward;
        this.getRotate = getRotate;
        this.getScale = getScale;
    }

    public double getForward() {
        return getForward.getAsDouble();
    }

    public double getRotate() {
        return getRotate.getAsDouble();
    }

    public double getScale() {
        return getScale.getAsDouble();
    }
}