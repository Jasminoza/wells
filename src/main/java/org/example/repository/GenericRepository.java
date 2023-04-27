package org.example.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    List<T> getAll();

    T create(T t);
}
