package cz.cvut.fit.sp.botbuilder.business.service;

import cz.cvut.fit.base.bot_builder.base.exceptions.ItemNotFoundException;
import cz.cvut.fit.sp.botbuilder.model.BotEntity;
import cz.cvut.fit.sp.botbuilder.repository.BotRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SecurityService {

    private final BotRepository botRepository;

    public SecurityService(BotRepository botRepository) {
        this.botRepository = botRepository;
    }

    public boolean checkAuthor(String id) {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        Optional<BotEntity> bot =  botRepository.findById(id);
        if (bot.isEmpty()) {
            throw new ItemNotFoundException(id);
        }

        return Objects.equals(bot.get().getOwner(), username);
    }

    public String getCurrentUser() {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        return username;
    }

}
