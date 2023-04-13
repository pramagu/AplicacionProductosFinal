package com.openwebinars.rest.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openwebinars.rest.Converter.CreateUserDTO;
import com.openwebinars.rest.Converter.GetUserDTO;
import com.openwebinars.rest.Service.ServicioUsuario;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final ServicioUsuario userEntityService;

	@PostMapping("/")
	public ResponseEntity<GetUserDTO> nuevoUsuario(@RequestBody CreateUserDTO newUser) {
		return userEntityService.nuevoUsuario(newUser);

	}

}