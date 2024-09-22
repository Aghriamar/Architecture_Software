package lesson5.uilayer;

/**
 * Интерфейс UI
 */
public interface UILayer{
    void openProject(String fileName);
    void showProjectSettings();
    void saveProject();
    void printAllModels();
    void printAllTextures();
    void renderAll();
    void renderModel(int i);
    void deleteModel(int i);
    void copyModel(int i);
}
