package com.sema4.foudastore.repositories;

import java.util.List;
import java.util.Optional;

public interface InMemoryRepositoty<T,U> {

    List<T> findAll();

    Optional<T> findById(U id);

    void save(T t);

    void deleteById(U id);
}
