package lesson1.store3D.models;

import java.util.List;

public class Scene {
    private int id;
    private List<PoligonalModel> models;
    private List<Flash> flashes;

    public Scene(int id, List<PoligonalModel> models, List<Flash> flashes) {
        this.id = id;
        this.models = models;
        this.flashes = flashes;
    }

    public int getId() {
        return id;
    }

    public List<PoligonalModel> getModels() {
        return models;
    }

    public List<Flash> getFlashes() {
        return flashes;
    }

    public Object method1(Object type) {
        // Логика метода 1
        return null;
    }

    public Object method2(Object type) {
        // Логика метода 2
        return null;
    }
}
