package cz.cvut.fit.sp.botbuilder.repository;

import cz.cvut.fit.sp.botbuilder.model.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends MongoRepository<MessageEntity, String> {
}