package lesson2.factorymethod;

import lesson2.templatemethod.LogEntry;
import lesson2.templatemethod.LogReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileReader extends LogReader {
    private String filePath;

    @Override
    public Object getDataSource() {
        return filePath;
    }

    @Override
    public void setDataSource(Object data) {
        this.filePath = data.toString();
    }

    @Override
    protected Iterable<String> readEntries(Integer position) {
        List<String> entries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                if (lineCount >= position) {
                    entries.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

    @Override
    protected LogEntry parseLogEntry(String stringEntry) {
        return new LogEntry(stringEntry);
    }
}
