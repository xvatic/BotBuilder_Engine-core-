package cz.cvut.fit.sp.botbuilder.business.service;

import cz.cvut.fit.base.bot_builder.base.AbstractCrudService;
import cz.cvut.fit.sp.botbuilder.model.MessageEntity;
import cz.cvut.fit.sp.botbuilder.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends AbstractCrudService<String, MessageEntity> {

    protected MessageService(MessageRepository repository) {
        super(repository);
    }

    @Override
    public boolean notExists(MessageEntity entity) {
        return false;
    }
}
