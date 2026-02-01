package cars.cars.services;

import cars.cars.entities.Role;
import cars.cars.entities.User;
import cars.cars.exceptions.UsernameTakenException;
import cars.cars.repositories.UserRepository;
import cars.cars.web.dto.UserRegistrationDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(User user) {
        userRepository.save(user);
    }

    public boolean isUsernameTaken(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public void register(UserRegistrationDTO registrationDTO) {

        if (isUsernameTaken(registrationDTO.getUsername())) {
            throw new UsernameTakenException("Username already taken!");
        }

        User user = User.builder()
                .name(registrationDTO.getFullName())
                .username(registrationDTO.getUsername())
                .password(passwordEncoder.encode(registrationDTO.getPassword()))
                .email(registrationDTO.getEmail())
                .role(Role.USER)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();

        save(user);
    }
}
