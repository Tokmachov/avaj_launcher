package avaj.launcher.airport_simulation.weather.provider;

import avaj.launcher.airport_simulation.arcraft.Coordinates;
import avaj.launcher.airport_simulation.weather.Weather;
import avaj.launcher.airport_simulation.weather.tower.WeatherTower;

import java.util.Random;

public interface WeatherProviding {
    Weather getCurrentWeather(Coordinates coordinates);
    void generate();
}
