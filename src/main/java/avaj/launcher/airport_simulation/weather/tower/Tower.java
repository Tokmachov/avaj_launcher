package avaj.launcher.airport_simulation.weather.tower;

import avaj.launcher.airport_simulation.arcraft.Flyable;

import java.util.ArrayList;
import java.util.List;

abstract public class Tower {
    protected List<Flyable> flyableList = new ArrayList<>();

    abstract void register(Flyable flyable);

    abstract void unregister(Flyable flyable);

    protected void conditionsChanged() {
        for (Flyable flyable : flyableList) {
            flyable.updateConditions();
        }
    }
}
