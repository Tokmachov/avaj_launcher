package avaj.launcher.airport_simulation;

import avaj.launcher.airport_simulation.jet_plane.JetPlaneTests;

public class TestMain {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ParsingTests.given_ScenarioFileDoesNotExist_FileNotFoundExceptionIsLogged();
        ParsingTests.given_FirstLineInScenarioIsNotParsableAsInt_ExceptionIsCaughtAndLogged();
        ParsingTests.given_NoLinesInScenario_FileIsEmptyIsLogged();
        ParsingTests.given_AircraftLinesAreAbsent_ScenarioDoesntContainsAircraftLinesIsLogged();
        ParsingTests.given_AircraftLineHasWrongNumberOfArgs_ExceptionIsLogged();
        ParsingTests.given_HeightIsOutOfRange_ExceptionIsLogged();

        JetPlaneTests.when_RAIN_LatitudeIncreasesWith5();
        JetPlaneTests.when_SUN_LatitudeIncreasesWith10HeightIncreasesWith2();
        JetPlaneTests.when_FOG_LatitudeIncreasesWith1();
        JetPlaneTests.when_SNOW_HeightDecreasesWith7();

        AllAirCraftTests.when_HeightReachesOrPasses100ItRemainsOn100();
        AllAirCraftTests.when_AircraftNeedsToGoBelow0Height_AircraftUnregisters();
    }
}
