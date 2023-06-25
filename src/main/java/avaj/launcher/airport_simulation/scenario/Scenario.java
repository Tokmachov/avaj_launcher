package avaj.launcher.airport_simulation.scenario;

import avaj.launcher.airport_simulation.AirportSimulation;
import avaj.launcher.airport_simulation.arcraft.AirCraftDto;
import avaj.launcher.airport_simulation.exceptions.ScenarioParsingException;
import avaj.launcher.airport_simulation.weather.tower.WeatherTower;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Scenario {
    private String sourceFile;

    private int numberOfTimesRun;

    private List<AirCraftDto> aircraftList = new ArrayList<>();

    public Scenario(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public int getNumberOfTimesRun() {
        return numberOfTimesRun;
    }

    public List<AirCraftDto> getAircraftList() {
        return aircraftList;
    }

    public void parse() throws IOException, ScenarioParsingException {
        try (BufferedReader fileReader = new BufferedReader(getScenarioSource(sourceFile))) {
            String line = fileReader.readLine();
            parseNumberOfTimesSimulationIsRun(line);

            int lineNumber = 2;
            line = fileReader.readLine();
            while (line != null) {
                try {
                    parseAirCraft(line);
                } catch (ScenarioParsingException e) {
                    throw new ScenarioParsingException(e.getMessage() + ". At line # " + lineNumber);
                }
                lineNumber++;
                line = fileReader.readLine();
            }
            if (aircraftList.isEmpty()) {
                throw new ScenarioParsingException("Scenario file doesn't contain aircraft lines");
            }
        }
    }

    private void parseNumberOfTimesSimulationIsRun(String line) throws ScenarioParsingException {
        if (line != null) {
            try {
                numberOfTimesRun = Integer.parseUnsignedInt(line);
            } catch (Exception e) {
                throw new ScenarioParsingException("Failed to parse positive number at line # 1. Exception: " + e.getClass().getSimpleName() + " " + e.getMessage());
            }
            if (numberOfTimesRun <= 0) {
                throw new ScenarioParsingException("Line # 1. Parsed negative number. Reason overflowing value in scenario.");
            }
        } else {
            throw new ScenarioParsingException("Failed to parse positive number at line # 1. File is empty");
        }
    }

    private void parseAirCraft(String line) throws ScenarioParsingException {
        String[] parts = line.split("\\s+");
        if (parts.length != 5) {
            throw new ScenarioParsingException("In scenario file expected 5 arguments in each aircraft line.");
        }
        AirCraftDto.Type type = parseAirCraftType(parts[0]);
        byte longitude = parseLongitude(parts[2]);
        byte latitude = parseLatitude(parts[3]);
        byte height = parseHeight(parts[4]);

        AirCraftDto airCraftDto = new AirCraftDto(type, parts[1], longitude, latitude, height);
        aircraftList.add(airCraftDto);
    }

    private static AirCraftDto.Type parseAirCraftType(String string) throws ScenarioParsingException {
        AirCraftDto.Type type = null;
        String stringType = string;
        try {
            type = AirCraftDto.Type.valueForLabel(stringType);
        } catch (Exception e) {
            throw new ScenarioParsingException("Failed to parse aircraft type. Argument was: " + stringType + ". But valid arguments are: " + Arrays.stream(AirCraftDto.Type.values()).map(AirCraftDto.Type::getLabel).collect(Collectors.toList()));
        }
        return type;
    }

    private byte parseLongitude(String string) throws ScenarioParsingException {
        byte longitude = 0;
        try {
            longitude = (byte) Integer.parseInt(string);
        } catch (Exception e) {
            throw new ScenarioParsingException("Failed to parse longitude");
        }
        byte minLongitude = WeatherTower.MIN_COORDINATES.getLongitude();
        byte maxLongitude = WeatherTower.MAX_COORDINATES.getLongitude();

        if (longitude < minLongitude || longitude > maxLongitude) {
            throw new ScenarioParsingException(String.format("Longitude should be between %d and %d", minLongitude, maxLongitude));
        }
        return longitude;
    }

    private byte parseLatitude(String line) throws ScenarioParsingException {
        byte latitude = 0;
        try {
            latitude = (byte) Integer.parseInt(line);
        } catch (Exception e) {
            throw new ScenarioParsingException("Failed to parse latitude");
        }

        byte minLatitude = WeatherTower.MIN_COORDINATES.getLatitude();
        byte maxLatitude = WeatherTower.MAX_COORDINATES.getLatitude();

        if (latitude < minLatitude || latitude > maxLatitude) {
            throw new ScenarioParsingException(String.format("Latitude should be between %d and %d", minLatitude, maxLatitude));
        }
        return latitude;
    }

    private static byte parseHeight(String string) throws ScenarioParsingException {
        byte height = 0;
        try {
            height = (byte) Integer.parseInt(string);
        } catch (Exception e) {
            throw new ScenarioParsingException("Failed to parse height");
        }

        byte minHeight = WeatherTower.MIN_COORDINATES.getHeight();
        byte maxHeight = WeatherTower.MAX_COORDINATES.getHeight();

        if (height < minHeight || height > maxHeight) {
            throw new ScenarioParsingException(String.format("Height should be between %d and %d", minHeight, maxHeight));
        }
        return height;
    }

    private Reader getScenarioSource(String path) throws FileNotFoundException {
        if (path.isEmpty()) {
            throw new FileNotFoundException();
        }

        File f = new File(path);
        if (f.exists()) {
            return new FileReader(path);
        } else {
            InputStream is = AirportSimulation.class.getClassLoader().getResourceAsStream(path);
            if (is != null) {
                return new InputStreamReader(is);
            }
            throw new FileNotFoundException();
        }
    }

    @Override
    public String toString() {
        return "Scenario{" +
                "sourceFile='" + sourceFile + '\'' +
                ", numberOfTimesRun=" + numberOfTimesRun +
                ", aircraftList=" + aircraftList +
                '}';
    }
}
