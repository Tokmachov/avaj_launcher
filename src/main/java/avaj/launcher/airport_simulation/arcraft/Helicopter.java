package avaj.launcher.airport_simulation.arcraft;

import avaj.launcher.airport_simulation.weather.tower.WeatherTower;

public class Helicopter extends AirCraft implements Flyable {
    private WeatherTower weatherTower;
    private static final String MESSAGE = "I wrote a book about helicopters. It really took off.";
    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void registerTower(WeatherTower tower) {
        weatherTower = tower;
    }

    @Override
    public void unregisterTower(WeatherTower tower) {
        tower = null;
    }

    @Override
    public void updateConditions() {
        super.updateConditions(weatherTower, MESSAGE);
        if (coordinates.getHeight() == 0) {
            weatherTower.flyableToBeRemovedAfterUpdate.add(this);
        }
    }

    @Override
    protected void onSunWeather() {
        super.onSunWeather();
        increaseLongitude((byte) 10);
        increaseHeight((byte) 2);
    }

    @Override
    protected void onFogWeather() {
        super.onFogWeather();
        increaseLongitude((byte) 1);
    }

    @Override
    protected void onSnowWeather() {
        super.onSnowWeather();
        decreaseHeight((byte) 12);
    }

    @Override
    protected void onRainWeather() {
        super.onRainWeather();
        increaseLongitude((byte) 5);
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
