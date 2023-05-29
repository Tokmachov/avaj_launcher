package avaj.launcher.airport_simulation;

import avaj.launcher.airport_simulation.exceptions.WrongArguments;

public class AirportSimulation {
    public static void main(String[] args) {
        AirportSimulation airportSimulation = new AirportSimulation();
        airportSimulation.run(args);
    }
    private void run(String[] args) {
        if (args.length != 1) {
            throw new WrongArguments();
        }
    }
}
