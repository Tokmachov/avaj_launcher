package avaj.launcher.airport_simulation.weather.tower;

import avaj.launcher.airport_simulation.arcraft.Coordinates;
import avaj.launcher.airport_simulation.arcraft.Flyable;
import avaj.launcher.airport_simulation.logger.Logger;
import avaj.launcher.airport_simulation.weather.Weather;
import avaj.launcher.airport_simulation.weather.provider.WeatherProvider;

import java.util.ArrayList;
import java.util.List;

public class WeatherTower extends Tower {
    public final static Coordinates MIN_COORDINATES = new Coordinates((byte) 0, (byte) 0, (byte) 0);
    public final static Coordinates MAX_COORDINATES = new Coordinates((byte) 30, (byte) 30, (byte) 100);

    public List<Flyable> flyableToBeRemovedAfterUpdate = new ArrayList<>();

    @Override
    public void register(Flyable flyable) {
        flyableList.add(flyable);
        flyable.registerTower(this);
        Logger.out.println("Tower says: " + flyable.getName() + " registered to weather tower.");
    }

    @Override
    public void unregister(Flyable flyable) {
        flyableList.remove(flyable);
        flyable.unregisterTower(this);
        Logger.out.println("Tower says: " + flyable.getName() + " unregistered to weather tower.");
    }

    public Weather getWeather(Coordinates coordinates) {
        return WeatherProvider.getWeatherProvider().getCurrentWeather(coordinates);
    }
    public void changeWeather() {
        WeatherProvider.getWeatherProvider().generate();
        conditionsChanged();

        for (Flyable flyable : flyableToBeRemovedAfterUpdate) {
            this.unregister(flyable);
        }
        flyableToBeRemovedAfterUpdate.clear();
    }
}
