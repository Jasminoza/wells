package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Well;
import org.example.repository.WellRepository;
import org.example.repository.jdbc.JDBCWellRepositoryImpl;

@AllArgsConstructor
public class WellService {
    private final WellRepository wellRepository;

    public WellService() {
        this.wellRepository = new JDBCWellRepositoryImpl();
    }

    public Well create(Well well) {
        return wellRepository.create(well);
    }

    public Well getWellByName(String wellName) {
        return wellRepository.getByName(wellName);
    }
}
