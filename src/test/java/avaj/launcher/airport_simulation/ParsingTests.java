package avaj.launcher.airport_simulation;

import avaj.launcher.airport_simulation.logger.Logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParsingTests {
    public static void given_ScenarioFileDoesNotExist_FileNotFoundExceptionIsLogged() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Logger.out = new PrintStream(byteArrayOutputStream);

        AirportSimulation.main(new String[] {""});
        TestUtils.assertContains("FileNotFoundException", byteArrayOutputStream.toString(), TestUtils.getEnclosingMethodName());
    }

    public static void given_FirstLineInScenarioIsNotParsableAsInt_ExceptionIsCaughtAndLogged() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Logger.out = new PrintStream(byteArrayOutputStream);

        AirportSimulation.main(new String[] {"scenario_1.txt"});
        TestUtils.assertContains("Failed to parse positive number at line # 1. Exception:", byteArrayOutputStream.toString(), TestUtils.getEnclosingMethodName());
    }

    public static void given_NoLinesInScenario_FileIsEmptyIsLogged() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Logger.out = new PrintStream(byteArrayOutputStream);

        AirportSimulation.main(new String[] {"scenario_2.txt"});
        TestUtils.assertContains("Failed to parse positive number at line # 1. File is empty", byteArrayOutputStream.toString(), TestUtils.getEnclosingMethodName());
    }

    public static void given_AircraftLinesAreAbsent_ScenarioDoesntContainsAircraftLinesIsLogged() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Logger.out = new PrintStream(byteArrayOutputStream);

        AirportSimulation.main(new String[] {"scenario_3.txt"});
        TestUtils.assertContains("Scenario file doesn't contain aircraft lines", byteArrayOutputStream.toString(), TestUtils.getEnclosingMethodName());
    }

    public static void given_AircraftLineHasWrongNumberOfArgs_ExceptionIsLogged() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Logger.out = new PrintStream(byteArrayOutputStream);

        AirportSimulation.main(new String[] {"scenario_4.txt"});
        TestUtils.assertContains("In scenario file expected 5 arguments in each aircraft line", byteArrayOutputStream.toString(), TestUtils.getEnclosingMethodName());
    }

    public static void given_HeightIsOutOfRange_ExceptionIsLogged() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Logger.out = new PrintStream(byteArrayOutputStream);

        AirportSimulation.main(new String[] {"scenario_5.txt"});
        TestUtils.assertContains("Message: Height should be between", byteArrayOutputStream.toString(), TestUtils.getEnclosingMethodName());
    }
}
