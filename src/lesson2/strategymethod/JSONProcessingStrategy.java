package lesson2.strategymethod;

import org.json.JSONObject;

public class JSONProcessingStrategy implements Strategy{
    @Override
    public void precessData(String data) {
        JSONObject json = new JSONObject(data);
        System.out.println("Processing JSON data: " + json.toString(2));
    }
}
