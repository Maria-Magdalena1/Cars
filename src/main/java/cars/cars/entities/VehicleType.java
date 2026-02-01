package cars.cars.entities;

import lombok.Getter;

@Getter
public enum VehicleType {
    CAR("Car"),
    SUV("SUV"),
    VAN("Van"),
    TRUCK("Truck"),
    MOTORCYCLE("Motorcycle"),
    AGRICULTURAL("Agricultural"),
    CARAVANS("Caravans"),
    YACHT("Yacht"),
    BOAT("Boat"),
    TRAILER("Trailer"),
    BICYCLE("Bicycle");

    private final String displayName;

    VehicleType(String displayName) {
        this.displayName = displayName;
    }
}
