package avaj.launcher.airport_simulation.arcraft;

import avaj.launcher.airport_simulation.weather.tower.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower tower);
    void unregisterTower(WeatherTower tower);

    String getName();
}
