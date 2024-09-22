package lesson5.uilayer;

import lesson5.businesslogicallayer.BusinessLogicalLayer;
import lesson5.businesslogicallayer.EditorBusinessLogicalLayer;
import lesson5.corelayer.Model3D;
import lesson5.corelayer.ProjectFile;
import lesson5.corelayer.Texture;
import lesson5.dataaccesslayer.DatabaseAccess;
import lesson5.dataaccesslayer.EditorDatabaseAccess;
import lesson5.databaselayer.Database;
import lesson5.databaselayer.EditorDatabase;

import java.util.ArrayList;

/**
 * UI (User Interface)
 */
public class Editor3D implements UILayer {

    private ProjectFile projectFile;
    private BusinessLogicalLayer businessLogicalLayer;
    private DatabaseAccess databaseAccess;
    private Database database;

    private void initialize(){
        database = new EditorDatabase(projectFile);
        databaseAccess = new EditorDatabaseAccess(database);
        businessLogicalLayer = new EditorBusinessLogicalLayer(databaseAccess);
    }

    @Override
    public void openProject(String fileName) {
        this.projectFile = new ProjectFile(fileName);
        initialize();
    }

    @Override
    public void showProjectSettings() {
        // Предусловие
        checkProjectFile();

        System.out.println("*** Project v1 ***");
        System.out.println("******************");
        System.out.printf("fileName: %s\n", projectFile.getFileName());
        System.out.printf("setting1: %d\n", projectFile.getSetting1());
        System.out.printf("setting2: %s\n", projectFile.getSetting2());
        System.out.printf("setting3: %s\n", projectFile.getSetting3());
        System.out.println("******************");
    }

    private void checkProjectFile(){
        if (projectFile == null)
            throw new NullPointerException("Файл проекта не определен.");
    }

    @Override
    public void saveProject() {
        // Предусловие
        checkProjectFile();

        database.save();
        System.out.println("Изменения успешно сохранены.");
    }

    @Override
    public void printAllModels() {
        // Предусловие
        checkProjectFile();

        ArrayList<Model3D> models = (ArrayList<Model3D>)businessLogicalLayer.getAllModels();
        for (int i = 0; i < models.size(); i++) {
            System.out.printf("===%d===\n", i);
            System.out.println(models.get(i));
            for (Texture texture : models.get(i).getTextures()) {
                System.out.printf("\t%s\n", texture);
            }
        }
    }

    @Override
    public void printAllTextures() {
        // Предусловие
        checkProjectFile();

        ArrayList<Texture> textures = (ArrayList<Texture>)businessLogicalLayer.getAllTexture();
        for (int i = 0; i < textures.size(); i++) {
            System.out.printf("===%d===\n", i);
            System.out.println(textures.get(i));
        }
    }

    @Override
    public void renderAll() {
        // Предусловие
        checkProjectFile();

        System.out.println("Подождите ...");
        long startTime = System.currentTimeMillis();
        businessLogicalLayer.renderAllModels();
        long endTime = (System.currentTimeMillis() - startTime);
        System.out.printf("Операция выполнена за %d мс.\n", endTime);
    }

    @Override
    public void renderModel(int i) {
        // Предусловие
        checkProjectFile();

        ArrayList<Model3D> models = (ArrayList<Model3D>)businessLogicalLayer.getAllModels();
        if (i < 0 || i > models.size() - 1)
            throw new RuntimeException("Номер модели указан некорректно.");
        System.out.println("Подождите ...");
        long startTime = System.currentTimeMillis();
        businessLogicalLayer.renderModel(models.get(i));
        long endTime = (System.currentTimeMillis() - startTime);
        System.out.printf("Операция выполнена за %d мс.\n", endTime);
    }

    @Override
    public void deleteModel(int i) {
        // Предусловие
        checkProjectFile();

        ArrayList<Model3D> models = (ArrayList<Model3D>) businessLogicalLayer.getAllModels();
        if (i < 0 || i > models.size() - 1) {
            throw new RuntimeException("Номер модели указан некорректно.");
        }
        Model3D modelToDelete = models.get(i);
        businessLogicalLayer.deleteModel(modelToDelete);
        System.out.printf("Модель %s удалена.\n", modelToDelete);
    }

    @Override
    public void copyModel(int i) {
        // Предусловие
        checkProjectFile();

        ArrayList<Model3D> models = (ArrayList<Model3D>) businessLogicalLayer.getAllModels();
        if (i < 0 || i > models.size() - 1) {
            throw new RuntimeException("Номер модели указан некорректно.");
        }
        Model3D modelToCopy = models.get(i);
        businessLogicalLayer.copyModel(modelToCopy);
        System.out.printf("Модель %s успешно скопирована.\n", modelToCopy);
    }
}
