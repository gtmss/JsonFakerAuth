package com.example.fakerwithauthorization.controllers;

import com.example.fakerwithauthorization.models.ERole;
import com.example.fakerwithauthorization.models.Roles;
import com.example.fakerwithauthorization.services.SimpleMailManager;
import com.example.fakerwithauthorization.services.dto.TokenResponseDTO;
import com.example.fakerwithauthorization.models.User;
import com.example.fakerwithauthorization.security.jwt.payload.request.LoginRequest;
import com.example.fakerwithauthorization.security.jwt.payload.request.SignupRequest;
import com.example.fakerwithauthorization.security.jwt.payload.response.MessageResponse;
import com.example.fakerwithauthorization.repository.RoleRepository;
import com.example.fakerwithauthorization.repository.UserRepository;
import com.example.fakerwithauthorization.security.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    final
    AuthenticationManager authenticationManager;

    final
    UserRepository userRepository;

    final
    RoleRepository roleRepository;

    final
    PasswordEncoder encoder;

    final
    JwtUtils jwtUtils;

    final SimpleMailManager simpleMailManager;
    

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils, SimpleMailManager simpleMailManager) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.simpleMailManager = simpleMailManager;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        logger.debug("Bearer " + jwt);
        logger.debug("Authentification: " + authentication);
        logger.debug("USERNAME: " + loginRequest.getUsername());
        logger.debug("ROLE: " + userRepository.findByUsername(loginRequest.getUsername()).get().getRoles());

        return ResponseEntity.ok(new TokenResponseDTO(jwt));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        //Create new user's account
        User user = new User(signupRequest.getUsername(),
                           signupRequest.getEmail(),
                            encoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Roles> roles = new HashSet<>();

        if (strRoles == null) {
            Roles userRoles = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRoles);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Roles adminRoles = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow( () -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRoles);

                        break;
                    case "mod":
                        Roles modRoles = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRoles);

                        break;
                    default:
                        Roles userRoles = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(userRoles);
                }
            });
        }

        user.setRoles(roles);

        logger.debug("New user added: " + userRepository.save(user));
        logger.debug("Sending credentials via e-mail...");

        return ResponseEntity.ok(new MessageResponse("User registred succesfully"));
    }
    @GetMapping("/test-mail")
    public void testMail(){
        simpleMailManager.sendSimpleMessage("danielmarandici2001@gmail.com","piva", "piva");

    }

}
