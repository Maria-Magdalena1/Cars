package cars.cars.services;

import cars.cars.web.dto.UserRegistrationDTO;
import jakarta.validation.Valid;

public interface UserService{
    void register(@Valid UserRegistrationDTO registrationDTO);
}
