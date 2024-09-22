package lesson5.dataaccesslayer;

import lesson5.corelayer.Entity;
import lesson5.corelayer.Model3D;
import lesson5.corelayer.Texture;
import lesson5.databaselayer.Database;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Имплементация DAL (Data Access Layer)
 */
public class EditorDatabaseAccess implements DatabaseAccess {

    private final Database editorDatabase;

    public EditorDatabaseAccess(Database editorDatabase) {
        this.editorDatabase = editorDatabase;
    }

    @Override
    public Collection<Model3D> getAllModels() {
        Collection<Model3D> models = new ArrayList<>();
        for (Entity entity: editorDatabase.getAll()){
            if (entity instanceof Model3D){
                models.add((Model3D) entity);
            }
        }
        return models;
    }

    @Override
    public Collection<Texture> getAllTextures() {
        Collection<Texture> textures = new ArrayList<>();
        for (Entity entity: editorDatabase.getAll()){
            if (entity instanceof Texture){
                textures.add((Texture) entity);
            }
        }
        return textures;
    }

    @Override
    public void addEntity(Entity entity) {
        editorDatabase.getAll().add(entity);
    }

    @Override
    public void removeEntity(Entity entity) {
        editorDatabase.getAll().remove(entity);
    }
}
