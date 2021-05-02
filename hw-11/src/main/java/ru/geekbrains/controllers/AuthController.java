package ru.geekbrains.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.beans.JwtTokenUtil;
import ru.geekbrains.entity.ErrorEntity;
import ru.geekbrains.jwt.JwtRequest;
import ru.geekbrains.jwt.JwtResponse;
import ru.geekbrains.services.PersonService;

@RestController("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final PersonService personService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex){
            return new ResponseEntity<>(new ErrorEntity("Данные не совпадают", HttpStatus.UNAUTHORIZED.value()),  HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = personService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

}
