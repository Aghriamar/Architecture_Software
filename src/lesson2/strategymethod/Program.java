package lesson2.strategymethod;

public class Program {
    public static void main(String[] args) {
        String textData = "Sample text data";
        String jsonData = "{ \"name\": \"Jhon\", \"age\": 30 }";
        String xmlData = "<person><name>Jhon</name><age>30</age></person>";

        DataProcessor processor = new DataProcessor();

        processor.setStrategy(new TextProcessingStrategy());
        processor.process(textData);

        processor.setStrategy(new TextProcessingStrategy());
        processor.process(jsonData);

        processor.setStrategy(new TextProcessingStrategy());
        processor.process(xmlData);
    }
}
