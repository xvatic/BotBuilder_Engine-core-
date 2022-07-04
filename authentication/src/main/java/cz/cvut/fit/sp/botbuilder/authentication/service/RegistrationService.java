package cz.cvut.fit.sp.botbuilder.authentication.service;

import cz.cvut.fit.base.bot_builder.base.exceptions.ItemAlreadyExistsException;
import cz.cvut.fit.sp.botbuilder.authentication.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final UserService userService;

    public UserEntity create (UserEntity user) throws ItemAlreadyExistsException {
        return userService.signUpUser(user);
    }

}
