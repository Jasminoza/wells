package org.example.repository;

import org.example.model.Well;

public interface WellRepository extends EntityRepository<Well> {
    Well getByName(String wellName);
}
