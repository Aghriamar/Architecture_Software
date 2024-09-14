package lesson2.factorymethod;

import lesson2.templatemethod.LogEntry;
import lesson2.templatemethod.LogReader;

import java.util.ArrayList;
import java.util.List;

public class DataBaseReader extends LogReader {
    private List<String> databaseLogs;

    @Override
    public Object getDataSource() {
        return databaseLogs;
    }

    @Override
    public void setDataSource(Object data) {
        // В качестве данных передаём список записей (эмуляция базы данных)
        this.databaseLogs = (List<String>) data;
    }

    @Override
    protected Iterable<String> readEntries(Integer position) {
        List<String> entries = new ArrayList<>();
        for (int i = position; i < databaseLogs.size(); i++) {
            entries.add(databaseLogs.get(i));
        }
        return entries;
    }

    @Override
    protected LogEntry parseLogEntry(String stringEntry) {
        return new LogEntry(stringEntry);
    }
}
