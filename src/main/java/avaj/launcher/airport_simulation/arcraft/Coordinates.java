package avaj.launcher.airport_simulation.arcraft;

import java.util.Objects;

public class Coordinates {
    private byte longitude;
    private byte latitude;
    private byte height;

    public Coordinates(byte longitude, byte latitude, byte height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public byte getLongitude() {
        return longitude;
    }

    public byte getLatitude() {
        return latitude;
    }

    public byte getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return longitude == that.longitude && latitude == that.latitude && height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude, height);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", height=" + height +
                '}';
    }
}
