package frc.robot;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class CheesyDriveGetters {
    private final DoubleSupplier getForward;
    private final DoubleSupplier getRotate;
    private final DoubleSupplier getScale;
    private final BooleanSupplier getIsInverted;

    public CheesyDriveGetters(
            final DoubleSupplier getForward,
            final DoubleSupplier getRotate,
            final DoubleSupplier getScale,
            final BooleanSupplier getIsInverted
    ) {
        this.getForward = getForward;
        this.getRotate = getRotate;
        this.getScale = getScale;
        this.getIsInverted = getIsInverted;
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

    public boolean getIsInverted() {
        return getIsInverted.getAsBoolean();
    }
}