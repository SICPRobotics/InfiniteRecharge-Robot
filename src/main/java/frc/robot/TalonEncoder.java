package frc.robot;

import java.util.function.IntSupplier;

public final class TalonEncoder {
    private final Runnable reset;
    private final IntSupplier get;

    public TalonEncoder(final Runnable reset, final IntSupplier get) {
        this.reset = reset;
        this.get = get;
    }

    public void reset() {
        this.reset.run();
    }

    public int get() {
        return this.get.getAsInt();
    }
}