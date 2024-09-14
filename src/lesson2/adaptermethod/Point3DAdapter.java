package lesson2.adaptermethod;

public class Point3DAdapter implements Drawable2D{
    private Point3D point3D;

    public Point3DAdapter(Point3D point3D) {
        this.point3D = point3D;
    }

    @Override
    public void draw(Point2D point) {
        System.out.println("Drawing 3D shape at (" +
                point3D.getX() + "," +
                point3D.getY() + ", ignoring Z: " +
                point3D.getZ() + ")");
    }
}
