package org.example.repository;

import org.example.model.Well;

public interface WellRepository extends GenericRepository<Well, Long> {
    Well getByName(String wellName);
}
