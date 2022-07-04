package cz.cvut.fit.base.bot_builder.base;


import cz.cvut.fit.base.bot_builder.base.exceptions.ItemNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;


public abstract class AbstractCrudService<K, E> {

    protected final MongoRepository<E, K> repository;

    protected AbstractCrudService(MongoRepository<E, K> repository) {
        this.repository = repository;
    }


    @Transactional
    public E create(E entity) {
        return repository.save(entity);
    }

    public Optional<E> getById(K id) {
        if (!repository.existsById(id)) throw new ItemNotFoundException(id);
        return repository.findById(id);
    }

    @Transactional
    public E update(E entity) {
        if (notExists(entity)) throw new ItemNotFoundException(entity);
        return repository.save(entity);
    }

    public Collection<E> getAll() {
        return repository.findAll();
    }

    @Transactional
    public void deleteById(K id) {
        if (!repository.existsById(id)) throw new ItemNotFoundException(id);

        repository.deleteById(id);
    }

    public abstract boolean notExists(E entity);
}

