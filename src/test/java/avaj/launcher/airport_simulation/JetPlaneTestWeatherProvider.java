package avaj.launcher.airport_simulation;

import avaj.launcher.airport_simulation.arcraft.Coordinates;
import avaj.launcher.airport_simulation.logger.Logger;
import avaj.launcher.airport_simulation.weather.Weather;
import avaj.launcher.airport_simulation.weather.provider.WeatherProviding;

public class JetPlaneTestWeatherProvider implements WeatherProviding {
    private Weather weather;

    public void setCurrentWeather(Weather weather) {
        this.weather = weather;
    }
    @Override
    public Weather getCurrentWeather(Coordinates coordinates) {
        return weather;
    }

    @Override
    public void generate() {
        Logger.out.println("Generate weather");
    }
}
