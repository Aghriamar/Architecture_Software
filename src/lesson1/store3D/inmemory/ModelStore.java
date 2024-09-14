package lesson1.store3D.inmemory;

import lesson1.store3D.models.Camera;
import lesson1.store3D.models.Flash;
import lesson1.store3D.models.PoligonalModel;
import lesson1.store3D.models.Scene;

import java.util.ArrayList;
import java.util.List;

public class ModelStore implements IModelChanger {
    private List<IModelChangedObserver> observers = new ArrayList<>();
    private List<PoligonalModel> models = new ArrayList<>();

    private List<Flash> flashes = new ArrayList<>();

    private List<Scene> scenes = new ArrayList<>();

    private List<Camera> cameras = new ArrayList<>();

    public void add(PoligonalModel model) {
        models.add(model);
        notifyChange();
    }

    public ModelStore() {
        this.models = new ArrayList<>();
        this.flashes = new ArrayList<>();
        this.scenes = new ArrayList<>();
        this.cameras = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public Scene getScene(int id) {
        // Логика получения сцены по ID
        return null;
    }

    @Override
    public void notifyChange() {
        for (IModelChangedObserver observer : observers){
            observer.applyUpdateModel();
        }
    }

    @Override
    public void RegisterModelChanger(IModelChangedObserver o) {
        observers.add(o);
    }

    @Override
    public void RemoveModelChanger(IModelChangedObserver o) {
        observers.remove(o);
    }
}
