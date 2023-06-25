package avaj.launcher.airport_simulation.arcraft;

import avaj.launcher.airport_simulation.logger.Logger;
import avaj.launcher.airport_simulation.weather.Weather;
import avaj.launcher.airport_simulation.weather.tower.WeatherTower;

public class AirCraft {
    private static long idCounter;

    protected long id;
    protected String name;
    protected Coordinates coordinates;

    AirCraft(String name, Coordinates coordinates) {
        this.id = nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    private long nextId() {
        return idCounter++;
    }

    protected void increaseLatitude(byte delta) {
        byte newLatitude = (byte) (coordinates.getLatitude() + delta);
        newLatitude = newLatitude > WeatherTower.MAX_COORDINATES.getLatitude()
                ? WeatherTower.MAX_COORDINATES.getLatitude()
                : newLatitude;
        coordinates = new Coordinates(coordinates.getLongitude(), newLatitude, coordinates.getHeight());
    }

    protected void decreaseLatitude(byte delta) {
        byte newLatitude = (byte) (coordinates.getLatitude() - delta);
        newLatitude = newLatitude < WeatherTower.MIN_COORDINATES.getLatitude()
                ? WeatherTower.MIN_COORDINATES.getLatitude()
                : newLatitude;
        coordinates = new Coordinates(coordinates.getLongitude(), newLatitude, coordinates.getHeight());
    }

    protected void increaseLongitude(byte delta) {
        byte newLongitude = (byte) (coordinates.getLongitude() + delta);
        newLongitude = newLongitude > WeatherTower.MAX_COORDINATES.getLongitude()
                ? WeatherTower.MAX_COORDINATES.getLongitude()
                : newLongitude;

        coordinates = new Coordinates(newLongitude, coordinates.getLatitude(), coordinates.getHeight());
    }

    protected void decreaseLongitude(byte delta) {
        byte newLongitude = (byte) (coordinates.getLongitude() - delta);
        newLongitude = newLongitude < WeatherTower.MIN_COORDINATES.getLongitude()
                ? WeatherTower.MIN_COORDINATES.getLongitude()
                : newLongitude;

        coordinates = new Coordinates(newLongitude, coordinates.getLatitude(), coordinates.getHeight());
    }

    protected void increaseHeight(byte delta) {
        byte newHeight = (byte) (coordinates.getHeight() + delta);
        if (newHeight > WeatherTower.MAX_COORDINATES.getHeight()) {
            newHeight = WeatherTower.MAX_COORDINATES.getHeight();
        }
        coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), newHeight);
    }

    protected void decreaseHeight(byte delta) {
        byte newHeight = (byte) (coordinates.getHeight() - delta);
        if (newHeight < WeatherTower.MIN_COORDINATES.getHeight()) {
            newHeight = WeatherTower.MIN_COORDINATES.getHeight();
        }
        coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), newHeight);
    }
    protected void updateConditions(WeatherTower weatherTower, String message) {
        Weather weather = weatherTower.getWeather(coordinates);
        Logger.out.println(getName() + ": " + message);
        Logger.out.println(getName() + ": my coordinates " + coordinates);
        Logger.out.println(getName() + ": received weather update: " + weather.name());

        switch (weather) {
            case SUN:
                onSunWeather();
                break;
            case SNOW:
                onSnowWeather();
                break;
            case FOG:
                onFogWeather();
                break;
            case RAIN:
                onRainWeather();
                break;
        }
        Logger.out.println(getName() + ": my new coordinates " + coordinates);
    }
    protected String getName() {
        return AirCraft.createName(this.getClass().getSimpleName(), name, id);
    }
    protected void onSunWeather() {}
    protected void onFogWeather() {}
    protected void onSnowWeather() {}
    protected void onRainWeather() {}

    private static String createName(String type, String name, long id) {
        return type + "#" + name + "(" + Long.toString(id) + ")";
    }

}
