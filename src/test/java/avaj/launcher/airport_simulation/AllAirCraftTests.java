package avaj.launcher.airport_simulation;

import avaj.launcher.airport_simulation.logger.Logger;
import avaj.launcher.airport_simulation.weather.Weather;
import avaj.launcher.airport_simulation.weather.provider.WeatherProvider;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

public class AllAirCraftTests {
    public static void when_HeightReachesOrPasses100ItRemainsOn100() throws NoSuchFieldException, IllegalAccessException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Logger.out = new PrintStream(byteArrayOutputStream);
        JetPlaneTestWeatherProvider jetPlaneTestWeatherProvider = new JetPlaneTestWeatherProvider();
        Field field = WeatherProvider.class.getDeclaredField("weatherProvider");
        field.setAccessible(true);
        field.set(null, jetPlaneTestWeatherProvider);

        jetPlaneTestWeatherProvider.setCurrentWeather(Weather.SUN);

        AirportSimulation.main(new String[] {"scenario_8_all_aircraft_test.txt"});

        TestUtils.assertAllKeyValuePairsFollowCondition(byteArrayOutputStream.toString(), "height", (height) -> height <= 100, TestUtils.getEnclosingMethodName());
    }

   public static void when_AircraftNeedsToGoBelow0Height_HeightRemains0() throws NoSuchFieldException, IllegalAccessException {
       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
       Logger.out = new PrintStream(byteArrayOutputStream);
       JetPlaneTestWeatherProvider jetPlaneTestWeatherProvider = new JetPlaneTestWeatherProvider();
       Field field = WeatherProvider.class.getDeclaredField("weatherProvider");
       field.setAccessible(true);
       field.set(null, jetPlaneTestWeatherProvider);

       jetPlaneTestWeatherProvider.setCurrentWeather(Weather.SNOW);

       AirportSimulation.main(new String[] {"scenario_9_all_aircraft_test_1.txt"});

       TestUtils.assertAllKeyValuePairsFollowCondition(byteArrayOutputStream.toString(), "height", (height) -> height >= 0, TestUtils.getEnclosingMethodName());
   }

    public static void when_AircraftNeedsToGoBelow0Height_AircraftUnregisters() throws NoSuchFieldException, IllegalAccessException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Logger.out = new PrintStream(byteArrayOutputStream);
        JetPlaneTestWeatherProvider jetPlaneTestWeatherProvider = new JetPlaneTestWeatherProvider();
        Field field = WeatherProvider.class.getDeclaredField("weatherProvider");
        field.setAccessible(true);
        field.set(null, jetPlaneTestWeatherProvider);

        jetPlaneTestWeatherProvider.setCurrentWeather(Weather.SNOW);

        AirportSimulation.main(new String[] {"scenario_9_all_aircraft_test_1.txt"});
        TestUtils.assertContains("Tower says: JetPlane#OoOooOoo(5) unregistered to weather tower.", byteArrayOutputStream.toString(),TestUtils.getEnclosingMethodName());
    }
}
