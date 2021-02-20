package main.persons;

import main.gasStation.GasStation;

public class Loader extends Person{


    public Loader(String name, GasStation gasStation) {
        super(name, gasStation);
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gasStation.refuelCar();
        }

    }
}
