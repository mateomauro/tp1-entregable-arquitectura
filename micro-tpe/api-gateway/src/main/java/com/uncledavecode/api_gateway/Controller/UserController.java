package com.uncledavecode.api_gateway.Controller;

import com.uncledavecode.api_gateway.DTO.UserDTO;
import com.uncledavecode.api_gateway.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<?> saveUser( @RequestBody UserDTO userDTO) {
        System.out.println("saveUser: " + userDTO);
        final var id = userService.saveUser( userDTO );
        return new ResponseEntity<>( id, HttpStatus.CREATED );
    }
}
