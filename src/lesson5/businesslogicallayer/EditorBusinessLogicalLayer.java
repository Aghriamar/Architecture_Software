package lesson5.businesslogicallayer;

import lesson5.dataaccesslayer.DatabaseAccess;
import lesson5.corelayer.Model3D;
import lesson5.corelayer.Texture;

import java.util.Collection;
import java.util.Random;

/**
 * Реализация BLL (Business Logical Layer)
 */
public class EditorBusinessLogicalLayer implements BusinessLogicalLayer {
    private DatabaseAccess databaseAccess;

    public EditorBusinessLogicalLayer(DatabaseAccess databaseAccess) {
        this.databaseAccess = databaseAccess;
    }

    @Override
    public Collection<Model3D> getAllModels() {
        return databaseAccess.getAllModels();
    }

    @Override
    public Collection<Texture> getAllTexture() {
        return databaseAccess.getAllTextures();
    }

    @Override
    public void renderModel(Model3D model) {
        processRender(model);
    }

    @Override
    public void renderAllModels() {
        for (Model3D model : getAllModels()) {
            processRender(model);
        }
    }

    @Override
    public void deleteModel(Model3D model) {
        databaseAccess.removeEntity(model);
    }

    @Override
    public void copyModel(Model3D model) {
        Model3D copiedModel = new Model3D((model.getTextures()));
        databaseAccess.addEntity(copiedModel);
    }

    private Random random = new Random();

    private void processRender(Model3D model){
        try{
            Thread.sleep(2500 - random.nextInt(2000));
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
