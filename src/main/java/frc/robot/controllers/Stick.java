package frc.robot.controllers;

import java.util.function.DoubleSupplier;

public class Stick {
    private DoubleSupplier getXSupplier, getYSupplier;
    public Stick(DoubleSupplier getX, DoubleSupplier getY) {
        this.getXSupplier = getX;
        this.getYSupplier = getY;
    }
    public double getX() {
        return this.getXSupplier.getAsDouble();
    }
    public double getY() {
        return -this.getYSupplier.getAsDouble();
    }
}