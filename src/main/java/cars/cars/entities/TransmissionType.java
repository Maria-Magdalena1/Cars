package cars.cars.entities;

import lombok.Getter;

@Getter
public enum TransmissionType {

    MANUAL("Manual"),
    AUTOMATIC("Automatic"),
    AMT("Automated Manual"),
    CVT("Continuously Variable Transmission"),
    DCT("Dual-Clutch Transmission");

    private final String displayName;

    TransmissionType(String displayName) {
        this.displayName = displayName;
    }
}
