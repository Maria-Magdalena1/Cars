package cars.cars.entities;

import lombok.Getter;

@Getter
public enum EngineType {
    GAS("Gas"),
    GASOLINE("Gasoline"),
    DIESEL("Diesel"),
    ELECTRIC("Electric"),
    HYBRID("Hybrid"),
    PLUG_IN("Plug-in Hybrid");

    private final String displayName;

    EngineType(String displayName) {
        this.displayName = displayName;
    }
}
