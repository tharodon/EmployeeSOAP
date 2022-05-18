package com.example.employeesoap.security.controller;

import com.example.employeesoap.security.entity.Role;
import com.example.employeesoap.security.entity.User;
import com.example.employeesoap.security.jwt.JwtUtils;
import com.example.employeesoap.security.pojo.JwtResponse;
import com.example.employeesoap.security.pojo.LoginRequest;
import com.example.employeesoap.security.pojo.MessageResponse;
import com.example.employeesoap.security.pojo.SignupRequest;
import com.example.employeesoap.security.repository.RoleRepository;
import com.example.employeesoap.security.repository.UserRepository;
import com.example.employeesoap.security.service.UserDetailsImpl;
import com.example.employeesoap.security.support.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest){
        Authentication authentication =
                authenticationManager
                        .authenticate(
                                new UsernamePasswordAuthenticationToken(
                                        loginRequest.getLogin(),
                                        loginRequest.getPassword())
                        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority()).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getPassword(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest request){
        if (userRepository.existsByEmail(request.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Email already exist"));
        }if (userRepository.existsByUsername(request.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Username already exist"));
        }
        User user = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()));

        ///////////////////////////////////////
        Set<String> roles = request.getRoles();
        //тут проверка переданных ролей, а пока что хардкод
        ///////////////////////////////////////
        Set<Role> enumRoles = new HashSet<>();
        enumRoles.add(roleRepository.findRoleByName(ERole.ROLE_USER).orElseThrow(RuntimeException::new));
        user.setRoles(enumRoles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User Created"));
    }

}
