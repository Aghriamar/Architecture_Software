package lesson3;

public class RefuelStationV2 implements IRefueling{
    @Override
    public void fuel(FuelType fuelType){
        switch (fuelType){
            case Diesel -> System.out.println("Заправка дизельным топливом");
            case Gasoline -> System.out.println("Заправка бензином");
        }
    }
}