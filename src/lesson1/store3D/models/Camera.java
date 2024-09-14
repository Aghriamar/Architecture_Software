package lesson1.store3D.models;

public class Camera {
    private Point3D location;
    private Angle3D angle;

    public Camera(Point3D location, Angle3D angle) {
        this.location = location;
        this.angle = angle;
    }

    public void rotate(Angle3D angle) {
        // Логика вращения камеры
    }

    public void move(Point3D point) {
        // Логика перемещения камеры
    }
}
