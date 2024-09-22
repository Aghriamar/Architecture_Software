package lesson5.databaselayer;

import lesson5.corelayer.Entity;

import java.util.Collection;

/**
 * Интерфейс БД
 */
public interface Database {
    void load();
    void save();
    Collection<Entity> getAll();
}
