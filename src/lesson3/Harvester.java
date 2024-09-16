package lesson3;

import java.awt.*;

public class Harvester extends Car implements Fueling, IWiping, IInspection {

    private IRefueling refueling;
    private IInspection inspection;
    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Harvester(String make, String model, Color color, FuelType fuelType) {
        super(make, model, color);
        this.model = model;
        setWheelsCount(6);
        this.fuelType = fuelType;
    }

    public void setRefuelStation(RefuelStation refuelStation) {
        this.refueling = refuelStation;
    }

    public void setInspectionStation(InspectionStation InspectionStation) {
        this.inspection = InspectionStation;
    }

    @Override
    public void movement() {
        System.out.println(model + " движется.");
    }

    @Override
    public void maintenance() {
        System.out.println(model + " проходит техобслуживание.");
    }

    @Override
    public boolean gearShifting() {
        System.out.println("Переключение передач " + model + ".");
        return true;
    }

    @Override
    public boolean switchHeadlights() {
        System.out.println("Фары " + model + " включены.");
        return true;
    }

    @Override
    public boolean switchWipers() {
        System.out.println("Дворники " + model + " включены.");
        return true;
    }

    public void sweeping() {
        System.out.println(model + " метёт улицу.");
    }


    @Override
    public void wipMirrors() {
        System.out.println("Зеркала " + model + " вымыты.");
    }

    @Override
    public void wipWindshield() {
        System.out.println("Лобовое стекло " + model + " вымыто.");
    }

    @Override
    public void wipHeadlights() {
        System.out.println("Фары " + model + " вымыты.");
    }

    /**
     * Заправить автомобиль
     */
    @Override
    public void fuel() {
        if(refueling != null) {
            refueling.fuel(fuelType);
        } else {
            System.out.println("Заправочная станция не установлена.");
        }
    }

    @Override
    public void inspect(){
        if (inspection != null) {
            inspection.inspect();
        } else {
            System.out.println("Станция техосмотра не установлена.");
        }
    }
}
