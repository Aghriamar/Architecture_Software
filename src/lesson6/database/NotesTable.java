package lesson6.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class NotesTable {
    private Random random = new Random();
    private Collection<NotesRecord> records;

    public Collection<NotesRecord> getRecords() {
        if(records == null){
            records = new ArrayList<>();
            int recordCount = 5 + random.nextInt(10);
            for(int i = 0; i < recordCount; i++){
                records.add(new NotesRecord("title #" + 1, "details #" + i));
            }
        }
        return records;
    }
}
