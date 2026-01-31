package cars.cars.entities;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("Admin"),
    USER("User");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }
}
