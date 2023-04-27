package org.example.repository;

import org.example.model.Well;

public interface WellRepository extends GenericRepository<Well> {
    Well getByName(String wellName);
}
