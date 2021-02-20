package main.persons;

import main.gasStation.GasStation;

public class Cashier extends Person{


    public Cashier(String name, GasStation gasStation) {
        super(name, gasStation);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gasStation.takeMoney();

    }
}
