package lesson2.factorymethod;

import lesson2.templatemethod.LogEntry;
import lesson2.templatemethod.LogReader;

import java.util.List;

public class Program {
    public static String data = """
                У лукоморья дуб зелёный;
                Златая цепь на дубе том:
                И днём и ночью кот учёный
                Всё ходит по цепи кругом;
                Идёт направо - песнь заводит,
                Налево - сказку говорит.""";

    public static List<String> mockDatabaseLogs = List.of(
            "DB Entry 1: User logged in",
            "DB Entry 2: User update settings",
            "DB Entry 3: User logged out"
    );

    public static void main(String[] args) {
        LogReader logReader = new ConcreteReaderCreator()
                .createLogReader(LogType.Poem, data);
        displayLogs(logReader);

        LogReader databaseReader = new ConcreteReaderCreator()
                .createLogReader(LogType.Database, mockDatabaseLogs);
        displayLogs(databaseReader);

        LogReader systemLogReader = new ConcreteReaderCreator()
                .createLogReader(LogType.System, null); // Логи уже заданы в классе
        displayLogs(systemLogReader);
    }

    private static void displayLogs(LogReader logReader) {
        for (LogEntry log : logReader.readLogEntry()){
            System.out.println(log.getText());
        }
    }
}
