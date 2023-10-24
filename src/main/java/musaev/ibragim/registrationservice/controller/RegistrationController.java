package musaev.ibragim.registrationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PatchMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO userDTO) {
        if (userService.isUserExists(userDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with this email already exists.");
        }

        userService.registerUser(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

}
