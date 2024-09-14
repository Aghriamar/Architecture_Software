package lesson2.factorymethod;

import lesson2.templatemethod.LogEntry;
import lesson2.templatemethod.LogReader;

import java.util.ArrayList;
import java.util.List;

public class OperationSystemLogEventReader extends LogReader {
    private String[] systemLogs = {
            "System Boot: 2024-09-01 10:00:00",
            "Login Success: User admin at 2024-09-01 10:05:00",
            "Shutdown: 2024-09-01 18:00:00"
    };

    @Override
    public Object getDataSource() {
        return systemLogs;
    }

    @Override
    public void setDataSource(Object data) {
        // В этом случае мы используем заранее подготовленный массив логов
    }

    @Override
    protected Iterable<String> readEntries(Integer position) {
        List<String> entries = new ArrayList<>();
        for (int i = position - 1; i < systemLogs.length; i++) {
            entries.add(systemLogs[i]);
        }
        return entries;
    }

    @Override
    protected LogEntry parseLogEntry(String stringEntry) {
        return new LogEntry(stringEntry);
    }
}
