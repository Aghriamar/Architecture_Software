package lesson2.factorymethod;

import lesson2.templatemethod.LogReader;
import lesson2.templatemethod.PoemReader;

public class ConcreteReaderCreator extends BaseLogReadCreator {
    @Override
    protected LogReader createLogReaderInstance(LogType logType) {

        return switch (logType){
            case Poem -> new PoemReader();
            case Text -> new TextFileReader();
            case Database -> new DataBaseReader();
            case System -> new OperationSystemLogEventReader();
        };
    }
}
