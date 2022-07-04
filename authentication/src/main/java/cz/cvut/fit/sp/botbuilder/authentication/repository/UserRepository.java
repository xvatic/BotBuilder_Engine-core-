package cz.cvut.fit.sp.botbuilder.authentication.repository;


import cz.cvut.fit.sp.botbuilder.authentication.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
}
