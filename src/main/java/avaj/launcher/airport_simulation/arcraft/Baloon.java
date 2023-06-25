package avaj.launcher.airport_simulation.arcraft;

import avaj.launcher.airport_simulation.weather.tower.WeatherTower;

public class Baloon extends AirCraft implements Flyable {
    private WeatherTower weatherTower;

    private static final String MESSAGE = "My wife asked me why hot air balloon rides are so expensive. I said inflation";
    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void registerTower(WeatherTower tower) {
        weatherTower = tower;
    }

    @Override
    public void unregisterTower(WeatherTower tower) {
        weatherTower = null;
    }

    @Override
    public void updateConditions() {
        updateConditions(weatherTower, MESSAGE);
        if (coordinates.getHeight() == 0) {
            weatherTower.flyableToBeRemovedAfterUpdate.add(this);
        }
    }

    @Override
    protected void onSunWeather() {
        super.onSunWeather();
        increaseLongitude((byte) 2);
        increaseHeight((byte) 4);
    }

    @Override
    protected void onFogWeather() {
        super.onFogWeather();
        decreaseHeight((byte) 3);
    }

    @Override
    protected void onSnowWeather() {
        super.onSnowWeather();
        decreaseHeight((byte) 15);
    }

    @Override
    protected void onRainWeather() {
        super.onRainWeather();
        decreaseHeight((byte) 5);
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
