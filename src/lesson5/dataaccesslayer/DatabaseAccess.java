package lesson5.dataaccesslayer;

import lesson5.corelayer.Entity;
import lesson5.corelayer.Model3D;
import lesson5.corelayer.Texture;

import java.util.Collection;

/**
 * Интерфейс Data Access Layer
 */
public interface DatabaseAccess{
    void addEntity(Entity entity);
    void removeEntity(Entity entity);
    Collection<Texture> getAllTextures();
    Collection<Model3D> getAllModels();
}
