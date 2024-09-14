package lesson1.store3D.models;

public class Angle3D {
    private double pitch;  // Угол наклона по оси X
    private double yaw;    // Угол поворота по оси Y
    private double roll;   // Угол крена по оси Z

    public Angle3D(double pitch, double yaw, double roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public double getYaw() {
        return yaw;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    public double getRoll() {
        return roll;
    }

    public void setRoll(double roll) {
        this.roll = roll;
    }
}
