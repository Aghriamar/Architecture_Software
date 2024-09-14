package lesson2.factorymethod;

import lesson2.templatemethod.LogEntry;
import lesson2.templatemethod.LogReader;

public class Program {
    public static String data = """
                У лукоморья дуб зелёный;
                Златая цепь на дубе том:
                И днём и ночью кот учёный
                Всё ходит по цепи кругом;
                Идёт направо - песнь заводит,
                Налево - сказку говорит.""";

    public static void main(String[] args) {
        LogReader logReader = new ConcreteReaderCreator()
                .createLogReader(LogType.Poem, data);
        for (LogEntry log : logReader.readLogEntry()){
            System.out.println(log.getText());
        }
    }
}
