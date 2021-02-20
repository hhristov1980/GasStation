package main.gasStation;

import main.fuelLoaderDevice.FuelLoaderDevice;
import main.payDesk.PayDesk;
import main.persons.Cashier;
import main.persons.Loader;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;

public class GasStation {
    private String name;
    private CopyOnWriteArraySet<FuelLoaderDevice> fuelLoaderDevices;
    private CopyOnWriteArraySet<PayDesk> payDesks;
    private BlockingQueue<Cashier> cashiers;
    private BlockingQueue<Loader> loaders;


}
