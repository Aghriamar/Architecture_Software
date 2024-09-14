package lesson1.store3D.models;

public class Flash {
    private Point3D location;
    private Angle3D angles;
    private Color color;
    private float power;

    public Flash(Point3D location, Angle3D angle, Color color, float power) {
        this.location = location;
        this.angles = angle;
        this.color = color;
        this.power = power;
    }

    public void rotate(Angle3D angle) {
        // Логика вращения
    }

    public void move(Point3D point) {
        // Логика перемещения
    }
}
