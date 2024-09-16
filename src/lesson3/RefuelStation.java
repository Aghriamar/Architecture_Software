package lesson3;

public class RefuelStation implements IRefueling{
    @Override
    public void fuel(FuelType fuelType){
        switch (fuelType){
            case Diesel -> System.out.println("Заправка дизельным топливом");
            case Gasoline -> System.out.println("Заправка бензином");
        }
    }
}
