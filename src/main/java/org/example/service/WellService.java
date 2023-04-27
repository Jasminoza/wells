package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Well;
import org.example.repository.WellRepository;
import org.example.repository.jdbc.JDBCWellRepositoryImpl;

import java.util.List;

@AllArgsConstructor
public class WellService {
    private final WellRepository wellRepository;

    public WellService() {
        this.wellRepository = new JDBCWellRepositoryImpl();
    }

   public List<Well> getAll() {
        return wellRepository.getAll();
   }

   public Well create(Well well) {
        return wellRepository.create(well);
   }

   public Well getById(Long id) {
        return wellRepository.getById(id);
   }

    public Well update(Well well) {
        return wellRepository.update(well);
    }

    public void delete(Long id) {
        wellRepository.delete(id);
    }

    public Well getWellByName(String wellName) {
        return wellRepository.getByName(wellName);
    }

    public String getAllWithEquipments(String outputFileName) {
        return wellRepository.getAllWithEquipments(outputFileName);
    }
}
