package lesson5.businesslogicallayer;

import lesson5.corelayer.Model3D;
import lesson5.corelayer.Texture;

import java.util.Collection;

/**
 * Интерфейс BLL (Business Logical Layer)
 */
public interface BusinessLogicalLayer{
    Collection<Model3D> getAllModels();
    Collection<Texture> getAllTexture();
    void renderModel(Model3D model);
    void renderAllModels();
    void deleteModel(Model3D model);
    void copyModel(Model3D model);
}
