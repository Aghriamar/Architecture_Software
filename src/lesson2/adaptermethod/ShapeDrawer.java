package lesson2.adaptermethod;

public class ShapeDrawer implements Drawable2D{

    @Override
    public void draw(Point2D point) {
        System.out.println("Drawing 2D shape at (" + point.getX() + ", " + point.getY() + ")");
    }
}
