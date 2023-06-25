package avaj.launcher.airport_simulation.arcraft;

import java.util.Arrays;
import java.util.EnumMap;

public class AirCraftDto {
    public enum Type {
        JET_PLANE("JetPlane"), HELICOPTER("Helicopter"), BALOON("Baloon");

        public String getLabel() {
            return label;
        }

        public static Type valueForLabel(String label) {
            return Arrays.stream(Type.values()).filter(type -> type.label.equals(label)).findAny().orElseThrow(() -> new RuntimeException());
        }

        private final String label;
        private Type(String label) {
            this.label = label;
        }
    }
    private Type type;
    private String name;
    private byte longitude;
    private byte latitude;
    private byte height;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getLongitude() {
        return longitude;
    }

    public void setLongitude(byte longitude) {
        this.longitude = longitude;
    }

    public byte getLatitude() {
        return latitude;
    }

    public void setLatitude(byte latitude) {
        this.latitude = latitude;
    }

    public byte getHeight() {
        return height;
    }

    public void setHeight(byte height) {
        this.height = height;
    }

    public AirCraftDto(Type type, String name, byte longitude, byte latitude, byte height) {
        this.type = type;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    @Override
    public String toString() {
        return "AirCraftDto{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", height=" + height +
                '}' + "\n";
    }
}
