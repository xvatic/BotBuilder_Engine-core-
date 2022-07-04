package cz.cvut.fit.sp.botbuilder.repository;

import cz.cvut.fit.sp.botbuilder.model.BotEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BotRepository extends MongoRepository<BotEntity, String> {
    @Query("{_id :?0}")
    Optional<BotEntity> findBotEntityById(ObjectId _id);
}
