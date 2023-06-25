package avaj.launcher.airport_simulation;

import avaj.launcher.airport_simulation.arcraft.AircraftFactory;
import avaj.launcher.airport_simulation.exceptions.ScenarioParsingException;
import avaj.launcher.airport_simulation.exceptions.WrongArguments;
import avaj.launcher.airport_simulation.logger.Logger;
import avaj.launcher.airport_simulation.weather.tower.WeatherTower;
import avaj.launcher.airport_simulation.arcraft.AirCraftDto;
import avaj.launcher.airport_simulation.arcraft.Flyable;
import avaj.launcher.airport_simulation.scenario.Scenario;

import java.io.IOException;

public class AirportSimulation {
    public static void main(String[] args) {
        AirportSimulation airportSimulation = new AirportSimulation();
        try {
            airportSimulation.run(args);
        } catch (Exception e) {
            Logger.out.println("Error occurred when executing program. Type: " + e.getClass().getSimpleName() + ". Message: " + e.getMessage());
        }
    }
    private void run(String[] args) throws IOException, ScenarioParsingException {
        if (args.length != 1) {
            throw new WrongArguments();
        }
        Scenario scenario = new Scenario(args[0]);
        scenario.parse();
        run(scenario);
    }

    private void run(Scenario scenario) {
        WeatherTower weatherTower = new WeatherTower();

        for (AirCraftDto airCraftDto : scenario.getAircraftList()) {
            Flyable flyable = AircraftFactory.newAircraft(airCraftDto.getType(), airCraftDto.getName(), airCraftDto.getLatitude(), airCraftDto.getLongitude(), airCraftDto.getHeight());
            weatherTower.register(flyable);
        }
        for (int i = 0; i < scenario.getNumberOfTimesRun(); i++) {
            weatherTower.changeWeather();
        }
    }
}
