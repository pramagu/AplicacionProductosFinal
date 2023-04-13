package com.openwebinars.rest.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.openwebinars.rest.Converter.CreateUserDTO;
import com.openwebinars.rest.Converter.GetUserDTO;
import com.openwebinars.rest.Converter.UserDTOConverter;
import com.openwebinars.rest.Exceptions.NewUserWithDifferentPasswordsException;
import com.openwebinars.rest.Model.UserEntity;
import com.openwebinars.rest.Model.UserEntityRepository;
import com.openwebinars.rest.Model.UserRole;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioUsuario extends ServicioBase<UserEntity, Long, UserEntityRepository> {

    private final PasswordEncoder passwordEncoder;
    private PassCheck passCheck = lambdaUtils();
    private final UserDTOConverter conversorDTOGetUser;

    public Optional<UserEntity> findByUsername(String username) {
        return this.repositorio.findByUsername(username);
    }

    public ResponseEntity<GetUserDTO> nuevoUsuario(CreateUserDTO newUser) {

        if (!passCheck.samePasswords(newUser.getPassword(), newUser.getPassword2()))
            throw new NewUserWithDifferentPasswordsException();

        UserEntity usuario = UserEntity.builder().username(newUser.getUsername())
                .password(passwordEncoder.encode(newUser.getPassword())).avatar(newUser.getAvatar())
                .fullName(newUser.getFullname()).email(newUser.getEmail())
                .roles(Stream.of(UserRole.USER).collect(Collectors.toSet())).build();

        this.save(usuario);
        try {

            GetUserDTO usuarioRespuesta = conversorDTOGetUser.convertUserEntityToGetUserDto(usuario);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(usuarioRespuesta);

        } catch (DataIntegrityViolationException integridadException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario ya existe");
        }
    }

    public ResponseEntity<GetUserDTO> convertUserEntityToGetUserDto(UserEntity user) {
        return ResponseEntity.ok(conversorDTOGetUser.convertUserEntityToGetUserDto(user));
    }

    private PassCheck lambdaUtils() {
        PassCheck passCheck = (a, b) -> a.contentEquals(b);
        return passCheck;
    };

    @FunctionalInterface
    interface PassCheck {
        Boolean samePasswords(String password, String secondPassword);
    }
}
