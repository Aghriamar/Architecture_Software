package lesson2.adaptermethod;

public class Program {
    public static void main(String[] args) {
        Point3D point3D = new Point3D(5, 10 , 15);
        Drawable2D drawer2D = new ShapeDrawer();
        drawer2D.draw(new Point2D(5,10));

        Drawable2D drawerWith3D = new Point3DAdapter(point3D);
        drawerWith3D.draw(new Point2D(5,10));
    }
}
