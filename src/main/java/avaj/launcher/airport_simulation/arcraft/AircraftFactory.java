package avaj.launcher.airport_simulation.arcraft;

public class AircraftFactory {
    public static Flyable newAircraft(AirCraftDto.Type type, String name, byte longitude, byte latitude, byte height) {
        Coordinates coordinates = new Coordinates(latitude, longitude, height);
        return switch (type) {
            case JET_PLANE -> new JetPlane(name, coordinates);
            case HELICOPTER -> new Helicopter(name, coordinates);
            case BALOON -> new Baloon(name, coordinates);
        };
    }
}
