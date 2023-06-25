package avaj.launcher.airport_simulation.weather.provider;

import avaj.launcher.airport_simulation.weather.Weather;
import avaj.launcher.airport_simulation.weather.tower.WeatherTower;
import avaj.launcher.airport_simulation.arcraft.Coordinates;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WeatherProvider implements WeatherProviding {
    private static WeatherProviding weatherProvider;

    public static WeatherProviding getWeatherProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    private Map<Coordinates, Weather> coordinatesWeatherMap = new HashMap<>();
    private WeatherProvider() {}

    @Override
    public Weather getCurrentWeather(Coordinates coordinates) {
        return coordinatesWeatherMap.get(coordinates);
    }

    @Override
    public void generate() {
        byte longitudeMin = WeatherTower.MIN_COORDINATES.getLongitude();
        byte longitudeMax = WeatherTower.MAX_COORDINATES.getLongitude();
        byte latitudeMin = WeatherTower.MIN_COORDINATES.getLatitude();
        byte latitudeMax = WeatherTower.MAX_COORDINATES.getLatitude();
        byte heightMin = WeatherTower.MIN_COORDINATES.getHeight();
        byte heightMax = WeatherTower.MAX_COORDINATES.getHeight();

        coordinatesWeatherMap.clear();

        for (byte longitude = longitudeMin; longitude <= longitudeMax; longitude++) {
            for (byte latitude = latitudeMin; latitude <= latitudeMax; latitude++) {
                for (byte height = heightMin; height <= heightMax; height++) {
                    Coordinates coordinates = new Coordinates(longitude, latitude, height);
                    Random random = new Random();
                    Weather weather = Weather.values()[random.nextInt(Weather.values().length)];
                    coordinatesWeatherMap.put(coordinates, weather);
                }
            }
        }
    }
}
