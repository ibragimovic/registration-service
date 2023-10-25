package musaev.ibragim.registrationservice.service;

import lombok.AllArgsConstructor;
import musaev.ibragim.registrationservice.dto.UserRegisterForm;
import musaev.ibragim.registrationservice.entity.User;
import musaev.ibragim.registrationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void registerUser(UserRegisterForm userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .lastname(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();

        userRepository.save(user);
    }

    public boolean isUserExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
