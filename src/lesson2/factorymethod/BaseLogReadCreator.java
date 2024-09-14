package lesson2.factorymethod;

import lesson2.templatemethod.LogReader;

public abstract class BaseLogReadCreator {

    protected LogReader createLogReader(LogType logType, Object data){
        LogReader logReader = createLogReaderInstance(logType);
        //TODO: Выполнение подготовительных действий
        logReader.setDataSource(data);
        logReader.setCurrentPosition(2);
        return logReader;
    }

    protected abstract LogReader createLogReaderInstance(LogType logType);

}
