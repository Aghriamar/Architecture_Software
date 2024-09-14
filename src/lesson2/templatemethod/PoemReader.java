package lesson2.templatemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Класс реализует примитивные шаги алгоритма
 */

public class PoemReader extends LogReader {
    private String data;
    @Override
    public Object getDataSource() {
        return null;
    }

    @Override
    public void setDataSource(Object data) {
        this.data = data.toString();
    }

    public PoemReader(){

    }

    public PoemReader(String data){
        this.data = data;
    }

    @Override
    protected Iterable<String> readEntries(Integer position) {
        Scanner scanner = new Scanner(data);
        List<String> logList = new ArrayList<>();
        int counter = 0;
        while (scanner.hasNext()){
            counter++;
            if(counter >= position){
                position = counter;
                String line = scanner.nextLine();
                logList.add(line);
            }
            else {
                scanner.nextLine();
            }
        }
        return logList;
    }

    @Override
    protected LogEntry parseLogEntry(String stringEntry) {

        return new LogEntry(stringEntry);
    }
}

