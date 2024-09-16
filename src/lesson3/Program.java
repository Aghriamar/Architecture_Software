package lesson3;

import java.awt.*;

public class Program {
    /*
    * 1. Спроектировать абстрактный класс "Car" у которого должны
    * быть свойства: марка, модель, цвет, тип кузова, число колёс, тип
    * топлива, тип коробки передач, объём двигателя; методы:
    * движение, обслуживание, переключение передач, включение
    * фар, включение дворников.
    *
    * 2. Создать конкретный автомобиль путём наследования класс
    * "Car".
    * 3. Расширить абстрактный класс "Car" добавить метод: подметать
    * улицу. Создать конкретный автомобиль путём наследование
    * класса "Car". Провести проверку принципа SRP.
    * 4. Расширить абстрактный класс "Car", добавить метод:
    * включение противотуманных фар, перевозка груза. Провести
    * проверку принципа OCP.
    * 5. Создать конкретный автомобиль путём наследования класса
    * "Car", определить число колёс = 3. Провести проверку принципа LSP.
    * 6. Создать конкретный автомобиль путём наследования класса
    * "Car", определить метод "движение" - "полёт". Провести
    * проверку принципа LSP.
    * 7. Создать интерфейс "Заправочная станция", создать метод:
    * заправка топливом.
    * 8. Имплементировать метод интерфейса "Заправочная станция" в
    * конкретный класс "Car".
    * 9. Добавить в интерфейс "Заправочная станция" методы: протирка
    * лобового стекла, протирка фар, протирка зеркал.
    * 10. Имплементировать все методы интерфейса "Заправочная
    * станция" в конкретный класс "Car". Провести проверку
    * принципа ISP. Создать дополнительный/е интерфейсы и
    * имплементировать их в конкретный класс "Car". Провести
    * проверку принципа ISP.
    * 11. Создать путём наследования класса "Car" два
    * автомобиля: с бензиновым и дизельным двигателям,
    * имплементировать метод "Заправка топливом" интерфейса
    * "Заправочная станция". Реализовать заправку каждого
    * автомобиля подходящим топливом. Провести проверку принципа DIP.
    *
    * @param args
     */

    public static void main(String[] args) {
//        Harvester harvester = new Harvester("A", "B", Color.BLACK);
//        RefuelStation refuelStation  = new RefuelStation();
//        harvester.setRefuelStation(refuelStation);
//        harvester.fuel();

        // Создаем Harvester
        Harvester harvester = new Harvester("Harvester", "LEXION 8000", Color.BLACK, FuelType.Diesel);

        // Создаем и назначаем заправочную станцию
        RefuelStation refuelStation = new RefuelStation();
        harvester.setRefuelStation(refuelStation);
        harvester.fuel();

        // Создаем и назначаем станцию техосмотра
        InspectionStation inspectionStation = new InspectionStation();
        harvester.setInspectionStation(inspectionStation);

        // Демонстрация работы
        harvester.movement();
        harvester.gearShifting();
        harvester.inspect();
        harvester.wipMirrors();
        harvester.wipWindshield();
        harvester.wipHeadlights();

        // Расчет стоимости техобслуживания
        double maintenanceCost = Program.calculateMaintenance(harvester);
        System.out.println("Стоимость техобслуживания: " + maintenanceCost + "\n");

        // Создаем SportCar
        SportCar sportCar = new SportCar("SportCar", "Porsche 911", Color.GREEN, FuelType.Gasoline);

        // Создаем и назначаем заправочную станцию
        RefuelStation refuelStation1 = new RefuelStation();
        sportCar.setRefuelStation(refuelStation1);
        sportCar.fuel();

        // Создаем и назначаем станцию техосмотра
        InspectionStation inspectionStation1 = new InspectionStation();
        sportCar.setInspectionStation(inspectionStation1);

        // Демонстрация работы
        sportCar.movement();
        sportCar.gearShifting();
        sportCar.inspect();
        sportCar.wipMirrors();
        sportCar.wipWindshield();
        sportCar.wipHeadlights();

        // Расчет стоимости техобслуживания
        double maintenanceCost1 = Program.calculateMaintenance(sportCar);
        System.out.println("Стоимость техобслуживания: " + maintenanceCost1 + "\n");

        // Создаем FlyCar
        FlyCar flyCar = new FlyCar("FlyCar", "FLY A2501 Porsche 908", Color.BLUE, FuelType.Diesel);

        // Создаем и назначаем заправочную станцию
        RefuelStation refuelStation2 = new RefuelStation();
        flyCar.setRefuelStation(refuelStation2);
        flyCar.fuel();

        // Создаем и назначаем станцию техосмотра
        InspectionStation inspectionStation2 = new InspectionStation();
        flyCar.setInspectionStation(inspectionStation2);

        // Демонстрация работы
        flyCar.movement();
        flyCar.gearShifting();
        flyCar.inspect();
        flyCar.wipMirrors();
        flyCar.wipWindshield();
        flyCar.wipHeadlights();

        // Расчет стоимости техобслуживания
        double maintenanceCost2 = Program.calculateMaintenance(flyCar);
        System.out.println("Стоимость техобслуживания: " + maintenanceCost2 + "\n");
    }

    public static double calculateMaintenance(Car car){
        if(car.getWheelsCount() == 6){
            return 1500*6;
        }
        else {
            return 1000*4;
        }
    }
}
