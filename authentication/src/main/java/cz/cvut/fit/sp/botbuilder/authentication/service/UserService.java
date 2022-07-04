package cz.cvut.fit.sp.botbuilder.authentication.service;

import cz.cvut.fit.base.bot_builder.base.exceptions.ItemAlreadyExistsException;
import cz.cvut.fit.sp.botbuilder.authentication.model.UserRole;
import cz.cvut.fit.sp.botbuilder.authentication.model.UserEntity;
import cz.cvut.fit.sp.botbuilder.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with username %s not found";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, username)));
    }

    public UserEntity signUpUser(UserEntity user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new ItemAlreadyExistsException(user.getUsername());

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setUserRole(UserRole.USER);
        user.setPasswordChangedTime(new Date());
        userRepository.save(user);
        return null;
    }

}
