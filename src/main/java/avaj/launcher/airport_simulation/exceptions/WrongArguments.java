package avaj.launcher.airport_simulation.exceptions;

public class WrongArguments extends RuntimeException {
    public WrongArguments() {
        super("App expects one argument - relative or absolute path to file with simulation scenario.txt");
    }
}