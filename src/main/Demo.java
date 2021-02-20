package main;

import main.cars.Car;
import main.fuelLoaderDevice.FuelDispenser;
import main.gasStation.GasStation;
import main.gasStation.Statistics;
import main.payDesk.PayDesk;
import main.persons.Cashier;
import main.persons.Loader;
import main.util.Constants;
import main.util.Randomizer;

public class Demo {
    public static void main(String[] args) {
        GasStation gasStation = new GasStation("IT Gas");
        Statistics statistics = new Statistics(gasStation);
        gasStation.setStatistics(statistics);
        statistics.setDaemon(true);
        for(int i = 0; i<5; i++){
            gasStation.addFuelDispenser(new FuelDispenser(gasStation));
        }
        for(int i = 0; i<2; i++){
            gasStation.addPayDesks(new PayDesk(gasStation));
            gasStation.addCashiers(new Cashier(Constants.NAMES[Randomizer.getRandomInt(0,Constants.NAMES.length-1)],gasStation));
            gasStation.addLoader(new Loader(Constants.NAMES[Randomizer.getRandomInt(0,Constants.NAMES.length-1)],gasStation));
        }
        gasStation.open();
        for(int i = 0; i<3; i++){
            Car.FuelType type = Car.FuelType.values()[Randomizer.getRandomInt(0,Car.FuelType.values().length-1)];
            Car car = new Car(type,gasStation);
            car.start();
        }
        statistics.start();
    }

}
