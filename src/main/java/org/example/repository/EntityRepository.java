package org.example.repository;

import java.util.List;

public interface EntityRepository<T> {
    List<T> getAll();

    T create(T t);
}
