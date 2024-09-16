package lesson3;

public class InspectionStation implements IInspection{

    @Override
    public void inspect() {
        System.out.println("Проведение технического осмотра.");
    }
}
