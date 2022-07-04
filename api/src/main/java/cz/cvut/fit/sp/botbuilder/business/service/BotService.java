package cz.cvut.fit.sp.botbuilder.business.service;

import cz.cvut.fit.base.bot_builder.base.AbstractCrudService;

import cz.cvut.fit.base.bot_builder.base.exceptions.ItemNotFoundException;
import cz.cvut.fit.sp.botbuilder.business.exception.*;
import cz.cvut.fit.sp.botbuilder.model.*;
import cz.cvut.fit.sp.botbuilder.repository.BotRepository;
import cz.cvut.fit.sp.botbuilder.repository.MessageRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BotService extends AbstractCrudService<String, BotEntity> {

    private SecurityService securityService;

    private final MessageRepository messageRepository;

    protected BotService(BotRepository repository, MessageRepository messageRepository) {
        super(repository);
        this.securityService = new SecurityService(repository);
        this.messageRepository = messageRepository;
    }

    @Override
    public boolean notExists(BotEntity entity) {
        return false;
    }

    public String setToken(String id, String token) throws RequiredBotHasDifferentOwnerException {
        if (!securityService.checkAuthor(id)) {
            throw new RequiredBotHasDifferentOwnerException();
        }
        if (repository.findById(id).isPresent()) {
            BotEntity requiredBot = repository.findById(id).get();
            requiredBot.setToken(token);
            repository.save(requiredBot);
            return requiredBot.getId();
        } else {
            throw new ItemNotFoundException(id);
        }
    }

    public BotEntity createBot(String token, String name) {
        String username = securityService.getCurrentUser();
        BotEntity newBot = new BotEntity();
        newBot.setOwner(username);
        newBot.setName(name);
        newBot.setToken(token);
        repository.save(newBot);
        return newBot;
    }

    public void addMessage(MessageEntity message, String id) throws RequiredBotHasDifferentOwnerException {
        if (!securityService.checkAuthor(id)) {
            throw new RequiredBotHasDifferentOwnerException();
        }
        if (repository.findById(id).isEmpty()) {
            throw new ItemNotFoundException(id);
        }
        BotEntity bot = repository.findById(id).get();

        bot.addMessage(message);


        repository.save(bot);
    }

    public void deleteBot(String id) throws RequiredBotHasDifferentOwnerException {
        if (!securityService.checkAuthor(id)) {
            throw new RequiredBotHasDifferentOwnerException();
        }
        if (repository.findById(id).isEmpty()) {
            throw new ItemNotFoundException(id);
        }

        BotEntity bot = repository.findById(id).get();
        List<MessageEntity> stages = bot.getStages();
        for (MessageEntity stage: stages) {
            messageRepository.deleteById(stage.getId());
        }
        repository.deleteById(id);
    }

    public void addConditionalRoute(String idBot, String idSourceResponseMessage, String idRequestMessage, String idDestResponseMessage) throws RequiredBotHasDifferentOwnerException, BadMessageTypeException, UnreachableMessageBranchException, RouteAlreadyExistsException {
        if (!securityService.checkAuthor(idBot)) {
            throw new RequiredBotHasDifferentOwnerException();
        }
        if (repository.findById(idBot).isEmpty()) {
            throw new ItemNotFoundException(idBot);
        }

        if (messageRepository.findById(idSourceResponseMessage).isEmpty() || messageRepository.findById(idRequestMessage).isEmpty() || messageRepository.findById(idDestResponseMessage).isEmpty()) {
            throw new ItemNotFoundException("BAD MESSAGE");
        }

        if (!messageRepository.findById(idSourceResponseMessage).get().isResponse() || messageRepository.findById(idRequestMessage).get().isResponse() || !messageRepository.findById(idDestResponseMessage).get().isResponse()) {
            throw new BadMessageTypeException();
        }

        BotEntity bot = repository.findById(idBot).get();

        if (bot.getRoutes().get(idSourceResponseMessage).isEmpty()) {
            bot.addRouteList(idSourceResponseMessage);
        }

        if (!bot.getRoutes().get(idSourceResponseMessage).get("epsilon").isEmpty()) {
            throw new UnreachableMessageBranchException();
        }

        if (!bot.getRoutes().get(idSourceResponseMessage).get(idRequestMessage).isEmpty()) {
            throw new RouteAlreadyExistsException();
        }

        bot.addConditionalRoute(idSourceResponseMessage, idRequestMessage, idDestResponseMessage);

        repository.save(bot);
    }


    public void generateBot(String id) throws RequiredBotHasDifferentOwnerException, NotEnoughParametersException {

    }

    public Optional<BotEntity> findById(String id) {
        return repository.findById(id);
    }

    public List<BotEntity> findAll() {
        return repository.findAll();
    }

}
