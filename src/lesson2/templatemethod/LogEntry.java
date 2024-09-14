package lesson2.templatemethod;
/**
 * Запись лога
 */

public  class LogEntry {

    private String text;

    public String getText(){
        return text;
    }

    public LogEntry(String text) {
        this.text = text;
    }
}
