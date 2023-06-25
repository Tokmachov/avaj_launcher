package avaj.launcher.airport_simulation.jet_plane;

import avaj.launcher.airport_simulation.AirportSimulation;
import avaj.launcher.airport_simulation.JetPlaneTestWeatherProvider;
import avaj.launcher.airport_simulation.TestUtils;
import avaj.launcher.airport_simulation.arcraft.Coordinates;
import avaj.launcher.airport_simulation.logger.Logger;
import avaj.launcher.airport_simulation.weather.Weather;
import avaj.launcher.airport_simulation.weather.provider.WeatherProvider;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;

public class JetPlaneTests {
    public static void when_SUN_LatitudeIncreasesWith10HeightIncreasesWith2() throws NoSuchFieldException, IllegalAccessException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Logger.out = new PrintStream(byteArrayOutputStream);
        JetPlaneTestWeatherProvider jetPlaneTestWeatherProvider = new JetPlaneTestWeatherProvider();
        Field field = WeatherProvider.class.getDeclaredField("weatherProvider");
        field.setAccessible(true);
        field.set(null, jetPlaneTestWeatherProvider);

        jetPlaneTestWeatherProvider.setCurrentWeather(Weather.SUN);

        AirportSimulation.main(new String[] {"scenario_7_jet_plane.txt"});

        TestUtils.assertContainsAll(Arrays.asList("latitude=30", "height=27"), byteArrayOutputStream.toString(), TestUtils.getEnclosingMethodName());
    }

    public static void when_RAIN_LatitudeIncreasesWith5() throws NoSuchFieldException, IllegalAccessException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Logger.out = new PrintStream(byteArrayOutputStream);
        JetPlaneTestWeatherProvider jetPlaneTestWeatherProvider = new JetPlaneTestWeatherProvider();
        Field field = WeatherProvider.class.getDeclaredField("weatherProvider");
        field.setAccessible(true);
        field.set(null, jetPlaneTestWeatherProvider);
        jetPlaneTestWeatherProvider.setCurrentWeather(Weather.RAIN);

        AirportSimulation.main(new String[] {"scenario_7_jet_plane.txt"});

        TestUtils.assertContains("latitude=25", byteArrayOutputStream.toString(), TestUtils.getEnclosingMethodName());
    }

    public static void when_FOG_LatitudeIncreasesWith1() throws NoSuchFieldException, IllegalAccessException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Logger.out = new PrintStream(byteArrayOutputStream);
        JetPlaneTestWeatherProvider jetPlaneTestWeatherProvider = new JetPlaneTestWeatherProvider();
        Field field = WeatherProvider.class.getDeclaredField("weatherProvider");
        field.setAccessible(true);
        field.set(null, jetPlaneTestWeatherProvider);
        jetPlaneTestWeatherProvider.setCurrentWeather(Weather.FOG);

        AirportSimulation.main(new String[] {"scenario_7_jet_plane.txt"});

        TestUtils.assertContains("latitude=21", byteArrayOutputStream.toString(), TestUtils.getEnclosingMethodName());
    }

    public static void when_SNOW_HeightDecreasesWith7() throws NoSuchFieldException, IllegalAccessException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Logger.out = new PrintStream(byteArrayOutputStream);
        JetPlaneTestWeatherProvider jetPlaneTestWeatherProvider = new JetPlaneTestWeatherProvider();
        Field field = WeatherProvider.class.getDeclaredField("weatherProvider");
        field.setAccessible(true);
        field.set(null, jetPlaneTestWeatherProvider);

        jetPlaneTestWeatherProvider.setCurrentWeather(Weather.SNOW);

        AirportSimulation.main(new String[] {"scenario_7_jet_plane.txt"});

        TestUtils.assertContains("height=18", byteArrayOutputStream.toString(), TestUtils.getEnclosingMethodName());
    }
}
