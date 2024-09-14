package lesson2.strategymethod;

public class DataProcessor {
    private Strategy strategy;
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void process(String data) {
        if(strategy != null) {
            strategy.precessData(data);
        } else {
            System.out.println("Strategy not set!");
        }
    }
}
