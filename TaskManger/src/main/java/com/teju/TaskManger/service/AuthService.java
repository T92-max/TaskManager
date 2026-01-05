package com.teju.TaskManger.service;

import com.teju.TaskManger.dto.AuthResponse;
import com.teju.TaskManger.dto.LoginRequest;
import com.teju.TaskManger.dto.RegisterRequest;
import com.teju.TaskManger.entity.User;
import com.teju.TaskManger.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest request) {
        System.out.println("=== Register Debug ===");
        System.out.println("Email: " + request.getEmail());

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        user.setFullName(request.getFullName());

        User savedUser = userRepository.save(user);
        System.out.println("User saved with ID: " + savedUser.getId());
        System.out.println("Encoded password: " + encodedPassword.substring(0, 20) + "...");

        String jwtToken = jwtService.generateToken(savedUser);
        System.out.println("Generated token: " + jwtToken.substring(0, 20) + "...");

        return new AuthResponse(jwtToken, savedUser.getEmail(), savedUser.getFullName());
    }

    public AuthResponse login(LoginRequest request) {
        System.out.println("=== Login Debug ===");
        System.out.println("Login attempt for: " + request.getEmail());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            System.out.println("Authentication successful!");
        } catch (BadCredentialsException e) {
            System.out.println("Authentication failed: Bad credentials");
            throw new RuntimeException("Invalid email or password");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("User found: " + user.getEmail());

        String jwtToken = jwtService.generateToken(user);
        System.out.println("Generated token: " + jwtToken.substring(0, 20) + "...");

        return new AuthResponse(jwtToken, user.getEmail(), user.getFullName());
    }
}