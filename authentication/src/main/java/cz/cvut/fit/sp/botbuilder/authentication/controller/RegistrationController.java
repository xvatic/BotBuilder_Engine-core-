package cz.cvut.fit.sp.botbuilder.authentication.controller;

import cz.cvut.fit.base.bot_builder.base.exceptions.ItemAlreadyExistsException;
import cz.cvut.fit.sp.botbuilder.authentication.dto.RegistrationRequest;
import cz.cvut.fit.sp.botbuilder.authentication.model.UserEntity;
import cz.cvut.fit.sp.botbuilder.authentication.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cz.cvut.fit.sp.botbuilder.authentication.util.ObjectMapperUtils;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/bot-builder/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody RegistrationRequest registrationRequest){
        try {
            registrationService.create(ObjectMapperUtils.map(registrationRequest, UserEntity.class));
            return new ResponseEntity<>("User successfully created", HttpStatus.CREATED);
        } catch (ItemAlreadyExistsException e) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
