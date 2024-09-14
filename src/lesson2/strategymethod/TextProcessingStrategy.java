package lesson2.strategymethod;

public class TextProcessingStrategy implements Strategy{
    @Override
    public void precessData(String data) {
        System.out.println("Processing text data: " + data);
    }
}
